package network.cycan.elpStatics.model.dto;

import network.cycan.core.page.PageNoSize;

import java.io.Serializable;

public class AirProjectAddressDto extends PageNoSize implements Serializable {
    private static final long serialVersionUID = -6207688125611454185L;
    private String userAdderss;
    public String getUserAdderss() {
        return userAdderss;
    }

    public void setUserAdderss(String userAdderss) {
        this.userAdderss = userAdderss;
    }



}
