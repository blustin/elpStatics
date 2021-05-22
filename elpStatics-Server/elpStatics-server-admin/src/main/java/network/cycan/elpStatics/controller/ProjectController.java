package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import network.cycan.elpStatics.model.dto.AirProjectDto;
import network.cycan.elpStatics.model.entity.AirProject;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.service.IAirProjectAddressService;
import network.cycan.elpStatics.service.IAirProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private IAirProjectService iAirProjectService;

    @RequestMapping("/pageList")
    public String  projectList(HttpServletRequest request, Model model, AirProjectDto dto){
        IPage<AirProject> pageList =  iAirProjectService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "project/projectList";
    }
}
