package network.cycan.elpStatics.service;

import java.math.BigDecimal;
import java.util.Date;

public interface IBlockChainService {

      boolean saveTodayBlockData(Date dateTime,String chainAddress,String userType);

      BigDecimal getContractTotalBalance(String contractAddress);

      BigDecimal getMovingBalance(String contractAddress,String address);

      boolean  saveMovingBalance( Date date);
}
