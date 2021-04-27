package network.cycan.elpStatics.util;

import network.cycan.core.util.FastJsonUtil;
import network.cycan.core.util.HttpClientHelper;
import network.cycan.core.util.StringUtils;
import network.cycan.elpStatics.model.dto.ChainResultDto;
import network.cycan.elpStatics.model.dto.ChainLogDto;

public class BlockChainUtil {
    public  static  final String APIKEY="UVJIY1RQ348R5CJX9225SA8YIM95YMWA39";



    //获取链上区块数量
    public  static ChainResultDto getBlocknoByTime(String timestamp){
        String url=String.format("https://api.bscscan.com/api?module=block&action=getblocknobytime&closest=before&timestamp=%s&apikey=%s", timestamp,APIKEY);
        String strBlockModel= HttpClientHelper.get(url);
        if(StringUtils.isNotEmpty(strBlockModel)) {
            ChainResultDto dto = FastJsonUtil.getJsonToBean(strBlockModel, ChainResultDto.class);
            return dto;
        }
        return  null;
    }
    public static ChainLogDto  getLogs(String fromBlock,String toBlock,String address,String topic0)
    {
        String url=String.format("https://api.bscscan.com/api?module=block&action=getblocknobytime&closest=before&timestamp=%s&apikey=%s", fromBlock,toBlock,address,topic0,APIKEY);
        String strlogModel= HttpClientHelper.get(url);
        if(StringUtils.isNotEmpty(strlogModel)) {
            ChainLogDto dto=FastJsonUtil.getJsonToBean(strlogModel, ChainLogDto.class);
            return dto;
        }
        return null;
    }
    //获取区块之间的日志
    public static ChainResultDto getAccountBalance(String address)
    {
        String url=String.format("https://api.bscscan.com/api?module=account&action=balance&address=%s&tag=latest&apikey=%s",address,APIKEY);
        String strBlockModel= HttpClientHelper.get(url);
        if(StringUtils.isNotEmpty(strBlockModel)) {
            ChainResultDto dto = FastJsonUtil.getJsonToBean(strBlockModel, ChainResultDto.class);
            return dto;
        }
        return  null;
    }
}
