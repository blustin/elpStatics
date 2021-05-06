package network.cycan.elpStatics.model.dto;

import network.cycan.core.page.PageNoSize;

import java.io.Serializable;

public class TransactionDto  extends PageNoSize implements Serializable {


    private static final long serialVersionUID = 610394620613241027L;
    private String startDate;
    private String endDate;
    private String fromAddress;
    private String toAddress;
    private  String fromAmount;
    private String toAmount;


    public String getTranactionType() {
        return tranactionType;
    }

    public void setTranactionType(String tranactionType) {
        this.tranactionType = tranactionType;
    }

    private String tranactionType;
    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }



    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }


}
