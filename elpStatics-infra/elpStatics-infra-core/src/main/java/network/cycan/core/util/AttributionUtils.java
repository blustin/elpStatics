package network.cycan.core.util;

import network.cycan.core.ModelSerializable;

/**
 * @author 林坚丁
 * @date 2020/5/11 17:36
 * @Description
 */
public class AttributionUtils implements ModelSerializable {
    private static final long serialVersionUID = -7297042746579525696L;
    private String success;// {0失败/1成功} 执行结果
    private String msgid;// 异常消息代码
    private String msg;// 异常消息
}
