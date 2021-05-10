package network.cycan.elpStatics.util;

import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.*;
import network.cycan.elpStatics.model.dto.ChainResultDto;
import network.cycan.elpStatics.model.dto.ChainLogDto;
import network.cycan.elpStatics.model.dto.TransactionRecorkResultDto;
import network.cycan.elpStatics.model.dto.UserChainBalanceResultDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BlockChainUtil {
    public static final String APIKEY = "UVJIY1RQ348R5CJX9225SA8YIM95YMWA39";

    public static final String ELP_CONTRACT_ADDREES = "0xe3894cb9e92ca78524fb6a30ff072fa5e533c162";
    public static final String ELP_TRANSFER_TOPIC0 = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";

    public static final String LP_TOKEN_ADDRESS = "0xdae876044756b51e83b8bcecf810be56cfad05b1";
    public static final String LP_TOPIC0 = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";

    public static final String MOVING_CONTRACT_ADDRESS = "0xe62bb7f26588628229b7f184c4eb84c2baff299d";
    public static final String MOVING_CONTRACT_DEPOSIT = "0x90890809c654f11d6e72a28fa60149770a0d11ec6c92319d6ceb2bb0a4ea1a15";
    public static final String MOVING_CONTRACT_WITHDRAW = "0xf279e6a1f5e320cca91135676d9cb6e44ca8a08c0b88342bcdb1144f6511b568";
    public static final String MOVING_CONTRACT_EMERGENCYWITHDRAW = "0xbb757047c2b5f3974fe26b7c10f732e7bce710b0952a71082702781e62ae0595";

    public static  final BigDecimal RADIX_POINT=new BigDecimal(Math.pow(10,18));

    //获取链上区块数量
    public static ChainResultDto getBlocknoByTime(Long timestamp) {
        try {
            String url = String.format("https://api.bscscan.com/api?module=block&action=getblocknobytime&closest=before&timestamp=%s&apikey=%s", timestamp, APIKEY);
            String strBlockModel = HttpClientHelper.get(url);
            log.info("getBlocknoByTime==返回===" + strBlockModel);
            if (StringUtils.isNotEmpty(strBlockModel)) {
                ChainResultDto dto = FastJsonUtil.getJsonToBean(strBlockModel, ChainResultDto.class);
                if(null!=dto&&checkStatus(dto.getStatus())&& ValidateUtils.isZIndex(dto.getResult()))
                {
                    return dto;

                }else
                {
                    Date dt= DateUtils.parseDate(DateUtils.stampToDate(timestamp.toString()));
                    Date before2Hour= DateUtils.addHour(dt,-2);
                   return  getBlocknoByTime( DateUtils.convertDateTamsp(before2Hour.getTime()));
                }

            }
        }catch (Exception ex )
        {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static ChainLogDto getLogs(Long fromBlock, Long toBlock, String address, String topic0) {
        try {
            String url = String.format("https://api.bscscan.com/api?module=logs&action=getLogs&fromBlock=%s&toBlock=%s&address=%s&topic0=%s&apikey=%s", fromBlock, toBlock, address, topic0, APIKEY);
            String strlogModel = HttpClientHelper.get(url);
            log.info("getLogs==返回===" + strlogModel);
            if (StringUtils.isNotEmpty(strlogModel)) {
                ChainLogDto dto = FastJsonUtil.getJsonToBean(strlogModel, ChainLogDto.class);
                return dto;
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }




    public static TransactionRecorkResultDto getTransactionRecord(Long fromBlock, Long toBlock, String contractAddress) {
        String url = String.format("https://api.bscscan.com/api?module=account&action=tokentx&startblock=%s&endblock=%s&sort=asc&contractaddress=%s&apikey=%s", fromBlock, toBlock, contractAddress, APIKEY);
        try {
            String strlogModel = HttpClientHelper.get(url);

            log.info("getTransactionRecord==返回===" + strlogModel);
            if (StringUtils.isNotEmpty(strlogModel)) {
                TransactionRecorkResultDto dto = FastJsonUtil.getJsonToBean(strlogModel, TransactionRecorkResultDto.class);
                return dto;
            }
        }catch (Exception ex)
        {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    //获取区块之间的日志
    public static ChainResultDto getAccountBalance(String contractAddress,String address) {
        try{
        String url = String.format("https://api.bscscan.com/api?module=account&action=tokenbalance&contractaddress=%s&address=%s&tag=latest&apikey=%s", contractAddress,address, APIKEY);
        String strBlockModel = HttpClientHelper.get(url);
        log.info("getAccountBalance==返回==="+strBlockModel);
        if (StringUtils.isNotEmpty(strBlockModel)) {
            ChainResultDto dto = FastJsonUtil.getJsonToBean(strBlockModel, ChainResultDto.class);
            return dto;
        }
    }catch (Exception ex)
    {
        log.error(ex.getMessage());
        ex.printStackTrace();
    }
        return null;
    }

    public static ChainResultDto getContractBalance(String contractAddress) {
        try{
        String url = String.format("https://api.bscscan.com/api?module=stats&action=tokenCsupply&contractaddress=%s&apikey=%s", contractAddress, APIKEY);
        String strBlockModel = HttpClientHelper.get(url);
        log.info("getAccountBalance==返回==="+strBlockModel);
        if (StringUtils.isNotEmpty(strBlockModel)) {
            ChainResultDto dto = FastJsonUtil.getJsonToBean(strBlockModel, ChainResultDto.class);
            return dto;
        }
    }catch (Exception ex)
    {
        log.error(ex.getMessage());
        ex.printStackTrace();
    }
        return null;
    }



    //获取用户余额
    public static UserChainBalanceResultDto getBatchAccountBalance(List<String> addressList) {
        String strAddress=String.join(",",addressList);
        String url="https://api.bscscan.com/api";
        HashMap<String,String>   hashMap=new HashMap<>();
        hashMap.put("module","account");
        hashMap.put("action","balancemulti");
        hashMap.put("tag","latest");
        hashMap.put("apikey",APIKEY);
        hashMap.put("address",strAddress);
        String strBlockModel = HttpClientHelper.doPost(url,hashMap,"utf-8");
        log.info("strBlockModel==返回==="+strBlockModel);
        try {
            if (StringUtils.isNotEmpty(strBlockModel)) {
                UserChainBalanceResultDto dto = FastJsonUtil.getJsonToBean(strBlockModel, UserChainBalanceResultDto.class);
                return dto;
            }
        }catch ( Exception exception)
        {
            log.error(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public static boolean checkStatus(String status) {
        if ("1".equals(status)) {
            return true;
        }
        return false;
    }
}
