package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.DateUtils;
import network.cycan.core.util.ListUtils;
import network.cycan.core.util.StringUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.enums.ChainContractType;
import network.cycan.elpStatics.model.dto.*;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.ITransactionRecorkService;
import network.cycan.elpStatics.service.IUserBalanceService;
import network.cycan.elpStatics.util.HttpBlockChainUtil;
import network.cycan.elpStatics.util.RpcBlockChainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlockChainServiceImpl implements IBlockChainService {
    @Autowired
    private IUserBalanceService iUserBalanceService;
    @Autowired
    private ITransactionRecorkService iTransactionRecorkService;


    private static boolean JOB_ELP_RUNING_FLAG=false;
    private static boolean JOB_MOVING_RUNNING_FLAG=false;
    @Override
    public boolean saveTodayBlockData(Date dateTime, String chainAddress, String userType) {
        if(JOB_ELP_RUNING_FLAG) {
                return false;
        }
        JOB_ELP_RUNING_FLAG=true;
        //获取当前保存的区块数
        Long fromBlockNo = iUserBalanceService.getMaxBlockNo(userType);
        Long timestamp = dateTime.getTime() / 1000;
        //获取截止当前时间点的公链的区  块数，如果当前区块数大于当前的保持的区块数则不执行
        ChainResultDto chainResultDto = HttpBlockChainUtil.getBlocknoByTime(timestamp);
        if (chainResultDto != null && HttpBlockChainUtil.checkStatus(chainResultDto.getStatus())) {
            if (Long.parseLong(chainResultDto.getResult()) > fromBlockNo) {
                Long targetBlockNo = Long.parseLong(chainResultDto.getResult());
                HashMap<String, Long> userAddress = saveTractionRecordList(chainAddress, userType, fromBlockNo, targetBlockNo);
                saveUserBalance(chainAddress, userType, userAddress);
            }
        }
        JOB_ELP_RUNING_FLAG=false;
        return true;
    }

    private void saveUserBalance(String chainAddress, String userType, HashMap<String, Long> userAddress) {
        List<String> mList = new ArrayList<>(userAddress.keySet());
        List<UserBalance> updateList=new ArrayList<>();
        List<UserBalance> saveList=new ArrayList<>();
        int index=0;
        try {
            for (String userItem : mList) {
                try {
                    log.info("index=======" + index+"==size==="+mList.size());
                    //判断是否存在当前块
                    QueryWrapper<UserBalance> userBalanceQueryWrapper=new QueryWrapper<>();
                    userBalanceQueryWrapper.eq("userAddress",userItem);
                    userBalanceQueryWrapper.eq("userType",userType);
                    UserBalance userBalance = iUserBalanceService.getOne(userBalanceQueryWrapper);
                    if(userBalance==null) {
                        userBalance = new UserBalance();
                        userBalance.setCreateTime(LocalDateTime.now());
                        userBalance.setUserAddress(userItem);
                        userBalance.setUserType(userType);
                        userBalance.setUserBalanceId(UUIDUtils.randomUUID());
                    }
                    ChainResultDto dto = HttpBlockChainUtil.getAccountBalance(chainAddress, userItem);
                    userBalance.setUpdateTime(LocalDateTime.now());
                    userBalance.setBalanceAmount(new BigDecimal(dto.getResult()).divide(HttpBlockChainUtil.RADIX_POINT));
                    userBalance.setBlockNum(userAddress.get(userItem));
                    iUserBalanceService.saveOrUpdate(userBalance);
                    index++;
                }catch (Exception ex){
                    log.error(ex.getMessage(),ex);
                    ex.printStackTrace();
                }
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
    }

    private HashMap<String, Long>  saveTractionRecordList(String chainAddress, String userType, Long fromBlockNo, Long targetBlockNo) {

        HashMap<String, Long> userAddress =new HashMap<>();
        for (Long startBlockNo = fromBlockNo; startBlockNo < targetBlockNo; ) {
            Long subBlockIndex = startBlockNo + 100000;
            try {
                log.info("subBlockIndex=======" + subBlockIndex);
                TransactionRecorkResultDto transactionRecordResult = HttpBlockChainUtil.getTransactionRecord(startBlockNo, subBlockIndex, chainAddress);
                List<TransactionRecork> recorkList = new ArrayList<>();
                List<String> hashList = new ArrayList<>();
                //循环获取到的数据，先保存交易数据
                for (TransactionRecorkDto log : transactionRecordResult.getResult()) {
                    TransactionRecork recork = new TransactionRecork();
                    recork.setCreateTime(LocalDateTime.now());
                    recork.setUpateTime(LocalDateTime.now());
                    recork.setTransactionId(UUIDUtils.randomUUID());
                    recork.setTransactionTime(LocalDateTime.ofEpochSecond(log.getTimeStampTEN(), 0, ZoneOffset.ofHours(8)));
                    recork.setTransactionAmount(log.getTrasactionAmount());
                    recork.setFromUserAddress(log.getFrom());
                    recork.setToUserAddress(log.getTo());
                    recork.setHashCode(log.getHash());
                    recork.setTranactionType(userType);
                    recorkList.add(recork);
                    hashList.add(log.getHash());
                    if (StringUtils.isNotEmpty(log.getFrom())) {
                        userAddress.put(log.getFrom(), log.getBlockNumberTEN());
                    }
                    if (StringUtils.isNotEmpty(log.getTo())) {
                        userAddress.put(log.getTo(), log.getBlockNumberTEN());
                    }
                }
                if (hashList.size() > 0) {
                    QueryWrapper<TransactionRecork> recorkWrapper = new QueryWrapper<>();
                    recorkWrapper.in("hashCode", hashList);
                    List<TransactionRecork> savedRecorks = iTransactionRecorkService.list(recorkWrapper);
                    List<TransactionRecork> distinctByUniqueList = recorkList.stream().filter(item -> !savedRecorks.stream().map(e -> e.getHashCode()).collect(Collectors.toList()).contains(item.getHashCode()))                            .collect(Collectors.toList());
                    iTransactionRecorkService.saveBatch(distinctByUniqueList);
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(),ex);
                ex.printStackTrace();
            }
            startBlockNo = startBlockNo + 100000;

        }
        return userAddress;
    }

    @Override
    public  BigDecimal getContractTotalBalance(String contractAddress)
    {
        ChainResultDto resultDto=   HttpBlockChainUtil.getContractBalance(contractAddress);
        return new BigDecimal(resultDto.getResult()).divide(HttpBlockChainUtil.RADIX_POINT);
    }
    @Override
    public  BigDecimal getMovingBalance(String contractAddress,String address)
    {
        ChainResultDto dto = HttpBlockChainUtil.getAccountBalance(contractAddress,address);
        return new BigDecimal(dto.getResult()).divide(HttpBlockChainUtil.RADIX_POINT);
    }

    @Override
    public boolean saveMovingBalance(Date date) {
        Date beforeDate= DateUtils.addDay(date,-1);
        if(JOB_MOVING_RUNNING_FLAG) {
            return false;
        }
        try {
            QueryWrapper<UserBalance> queryWrapper = new QueryWrapper<>();
            queryWrapper.gt("updateTime", beforeDate);
            queryWrapper.le("updateTime", date);
            queryWrapper.eq("userType", ChainContractType.ELP.getType());
            queryWrapper.select(" distinct userAddress,blockNum ");
            List<UserBalance> needUpdateList = iUserBalanceService.list(queryWrapper);
            int index = 0;
            for (UserBalance item : needUpdateList) {
                String userAddress=item.getUserAddress();
                try {
                    QueryWrapper<UserBalance> movingWrapper = new QueryWrapper<>();
                    movingWrapper.eq("userType", ChainContractType.Moving.getType());
                    movingWrapper.eq("userAddress", userAddress);
                    UserBalance one = iUserBalanceService.getOne(movingWrapper);
                    BigDecimal bigDecimal = RpcBlockChainUtil.getBalance(userAddress, HttpBlockChainUtil.MOVING_CONTRACT_ADDRESS);
                    if (one == null) {
                        one = new UserBalance();
                        one.setCreateTime(LocalDateTime.now());
                        one.setUserAddress(userAddress);
                        one.setUserBalanceId(UUIDUtils.randomUUID());
                        one.setUserType(ChainContractType.Moving.getType());
                    }
                    one.setBalanceAmount(bigDecimal);
                    one.setBlockNum(item.getBlockNum());
                    one.setUpdateTime(LocalDateTime.now());
                    iUserBalanceService.saveOrUpdate(one);
                    log.info("saveMovingBalance:index===" + index++ + "=====size==" + needUpdateList.size());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    log.error("saveMovingBalance===" + ex.getMessage(),ex);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("saveMovingBalance===" + ex.getMessage(),ex);
        }
        JOB_MOVING_RUNNING_FLAG= false;
        return true;
    }
}
