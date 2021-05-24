package network.cycan.elpStatics;


import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.entity.SysUser;
import network.cycan.elpStatics.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestEncoder {

    @Autowired
    private ISysUserService iSysUserService;
    @Test
    public void encoder() {
        String password = "admin";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String enPassword = encoder.encode(password);
        SysUser sysUser=new SysUser();
        sysUser.setUserPwd(enPassword);
        sysUser.setNickName("admin");
        sysUser.setUserId(UUIDUtils.randomUUID());
        sysUser.setUserName("admin");
        sysUser.setRemark("admin" );
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUser.setUpdateUser("admin");
        sysUser.setCreateUser("admin");
        iSysUserService.save(sysUser);
        System.out.println(enPassword);
    }

}
