package network.cycan.elpStatics.model.dto;

import java.math.BigInteger;

public class ChainResultDto {
    //{"status":"1","message":"OK","result":"6868852"}
    private  String status;
    private String  message;
    private String result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
