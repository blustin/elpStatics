package network.cycan.elpStatics;

import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.DateUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.enums.ChainContractType;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.IUserBalanceService;
import network.cycan.elpStatics.util.BlockChainUtil;
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
    @Autowired
    private IBlockChainService iBlockChainService;
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
    @Test
    public  void testBlockChainService()
    {
     iBlockChainService.saveTodayBlockData(DateUtils.today(), BlockChainUtil.ELP_CONTRACT_ADDREES, ChainContractType.ELP.getType());
    iBlockChainService.saveTodayBlockData(DateUtils.today(), BlockChainUtil.LP_TOKEN_ADDRESS, ChainContractType.LP.getType());
      //  iBlockChainService.saveTodayBlockData(DateUtils.today(), BlockChainUtil.MOVING_CONTRACT_ADDRESS, ChainContractType.Moving.getType());
    }


}
