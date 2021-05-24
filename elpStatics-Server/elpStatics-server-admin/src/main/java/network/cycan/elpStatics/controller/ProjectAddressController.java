package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import network.cycan.common.apiInfor.ApiResponse;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.dto.AirProjectAddressDto;
import network.cycan.elpStatics.model.dto.AirProjectDto;
import network.cycan.elpStatics.model.entity.AirProject;
import network.cycan.elpStatics.model.entity.AirProjectAddress;
import network.cycan.elpStatics.service.IAirProjectAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
public class ProjectAddressController extends BaseController {

    @Autowired
    private IAirProjectAddressService projectAddressService;
    @RequestMapping("/pageProjectAddressList")
    public String  projectList(HttpServletRequest request, Model model, AirProjectAddressDto dto){
        IPage<AirProjectAddress> pageList =  projectAddressService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "project/addressList";
    }

    @RequestMapping("/add")
    public ApiResponse add(HttpServletRequest request, Model model, AirProjectAddress project)
    {

        project.setSourceType("手动输入");
        project.setCreateTime(LocalDateTime.now());
        project.setUpdateTime(LocalDateTime.now());
        projectAddressService.save(project);
        return ApiResponse.builder().success(true).build();

    }
    @RequestMapping("/deleteBacth")
    public ApiResponse deleteBacth(HttpServletRequest request, Model model, List<String> ids)
    {
        projectAddressService.removeByIds(ids);
        return ApiResponse.builder().success(true).build();
    }
    @RequestMapping("/importProjectAddess")
    public  ApiResponse importProjectAddess(HttpServletRequest request,String projectId){
        return ApiResponse.builder().success(true).build();
    }
}
