package network.cycan.core.exception;

/**
 * @author 林坚丁
 * @date 2020/5/12 14:20
 * @Description 异常基础类负责拼装消息
 */
public abstract class NestedExceptionUtils {

    public static String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuffer buf = new StringBuffer();
            if (message != null) {
                buf.append(message).append(";");
            }
            buf.append("Exception is:").append(cause);
            return buf.toString();
        } else {
            return message;
        }
    }

    public static String buildMessage(String message, int type, Throwable cause) {
        if (cause != null) {
            StringBuffer buf = new StringBuffer();
            if (message != null) {
                buf.append(message).append(",");
            }
            try {
                switch (type) {
                    case ExceptionDescriptor.Exception_DEF:
                        buf.append("NestedException.");
                    case ExceptionDescriptor.Exception_SQL:
                        buf.append("SQLException,SQL is: " + ((DataBaseAccessException) cause).getSql());
                    case ExceptionDescriptor.Exception_CCE:
                        buf.append("ClassCastException.");
                    case ExceptionDescriptor.Exception_IOB:
                        buf.append("IndexOutOfBandsException.");
                    case ExceptionDescriptor.Exception_NCF:
                        buf.append("NoClassDefFoundException.");
                    case ExceptionDescriptor.Exception_SEC:
                        buf.append("SeccurityException.");
                    case ExceptionDescriptor.Exception_NPE:
                        buf.append("NullPointerException.");
                    case ExceptionDescriptor.Exception_MOG:// MongoDB数据库异常
                        buf.append("NestedMongoDbException.");
                    case ExceptionDescriptor.Exception_BIZ:// 业务流程自定义异常
                        buf.append("NestedBusinessException,business info is: "
                                + ((NestedBusinessException) cause).getBusinessMessage());
                    default:// 业务自定义异常
                        buf.append("NestedException");
                }
            } catch (Exception e) {
                buf.append("NestedRuntimeException.");
            }
            // buf.append(" caused by :").append(cause);
            buf.append(" caused by :").append(cause.getMessage());
            return buf.toString();
        } else {
            return message;
        }
    }
}


