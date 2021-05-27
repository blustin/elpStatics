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

        airProjectAddress.setSourceType("手动输入");
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
     * @Description 下载CSV
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
            //创建临时csv文件
            File tempFile = createTempFile(userAddress, fileName);
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile);
            //删除临时文件
            tempFile.delete();
        } catch (IOException e) {
            log.error("导出失败",e);
            System.out.println("导出失败");
        }

    }



    /**
     * 写入csv结束，写出流
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
        //为了保证excel打开csv不出现中文乱码
        out.write(new   byte []{( byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });
        while ((n = in.read(b)) != -1) {
            //每次写入out1024字节
            out.write(b, 0, n);
        }
        in.close();
        out.close();
    }

    /**
     * 创建临时的csv文件
     * @return
     * @throws IOException
     */
    public File createTempFile(List<String> datas,String fileName) throws IOException {
        File tempFile = File.createTempFile(fileName, ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), '\n', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"address"};
        csvWriter.writeRecord(headers);
        for (String item : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.writeRecord(new String[]{item});

        }
        csvWriter.close();
        return tempFile;
    }

    @ResponseBody
    @PostMapping(value = "/uploadAddress")
    public ApiResponse uploadAddress(@RequestParam("file") MultipartFile multipartFile,@RequestParam("projectId") Long projectId ) {

        try {
            //上传内容不能为空
            if (multipartFile.isEmpty()) {
                return ApiResponse.builder().success(true).msg("上传内容不能为空").build();
            }
            File file = CSVUtils.uploadFile(multipartFile);
            CsvReader csvReader = new CsvReader(file.getPath());
            // 读表头
            csvReader.readHeaders();
            List<String>  addressList=new ArrayList<>();
            while (csvReader.readRecord()){
                addressList.add(csvReader.getRawRecord());
            }
            csvReader.close();
            file.delete();
            //过滤重复的
           List<String> existAddress= projectAddressService.selectExistAddressList(projectId,addressList);
            List<String> saveList=addressList.stream().filter(t-> !existAddress.contains(t)).collect(Collectors.toList());
            List<AirProjectAddress> addresses=new ArrayList<>();
            for (String item : saveList) {
                AirProjectAddress airProjectAddress = new AirProjectAddress();
                airProjectAddress.setProjectId(projectId);
                airProjectAddress.setSourceType("文件导入");
                airProjectAddress.setUserAdderss(item);
                airProjectAddress.setCreateTime(LocalDateTime.now());
                airProjectAddress.setUpdateTime(LocalDateTime.now());
                addresses.add(airProjectAddress);
            }
            projectAddressService.saveBatch(addresses);
            return ApiResponse.builder().success(true).msg("成功条数："+saveList.size()+"失败条数:"+existAddress.size()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件导入异常！",e);
        }
        return ApiResponse.builder().success(false).msg("系统异常，请联系管理员!!!").build();
    }

}
