package network.cycan.elpStatics.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.common.apiInfor.ApiResponse;
import network.cycan.core.util.StringUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.dto.SysUserDto;
import network.cycan.elpStatics.model.dto.UserDto;
import network.cycan.elpStatics.model.entity.AirProject;
import network.cycan.elpStatics.model.entity.SysUser;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/sysUser")
public class UserController extends BaseController {

    @Autowired
    private ISysUserService iSysUserService;

    @RequestMapping("/pageList")
    public String list(HttpServletRequest request, Model model, SysUserDto dto) {
        IPage<SysUser> pageList = iSysUserService.pageByCondition(dto);
        model.addAttribute("pageList", pageList);
        model.addAttribute("dto", dto);
        return "sysUser/pageList";
    }


    @ResponseBody
    @RequestMapping(value = "/updateUserPwd", method = RequestMethod.POST)
    public ApiResponse updateUserPwd(
            HttpServletRequest request, Model model, @RequestBody Map<String, String> map) {
        String id = map.get("userId");
        String oldPwd = map.get("oldPwd");
        String newPwd = map.get("newPwd");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        SysUser user = iSysUserService.getById(id);
        if (user == null) {
            return ApiResponse.builder().success(false).msg("数据不存在！").build();
        }
        if (!user.getUserPwd().equals(encoder.encode(oldPwd))) {
            return ApiResponse.builder().success(false).msg("原密码错误！").build();
        }
        String enPassword = encoder.encode(newPwd);
        user.setUserPwd(enPassword);
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser(getUser().getUpdateUser());
        iSysUserService.updateById(user);
        return ApiResponse.builder().success(true).build();
    }

    @ResponseBody
    @RequestMapping("/edit")
    public ApiResponse edit(HttpServletRequest request, Model model, @RequestBody SysUser user) {
        SysUser sysUser = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        if (user.getId() != null && user.getId() > 0) {
            sysUser = iSysUserService.getById(user.getId());
            sysUser.setUpdateUser(getUser().getUserName());
            sysUser.setUpdateTime(LocalDateTime.now());
            sysUser.setRemark(user.getRemark());
            sysUser.setNickName(user.getNickName());
            if (StringUtils.isNotEmpty(user.getUserPwd())) {
                sysUser.setUserPwd(encoder.encode(user.getUserPwd()));
            }
            iSysUserService.updateById(sysUser);
        } else {
            sysUser = new SysUser();
            SysUser user1 = iSysUserService.findByUsername(sysUser.getUserName());
            if (user1 != null) {
                return ApiResponse.builder().success(false).msg("用户名不能重复").build();
            }
            sysUser.setUserId(UUIDUtils.randomUUID());
            sysUser.setNickName(user.getNickName());
            sysUser.setRemark(user.getRemark());
            sysUser.setCreateUser(getUser().getUserName());
            sysUser.setUpdateUser(getUser().getUserName());
            sysUser.setUserName(user.getUserName());
            sysUser.setUserPwd(encoder.encode(user.getUserPwd()));
            sysUser.setCreateTime(LocalDateTime.now());
            sysUser.setUpdateTime(LocalDateTime.now());
            iSysUserService.save(sysUser);
        }
        return ApiResponse.builder().success(true).build();

    }

    @ResponseBody
    @RequestMapping("/deleteById")
    public ApiResponse deleteById(HttpServletRequest request, Model model, @RequestParam(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ApiResponse.builder().success(false).msg("参数不合法").build();
        }
        boolean flag = iSysUserService.removeById(id);
        if (!flag) {
            return ApiResponse.builder().success(false).msg("数据不存在！").build();
        }
        return ApiResponse.builder().success(true).build();
    }


}
