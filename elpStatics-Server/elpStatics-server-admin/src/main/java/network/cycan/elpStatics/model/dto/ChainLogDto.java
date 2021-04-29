package network.cycan.elpStatics.model.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class ChainLogDto implements Serializable {
    //{"status":"1","message":"OK","result":"6868852"}
    private static final long serialVersionUID = -432908543160176349L;
    private  String status;
    private String  message;
    private List<ChainLogResultDto> result;

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

    public List<ChainLogResultDto> getResult() {
        return result;
    }

    public void setResult(List<ChainLogResultDto> result) {
        this.result = result;
    }




}
