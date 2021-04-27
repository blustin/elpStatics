package network.cycan.core.enums;

/**
 * @author 林坚丁
 * @date 2020/5/12 13:50
 * @Description
 */
public enum IsValid {
    INVALID("0", "无效"), VALID("1", "有效"), UNVALID("2", "未生效");

    private String value;
    private String name;

    IsValid(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByValue(String value) {
        for (IsValid att : IsValid.values()) {
            if (att.getValue().equals(value))
                return att.getName();
        }
        return null;
    }
}
