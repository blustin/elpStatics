package network.cycan.core.enums;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 林坚丁
 * @date 2020/5/9 11:45
 * @Description 状态枚举类
 */
public enum Status {
    VALID("1", "启用"),
    ININVALID("2", "禁用");
    private String value;
    private String cnName;

    Status(String value, String cnName) {
        this.value = value;
        this.cnName = cnName;
    }

    public String getValue() {
        return value;
    }

    public String getCnName() {
        return cnName;
    }

    public static List<Status> list() {
        return Arrays.asList(Status.values());
    }

    /**
     * 跟据所传的数字字符串获取相应的枚举类
     *
     * @param value 数字
     */
    public static Status getEnumByValue(String value) {
        if (StringUtils.isNotBlank(value)) {
            for (Status status : Status.values()) {
                if (status.getValue().equals(value)) {
                    return status;
                }
            }
        }
        return null;

    }

    /**
     * 通过数值获取相应的中文名称
     *
     * @param value 数值
     */
    public static String getCnNameByValue(String value) {
        Status status = getEnumByValue(value);
        if (status != null) {
            return status.getCnName();
        }
        return "";
    }
}
