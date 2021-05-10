package network.cycan.elpStatics;

import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.DateUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.enums.ChainContractType;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.IStsDailyContractService;
import network.cycan.elpStatics.service.IUserBalanceService;
import network.cycan.elpStatics.util.HttpBlockChainUtil;
import network.cycan.elpStatics.util.RpcBlockChainUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
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
    @Autowired
    private IStsDailyContractService iStsDailyContractService;
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
        log.info("定时任务启动====="+DateUtils.getNowTime(DateUtils.YYYY_MM_DD_HH_MM_SS));
        try
        {
            iBlockChainService.saveTodayBlockData(DateUtils.today(), HttpBlockChainUtil.ELP_CONTRACT_ADDREES, ChainContractType.ELP.getType());
            iBlockChainService.saveTodayBlockData(DateUtils.today(), HttpBlockChainUtil.LP_TOKEN_ADDRESS, ChainContractType.LP.getType());
            iStsDailyContractService.dailyStatic(DateUtils.today());
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
        }

        log.info("定时任务结束====="+DateUtils.getNowTime(DateUtils.YYYY_MM_DD_HH_MM_SS));
    }

    @Test
    public  void stsBlockChainService()
    {
        iStsDailyContractService.dailyStatic(DateUtils.today());
    }

    @Test
    public void testgetBalance()throws IOException {
        RpcBlockChainUtil.getBalance("0x6f93A648e39BE8f57ceb90a91ea6688F986B0DeF",HttpBlockChainUtil.MOVING_CONTRACT_ADDRESS);
    }

}
