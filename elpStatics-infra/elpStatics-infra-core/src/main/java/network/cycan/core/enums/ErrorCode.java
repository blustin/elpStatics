package network.cycan.core.enums;

import lombok.Getter;

/**
 * @author 林坚丁
 * @date 2020/5/12 14:34
 * @Description
 */
@Getter
public enum ErrorCode {
    UNKNOWN(0, "未知错误"),
    RESOURCE_NOT_FUND(400, "资源未到找"),
    SERVICE_ERROR(500, "服务熔断"),
    BIZ_EXCEPTION(600, "业务错误"),
    DATA_EXCEPTION(602, "数据库异常");




    private Integer code;
    private String msg;


    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
