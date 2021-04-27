package network.cycan.core.exception;

/**
 * <p>
 * RedisException 类型:
 * </p>
 *
 * @author linjd
 * @since 2020/6/5 14:07
 */
public class RedisException extends NestedInspectException {
    private static final long serialVersionUID = 2019604762482690770L;

    public RedisException() {
        super();
    }

    public RedisException(String msg) {
        super(msg);
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

    public RedisException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
