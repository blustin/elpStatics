package network.cycan.core.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import network.cycan.core.enums.ErrorCode;
import network.cycan.core.util.DateUtils;
import lombok.Data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.io.IOException;
import java.util.Date;

/**
 * @author 林坚丁
 * @date 2020/5/12 14:29
 * @Description
 */
@Data
public class ExceptionResult {
    private static final Log logger = LogFactory.getLog(ExceptionResult.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    private HttpStatus status;
    private Date errorTime; //发生时间
    private String errorMsg; //错误信息
    private ErrorCode code; //错误代码
    private String debugMsg;//调试信息
    private String exception; //异常名称

    public ExceptionResult() {
        errorTime = new Date();
    }

    public ExceptionResult(HttpStatus status, ErrorCode errorCode, Throwable ex) {
        this();
        this.status = status;
        this.code = errorCode;
        this.errorMsg = ex.getMessage();
        this.debugMsg = ex.getLocalizedMessage();
        this.exception = ex.getClass().getName();

    }

    /**
     * 解析服务端返回的误错信息
     * @param errorMsg  msgBody
     */
    public static ExceptionResult parse(String errorMsg) {

        try {
            return mapper.readValue(errorMsg,ExceptionResult.class);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

}
