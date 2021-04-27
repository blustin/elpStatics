package network.cycan.common.apiInfor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ResponseUtils 类型:
 * </p>
 *
 * @author linjd
 * @since 2021/1/25 22:12
 */
@Slf4j
public class ResponseUtils<T> {
    private static  final  ApiResponse sucRtn=ApiResponse.builder().success(true).build();
    private static  final  ApiResponse eroRtn=ApiResponse.builder().success(false).build();

    /**
     * 返回正常响应
     */
    public static ApiResponse buildRtnSuc() {
        return sucRtn;
    }
    /**
     * 返回空响应
     */
    public static ApiResponse rtnNULL() {
        return null;
    }
    /**
     * 返回对应格式的消息
     *
     * @param eroStr
     *            消息内容
     * @return json格式数据
     */
    public static ApiResponse buildRtnEro(String eroStr) {
        if (StringUtils.isBlank(eroStr)) {
            return eroRtn;
        }
        return  ApiResponse.builder().success(false).msg(eroStr).build();
    }

    /**
     * 返回错误信息结果，编码和描述
     * @param eroCode
     * @param eroStr
     * @return
     */
    public static ApiResponse buildRtnEro(Integer eroCode, String eroStr) {
        return  ApiResponse.builder().success(false).code(eroCode).msg(eroStr).build();
    }

    public static ApiResponse buildRtnSuc(String str) {
        return  ApiResponse.builder().success(true).msg(str).build();
    }

    public static ApiResponse buildRtnOne(Object obj) {
        return  ApiResponse.builder().success(true).data(obj).build();
    }
    public static<T> ApiResponse<T> buildRtnTypeOne(T obj) {
        ApiResponse ret=ApiResponse.builder().success(true).build();
        ret.setData(obj);
        return  ret;
    }
}
