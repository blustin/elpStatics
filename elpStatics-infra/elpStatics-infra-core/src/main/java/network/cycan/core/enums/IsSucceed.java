package network.cycan.core.enums;

import lombok.Data;
import lombok.ToString;

/**
 * @author 林坚丁
 * @date 2020/5/12 13:47
 * @Description
 */
@ToString

public enum IsSucceed {
    SUCCEED("0", "成功"),
    FAILURE("1", "失败"),
    RUNNING("2", "执行中"),
    ;
    private String value;
    private String name;
    public String getValue() {
        return value;
    }
    public String getName() {
        return name;
    }


    IsSucceed(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getNameByValue(String value) {
        for (IsSucceed att : IsSucceed.values()) {
            if (att.getValue().equals(value))
                return att.getName();
        }
        return null;
    }
}
