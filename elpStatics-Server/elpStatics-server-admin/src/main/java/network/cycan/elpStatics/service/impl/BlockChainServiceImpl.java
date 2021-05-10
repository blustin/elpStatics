package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.DateUtils;
import network.cycan.core.util.StringUtils;
import network.cycan.core.util.UUIDUtils;
import network.cycan.elpStatics.model.dto.*;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.ITransactionRecorkService;
import network.cycan.elpStatics.service.IUserBalanceService;
import network.cycan.elpStatics.util.BlockChainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.ArrayUtil;


import java.math.BigDecimal;
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

    @Override
    public void saveTodayBlockData(Date dateTime, String chainAddress, String userType) {
        //获取当前保存的区块数
        Long fromBlockNo = iUserBalanceService.getMaxBlockNo(userType);
        if (fromBlockNo < 50000) {
            fromBlockNo = 0L;
        }
        Long timestamp = dateTime.getTime() / 1000;
        //获取截止当前时间点的公链的区  块数，如果当前区块数大于当前的保持的区块数则不执行
        ChainResultDto chainResultDto = BlockChainUtil.getBlocknoByTime(timestamp);
        if (chainResultDto != null && BlockChainUtil.checkStatus(chainResultDto.getStatus())) {

            if (Long.parseLong(chainResultDto.getResult()) > fromBlockNo) {
                Long targetBlockNo = Long.parseLong(chainResultDto.getResult());
                HashMap<String, Long> userAddress = new HashMap<String, Long>();
                for (Long startBlockNo = fromBlockNo; startBlockNo < targetBlockNo; ) {
                    Long subBlockIndex = startBlockNo + 100000;
                    try {
                        log.info("subBlockIndex=======" + subBlockIndex);
                        TransactionRecorkResultDto transactionRecordResult = BlockChainUtil.getTransactionRecord(startBlockNo, subBlockIndex, chainAddress);
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
                            List<TransactionRecork> distinctByUniqueList = recorkList.stream()
                                    .filter(item -> !savedRecorks.stream().map(e -> e.getHashCode()).
                                            collect(Collectors.toList()).contains(item.getHashCode()))
                                    .collect(Collectors.toList());
                            iTransactionRecorkService.saveBatch(distinctByUniqueList);
                        }
                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                        ex.printStackTrace();
                    }
                    startBlockNo = startBlockNo + 100000;
                }

                List<String> mList = new ArrayList<>(userAddress.keySet());
                try {
                    if (mList != null && !mList.isEmpty()) {
                        for (int index = 0; index < mList.size(); index++) {
                            try {
                                log.info("index=======" + index);
                                String userItem = mList.get(index);
                                //判断是否存在当前块
                                QueryWrapper<UserBalance> userBalanceQueryWrapper=new QueryWrapper<>();
                                userBalanceQueryWrapper.eq("userAddress",userItem);
                                userBalanceQueryWrapper.eq("userType",userType);
                                userBalanceQueryWrapper.eq("blockNum",userAddress.get(userItem));
                                int saveCount=  iUserBalanceService.count(userBalanceQueryWrapper);
                                if(saveCount>0) {
                                    continue;
                                }
                                ChainResultDto dto = BlockChainUtil.getAccountBalance(chainAddress, userItem);
                                UserBalance userBalance = new UserBalance();
                                userBalance.setUpdateTime(LocalDateTime.now());
                                userBalance.setCreateTime(LocalDateTime.now());
                                userBalance.setUserAddress(userItem);
                                userBalance.setBalanceAmount(new BigDecimal(dto.getResult()).divide(BlockChainUtil.RADIX_POINT));
                                userBalance.setBlockNum(userAddress.get(userItem));
                                userBalance.setUserBalanceId(UUIDUtils.randomUUID());
                                userBalance.setUserType(userType);
                                UpdateWrapper<UserBalance> updateWrapper = new UpdateWrapper<UserBalance>();
                                updateWrapper = updateWrapper.eq("userAddress", userItem);
                                updateWrapper = updateWrapper.eq("userType", userType);
                                iUserBalanceService.saveOrUpdate(userBalance, updateWrapper);
                            } catch (Exception ex) {
                                log.error(ex.getMessage());
                                ex.printStackTrace();
                            }

                        }
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                    log.error(ex.getMessage());
                }
            }
        }


    }

    @Override
    public  BigDecimal getContractTotalBalance(String contractAddress)
    {
        ChainResultDto resultDto=   BlockChainUtil.getContractBalance(contractAddress);
        return new BigDecimal(resultDto.getResult()).divide(BlockChainUtil.RADIX_POINT);
    }
    @Override
    public  BigDecimal getMovingBalance(String contractAddress,String address)
    {
        ChainResultDto dto = BlockChainUtil.getAccountBalance(contractAddress,address);
        return new BigDecimal(dto.getResult()).divide(BlockChainUtil.RADIX_POINT);
    }
}
