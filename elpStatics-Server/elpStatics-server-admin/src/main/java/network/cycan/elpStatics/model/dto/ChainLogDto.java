package network.cycan.elpStatics.model.dto;

import java.math.BigInteger;
import java.util.List;

public class ChainLogDto {
    //{"status":"1","message":"OK","result":"6868852"}
    private  String status;
    private String  message;
    private List<ChainLogResultDto> results;

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

    public List<ChainLogResultDto> getResults() {
        return results;
    }

    public void setResults(List<ChainLogResultDto> results) {
        this.results = results;
    }




}
