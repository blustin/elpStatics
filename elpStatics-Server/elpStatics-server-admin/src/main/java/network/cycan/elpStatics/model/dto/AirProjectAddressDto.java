package network.cycan.elpStatics.model.dto;

import network.cycan.core.page.PageNoSize;

import java.io.Serializable;

public class AirProjectAddressDto extends PageNoSize implements Serializable {
    private static final long serialVersionUID = -6207688125611454185L;

    private  String projectId;

    private String userAdderss;

    private  String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserAdderss() {
        return userAdderss;
    }

    public void setUserAdderss(String userAdderss) {
        this.userAdderss = userAdderss;
    }



}
