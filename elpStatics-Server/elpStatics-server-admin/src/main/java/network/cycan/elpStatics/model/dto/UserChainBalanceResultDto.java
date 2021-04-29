package network.cycan.elpStatics.model.dto;

import java.io.Serializable;
import java.util.List;

public class UserChainBalanceResultDto implements Serializable {

    private  String status;
    private String  message;
    private List<UserChainBalanceDto> result;

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

    public List<UserChainBalanceDto> getResult() {
        return result;
    }

    public void setResult(List<UserChainBalanceDto> result) {
        this.result = result;
    }
}
