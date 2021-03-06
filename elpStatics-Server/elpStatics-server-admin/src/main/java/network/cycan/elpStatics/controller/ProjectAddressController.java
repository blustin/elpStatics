package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import jnr.ffi.annotations.In;
import lombok.extern.slf4j.Slf4j;
import network.cycan.common.apiInfor.ApiResponse;
import network.cycan.core.util.DateUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.dto.AirProjectAddressDto;
import network.cycan.elpStatics.model.dto.AirProjectDto;
import network.cycan.elpStatics.model.entity.AirProject;
import network.cycan.elpStatics.model.entity.AirProjectAddress;
import network.cycan.elpStatics.service.IAirProjectAddressService;
import network.cycan.elpStatics.util.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/projectAddress")
public class ProjectAddressController extends BaseController {

    @Autowired
    private IAirProjectAddressService projectAddressService;
    @RequestMapping("/pageList")
    public String  projectList(HttpServletRequest request, Model model, AirProjectAddressDto dto){
        IPage<AirProjectAddress> pageList =  projectAddressService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "projectAddress/pageList";
    }
    @ResponseBody
    @RequestMapping("/add")
    public ApiResponse add(HttpServletRequest request, Model model,  @RequestBody AirProjectAddress airProjectAddress)
    {

        airProjectAddress.setSourceType("????????????");
        airProjectAddress.setCreateTime(LocalDateTime.now());
        airProjectAddress.setUpdateTime(LocalDateTime.now());
        projectAddressService.save(airProjectAddress);
        return ApiResponse.builder().success(true).build();

    }
    @ResponseBody
    @RequestMapping(value = "/deleteBacth",method = {RequestMethod.POST })
    public ApiResponse deleteBacth(HttpServletRequest request, Model model, @RequestBody List<String> ids)
    {
        projectAddressService.removeByIds(ids);
        return ApiResponse.builder().success(true).build();
    }

    /**
     * @param response
     * @return
     * @Description ??????CSV
     **/
    @GetMapping("/downloadCVS")
    public void downloadAllUserRoleCSV(HttpServletResponse response,AirProjectAddressDto dto) throws IOException {
        String[] head = new String[]{dto.getProjectName()};
        List<String> userAddress = projectAddressService.selectAddressList(dto);
        List<String[]> values = new ArrayList<>();
        for (String item : userAddress) {
            values.add(new String[]{item});
        }
        String fileName = dto.getProjectName() + DateUtils.cfs(DateUtils.today());
        try {
            //????????????csv??????
            File tempFile = createTempFile(userAddress, fileName);
            //??????csv????????????????????????????????????
            outCsvStream(response, tempFile);
            //??????????????????
            tempFile.delete();
        } catch (IOException e) {
            log.error("????????????",e);
            System.out.println("????????????");
        }

    }



    /**
     * ??????csv??????????????????
     */
    public void outCsvStream(HttpServletResponse response,File tempFile) throws IOException {
        java.io.OutputStream out = response.getOutputStream();
        byte[] b = new byte[10240];
        java.io.File fileLoad = new java.io.File(tempFile.getCanonicalPath());
        response.reset();
        response.setContentType("application/csv");
        response.setHeader("content-disposition", "attachment; filename=export.csv");
        java.io.FileInputStream in = new java.io.FileInputStream(fileLoad);
        int n;
        //????????????excel??????csv?????????????????????
        out.write(new   byte []{( byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });
        while ((n = in.read(b)) != -1) {
            //????????????out1024??????
            out.write(b, 0, n);
        }
        in.close();
        out.close();
    }

    /**
     * ???????????????csv??????
     * @return
     * @throws IOException
     */
    public File createTempFile(List<String> datas,String fileName) throws IOException {
        File tempFile = File.createTempFile(fileName, ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), '\n', Charset.forName("UTF-8"));
        // ?????????
        String[] headers = {"address"};
        csvWriter.writeRecord(headers);
        for (String item : datas) {
            //????????????????????????String????????????????????????
            csvWriter.writeRecord(new String[]{item});

        }
        csvWriter.close();
        return tempFile;
    }

    @ResponseBody
    @PostMapping(value = "/uploadAddress")
    public ApiResponse uploadAddress(@RequestParam("file") MultipartFile multipartFile,@RequestParam("projectId") Long projectId ) {

        try {
            //????????????????????????
            if (multipartFile.isEmpty()) {
                return ApiResponse.builder().success(true).msg("????????????????????????").build();
            }
            File file = CSVUtils.uploadFile(multipartFile);
            CsvReader csvReader = new CsvReader(file.getPath());
            // ?????????
            csvReader.readHeaders();
            List<String>  addressList=new ArrayList<>();
            while (csvReader.readRecord()){
                addressList.add(csvReader.getRawRecord());
            }
            csvReader.close();
            file.delete();
            //???????????????
           List<String> existAddress= projectAddressService.selectExistAddressList(projectId,addressList);
            List<String> saveList=addressList.stream().filter(t-> !existAddress.contains(t)).collect(Collectors.toList());
            List<AirProjectAddress> addresses=new ArrayList<>();
            for (String item : saveList) {
                AirProjectAddress airProjectAddress = new AirProjectAddress();
                airProjectAddress.setProjectId(projectId);
                airProjectAddress.setSourceType("????????????");
                airProjectAddress.setUserAdderss(item);
                airProjectAddress.setCreateTime(LocalDateTime.now());
                airProjectAddress.setUpdateTime(LocalDateTime.now());
                addresses.add(airProjectAddress);
            }
            projectAddressService.saveBatch(addresses);
            return ApiResponse.builder().success(true).msg("???????????????"+saveList.size()+"????????????:"+existAddress.size()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("?????????????????????",e);
        }
        return ApiResponse.builder().success(false).msg("?????????????????????????????????!!!").build();
    }

}
