package network.cycan.elpStatics.model.dto;

import network.cycan.core.page.PageNoSize;

import java.io.Serializable;

public class StsDailyDto extends PageNoSize implements Serializable {
    private static final long serialVersionUID = -5364158447643421863L;
    private String startDate;
    private String endDate;


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
}
