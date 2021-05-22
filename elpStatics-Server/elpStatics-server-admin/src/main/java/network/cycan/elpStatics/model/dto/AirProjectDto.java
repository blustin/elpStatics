package network.cycan.elpStatics.model.dto;

import network.cycan.core.page.PageNoSize;

import java.io.Serializable;

public class AirProjectDto extends PageNoSize implements Serializable {
    private static final long serialVersionUID = -6741326159697675404L;
    private  String projectName;
    private  String batchNo;
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }



}
