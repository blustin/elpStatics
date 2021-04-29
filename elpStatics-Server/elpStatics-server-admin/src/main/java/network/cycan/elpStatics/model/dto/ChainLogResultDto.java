package network.cycan.elpStatics.model.dto;

import network.cycan.core.util.StringUtils;

import java.io.Serializable;
import java.util.List;

public class ChainLogResultDto implements Serializable {
    private static final long serialVersionUID = -6957361951748382519L;
    private String address;
    private List<String> topics;
    private String data;
    private String blockNumber;
    private String timeStamp;
    private String gasPrice;
    private String gasUsed;
    private String logIndex;
    private String transactionHash;
    private String transactionIndex;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTopics() {
        return topics;
    }

    public String getUserAddress()
    {
        String userAddress="0x"+this.topics.get(1).substring(26,40);
        return userAddress;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBlockNumber() {
        return blockNumber;
    }
    public Long getBlockNumberTEN()
    {
       return Long.parseLong(blockNumber.substring(2),16);
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
    public Long getTimeStampTEN()
    {
        return Long.parseLong(timeStamp.substring(2),16);
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getLogIndex() {
        return logIndex;
    }

    public void setLogIndex(String logIndex) {
        this.logIndex = logIndex;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(String transactionIndex) {
        this.transactionIndex = transactionIndex;
    }


}
