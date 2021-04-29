package network.cycan.elpStatics.model.dto;

import java.io.Serializable;
import java.util.List;

public class TransactionRecorkResultDto implements Serializable {

    //{"status":"1","message":"OK","result":"6868852"}
    private String status;
    private String  message;
    private List<TransactionRecorkDto> result;
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

    public List<TransactionRecorkDto> getResult() {
        return result;
    }

    public void setResult(List<TransactionRecorkDto> result) {
        this.result = result;
    }


}
