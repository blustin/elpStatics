package network.cycan.core.exception;

/**
 * @author 林坚丁
 * @date 2020/5/12 14:27
 * @Description 业务自定义异常基类
 */
public class NestedBusinessException extends NestedRuntimeException{
    private static final long serialVersionUID = -4594288245998945585L;

    ExceptionResult serverException;
    String businessMes;

    public NestedBusinessException(String msg) {
        super(msg);
        businessMes = msg;
    }

    public NestedBusinessException(Throwable cause) {
        super(cause);
    }

    public NestedBusinessException(String msg, Throwable cause) {
        super(msg, cause);
        businessMes = msg;
    }

    public ExceptionResult getServerException() {
        return serverException;
    }

    public void setServerException(ExceptionResult serverException) {
        this.serverException = serverException;
    }

    /**
     * Return the detail message, including the message from the business check
     * exception if there is one.
     */
    public String getMessage() {
        return super.getMessage(ExceptionDescriptor.Exception_BIZ);
    }

    public String getBusinessMessage() {
        return businessMes == null ? "" : businessMes;
    }
}
