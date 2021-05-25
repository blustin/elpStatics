package network.cycan.elpStatics.controller;

import network.cycan.common.apiInfor.ApiResponse;
import network.cycan.elpStatics.model.entity.SysUser;
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
    @ResponseBody
    @RequestMapping(value = "/updateUserPwd", method = RequestMethod.POST)
    public ApiResponse updateUserPwd(
            HttpServletRequest request, Model model,@RequestBody Map<String, String> map) {
        String id=map.get("userId");
        String oldPwd=map.get("oldPwd");
        String newPwd=map.get("newPwd");
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
        return  ApiResponse.builder().success(true).build();
    }

}
