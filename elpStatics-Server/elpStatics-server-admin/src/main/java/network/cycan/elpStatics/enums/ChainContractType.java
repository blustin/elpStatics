package network.cycan.elpStatics.enums;

import network.cycan.core.enums.Status;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

public enum ChainContractType {

    ELP("1", "ELP"),
    LP("2", "LP"),
    Moving("3", "流动性挖矿合约"),
    LP_V2("4", "LP_V2"),
    Moving_V2("5", "流动性挖矿合约_V2");
    ChainContractType(String type, String name) {
        this.type = type;
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private String type;
    private String name;

    public static List<ChainContractType> list() {
        return Arrays.asList(ChainContractType.values());
    }



}
