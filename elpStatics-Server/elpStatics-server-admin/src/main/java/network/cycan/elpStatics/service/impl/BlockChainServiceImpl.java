package network.cycan.elpStatics.service.impl;
import network.cycan.elpStatics.service.IBlockChainService;

import java.math.BigInteger;
import java.util.Date;

public class BlockChainServiceImpl implements IBlockChainService {

    @Override
    public void saveTodayBlockData(Date dateTime) {
          //获取当前保存的区块数
            BigInteger  saveCurrent= BigInteger.valueOf(0);

          //获取截止当前时间点的公链的区  块数，如果当前区块数大于当前的保持的区块数则不执行
          //循环获取到的数据，先保存交易数据
          //

    }
}
