package network.cycan.elpStatics.model.dto;

import network.cycan.elpStatics.util.BlockChainUtil;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserChainBalanceDto implements Serializable  {

    private String account;
    private String balance;

    public BigDecimal getUserBalance()
    {
        return new BigDecimal( balance).divide(BlockChainUtil.RADIX_POINT);
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


}
