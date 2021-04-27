package network.cycan.core.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author 林坚丁
 * @date 2020/5/9 10:35
 * @Description
 */
public enum YesOrNo {
    YES("1", "是"), NO("0", "否");

    private String code;
    private String cnName;

    YesOrNo(String code, String cnName) {
        this.code = code;
        this.cnName = cnName;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public static String getCnNameByCode(String code) {
        for (YesOrNo yesOrNo : YesOrNo.values()) {
            if (yesOrNo.getCode().equals(code)) {
                return yesOrNo.getCnName();
            }
        }
        return "";
    }

    public static String getCodeByCnName(String cnName) {
        for (YesOrNo yesOrNo : YesOrNo.values()) {
            if (yesOrNo.getCnName().equals(cnName)) {
                return yesOrNo.getCode();
            }
        }
        return "";
    }

    public static List<YesOrNo> getAllList(){
        return Arrays.asList(YesOrNo.values());
    }
}
