package network.cycan.elpStatics;

import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.IUserBalanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
    @Autowired
    private IUserBalanceService iUserBalanceService;

    @Test
    public void AddUser()
    {
        UserBalance userBalance=new UserBalance();
        userBalance.setBalanceAmount(new BigDecimal(2.1));
        userBalance.setUserType("1");
        userBalance.setUserAddress(UUIDUtils.randomUUID());
        userBalance.setCreateTime(LocalDateTime.now());
        userBalance.setUpdateTime(LocalDateTime.now());
        iUserBalanceService.save(userBalance);

    }
}
