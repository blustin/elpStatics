package network.cycan.elpStatics.service;

import java.math.BigDecimal;
import java.util.Date;

public interface IBlockChainService {

      void saveTodayBlockData(Date dateTime,String chainAddress,String userType);

      BigDecimal getContractTotalBalance(String contractAddress);

      BigDecimal getMovingBalance(String contractAddress,String address);

      void  saveMovingBalance( Date date);
}
