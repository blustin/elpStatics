package network.cycan.elpStatics.job;

import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.DateUtils;
import network.cycan.elpStatics.enums.ChainContractType;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.IStsDailyContractService;
import network.cycan.elpStatics.util.BlockChainUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Slf4j
@Component
public class StartOfDayJob extends QuartzJobBean {
    @Autowired
    private IBlockChainService iBlockChainService;
    @Autowired
    private IStsDailyContractService iStsDailyContractService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        log.info("定时任务启动====="+DateUtils.getNowTime(DateUtils.YYYY_MM_DD_HH_MM_SS));
        try
        {

            iBlockChainService.saveTodayBlockData(DateUtils.today(), BlockChainUtil.ELP_CONTRACT_ADDREES, ChainContractType.ELP.getType());
            iBlockChainService.saveTodayBlockData(DateUtils.today(), BlockChainUtil.LP_TOKEN_ADDRESS, ChainContractType.LP.getType());
            iStsDailyContractService.dailyStatic(DateUtils.today());
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
        }

        log.info("定时任务结束====="+DateUtils.getNowTime(DateUtils.YYYY_MM_DD_HH_MM_SS));

    }
}
