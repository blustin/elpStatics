package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import network.cycan.common.apiInfor.ApiResponse;
import network.cycan.core.util.StringUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.dto.AirProjectDto;
import network.cycan.elpStatics.model.entity.AirProject;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.service.IAirProjectAddressService;
import network.cycan.elpStatics.service.IAirProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private IAirProjectService iAirProjectService;


    @RequestMapping("/pageList")
    public String  projectList(HttpServletRequest request, Model model,   AirProjectDto dto){
        IPage<AirProject> pageList =  iAirProjectService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "project/projectList";
    }
    @ResponseBody
    @RequestMapping("/edit")
    public ApiResponse edit(HttpServletRequest request, Model model, @RequestBody AirProject project)
    {
        AirProject airProject=null;
        if(project.getId()!=null&&project.getId()>0){
            airProject=   iAirProjectService.getById(project.getId());
            airProject.setUpdateTime(LocalDateTime.now());
            airProject.setProjectName(project.getProjectName());
            airProject.setRemark(project.getRemark());
            airProject.setUpdateUserName(getUser().getUserName());
            airProject.setUpdateUserId(getUser().getUserId());

        }else
        {
            airProject=new AirProject();
            airProject.setProjectName(project.getProjectName());
            airProject.setProjectId(UUIDUtils.randomUUID());
            airProject.setRemark(project.getRemark());
            airProject.setProjectId(UUIDUtils.randomUUID());
            airProject.setCreateTime(LocalDateTime.now());
            airProject.setUpdateTime(LocalDateTime.now());
            airProject.setUpdateUserId(getUser().getUserId());
            airProject.setUpdateUserName(getUser().getUserName());
            airProject.setCreateUserId(getUser().getUserId());
            airProject.setCreateUserName(getUser().getUserName());

        }
        iAirProjectService.saveOrUpdate(airProject);
        return ApiResponse.builder().success(true).build();

    }
    @ResponseBody
    @RequestMapping("/deleteById")
    public ApiResponse delete(HttpServletRequest request, Model model,@RequestParam(value = "id") String id)
    {
        if(StringUtils.isEmpty(id)) {
            return ApiResponse.builder().success(false).msg("参数不合法").build();
        }

        boolean flag=  iAirProjectService.removeById(id);
        if(!flag)
        {
            return ApiResponse.builder().success(false).msg("数据不存在！").build();
        }
        return ApiResponse.builder().success(true).build();
    }

}
