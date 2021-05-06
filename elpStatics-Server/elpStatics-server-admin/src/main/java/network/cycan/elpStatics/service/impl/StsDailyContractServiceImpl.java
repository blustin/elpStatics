package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import network.cycan.core.util.DateUtils;
import network.cycan.core.util.StringUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.dto.StsDailyDto;
import network.cycan.elpStatics.model.entity.StsDailyContract;
import network.cycan.elpStatics.mapper.StsDailyContractMapper;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.IStsDailyContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import network.cycan.elpStatics.service.ITransactionRecorkService;
import network.cycan.elpStatics.service.IUserBalanceService;
import network.cycan.elpStatics.util.BlockChainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
@Service
public class StsDailyContractServiceImpl extends ServiceImpl<StsDailyContractMapper, StsDailyContract> implements IStsDailyContractService {

    @Autowired
    private IUserBalanceService iUserBalanceService;
    @Autowired
    private ITransactionRecorkService iTransactionRecorkService;

    @Autowired
    private IBlockChainService iBlockChainService;

    @Autowired
    private StsDailyContractMapper stsDailyContractMapper;

    private final static DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(DateUtils.YYYY_MM_DD);
    @Override
    public void dailyStatic(Date today) {

        QueryWrapper<StsDailyContract> stsQueryWrapper=new QueryWrapper<>();
        stsQueryWrapper.eq("daily_str",DateUtils.dateToString(today,DateUtils.YYYY_MM_DD));
        this.remove(stsQueryWrapper);
        StsDailyContract stsDailyContract=new StsDailyContract();
        stsDailyContract.setDaily(DateUtils.asLocalDate(today));
        stsDailyContract.setDailyStr(DateUtils.dateToString(today,DateUtils.YYYY_MM_DD));
        //   * ELP持币地址数
        //  * ELP持币余额总数
        QueryWrapper <UserBalance> elpAddres=new QueryWrapper<>();
        elpAddres.select("count(userAddress) as userAccount,sum(balanceAmount) as balanceTotal  ");
        elpAddres.eq("userType","1");
        Map<String,Object> elpAddressMap=iUserBalanceService.getMap(elpAddres);
        Long elpUserAccount=Long.valueOf(String.valueOf(elpAddressMap.get("userAccount")));
        stsDailyContract.setElpAddressCount(elpUserAccount);
        BigDecimal elpBalanceTotal=new BigDecimal(String.valueOf( elpAddressMap.get("balanceTotal")));
        stsDailyContract.setElpTotalBalance(elpBalanceTotal);
        //   * LP持币地址数
        //  * LP持币余额总数
        QueryWrapper <UserBalance> lpAddres=new QueryWrapper<>();
        lpAddres.select("count(userAddress) as userAccount,sum(balanceAmount) as balanceTotal  ");
        lpAddres.eq("userType","2");
        Map<String,Object> lpAddressMap=iUserBalanceService.getMap(lpAddres);
        Long lpUserAccount=Long.valueOf(String.valueOf(lpAddressMap.get("userAccount")));
        stsDailyContract.setLpAddressCount(lpUserAccount);
        BigDecimal lpBalanceTotal=new BigDecimal(String.valueOf( lpAddressMap.get("balanceTotal")));
        stsDailyContract.setLpTotalBalance(lpBalanceTotal);
        // * 流动性挖矿合约中LP余额总数
        // * 流动性挖矿合约中LP余额总数所占百分比
     //elp总余额
         BigDecimal elpTotalBalance= iBlockChainService.getContractTotalBalance(BlockChainUtil.ELP_CONTRACT_ADDREES);
        //lp总余额
        BigDecimal lpTotalBalance= iBlockChainService.getContractTotalBalance(BlockChainUtil.LP_TOKEN_ADDRESS);
       //elp流动性总额
//        BigDecimal elpMovingTotalBalance= iBlockChainService.getMovingBalance(BlockChainUtil.ELP_CONTRACT_ADDREES,BlockChainUtil.MOVING_CONTRACT_ADDRESS);
        //lp流动性总额
        BigDecimal lpMovingTotalBalance= iBlockChainService.getMovingBalance(BlockChainUtil.LP_TOKEN_ADDRESS,BlockChainUtil.MOVING_CONTRACT_ADDRESS);
        //   BigDecimal elpRate=elpMovingTotalBalance.divide(elpTotalBalance);
        BigDecimal lpRate=lpMovingTotalBalance.divide(lpTotalBalance, 18, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        stsDailyContract.setMovingLpBalanceRate(lpRate);
        stsDailyContract.setMovingLpTotalBalance(lpMovingTotalBalance);
        stsDailyContract.setContractStatisticsId(UUIDUtils.randomUUID());
        this.save(stsDailyContract);

    }

    @Override
    public IPage<StsDailyContract> pageByCondition(StsDailyDto dto) {
        IPage<StsDailyContract> stsDailyContractPage=new Page<>();
        stsDailyContractPage.setSize(dto.getPageSize());
        stsDailyContractPage.setCurrent(dto.getPageNo());
        QueryWrapper<StsDailyContract> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getStartDate()))
        {
            queryWrapper.ge("daily",dto.getStartDate());
        }
        if(StringUtils.isNotEmpty(dto.getEndDate()))
        {
            queryWrapper.le("daily",dto.getEndDate());
        }
        queryWrapper.orderByDesc("daily");

        IPage<StsDailyContract> pageList= stsDailyContractMapper.selectPage(stsDailyContractPage,queryWrapper);
        return pageList;
    }
}
