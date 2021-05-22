package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
public class ProjectAddressController {

    @Autowired
    private IAirProjectAddressService projectAddressService;
    @RequestMapping("/pageProjectAddressList")
    public String  projectList(HttpServletRequest request, Model model, AirProjectAddressDto dto){
        IPage<AirProjectAddress> pageList =  projectAddressService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "project/addressList";
    }
}
