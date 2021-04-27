package network.cycan.core.util;

import java.util.UUID;

/**
 * @author 林坚丁
 * @date 2020/5/9 11:38
 * @Description UUIDUtil 生成随机主键
 */
public final class UUIDUtils {
    /**
     * 生成随机UUID
     *
     * @return String
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
