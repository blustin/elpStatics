package network.cycan.core.code;

/**
 * @author 林坚丁
 * @date 2020/5/12 15:23
 * @Description
 */
public interface DatabaseCode {
    /**
     * 数据重复
     */
    int DB_100 = 100;

    /**
     * 查询超时，请重试
     */
    int DB_101 = 101;

    /**
     * 获取数据失败
     */
    int DB_102 = 102;

    /**
     * 您暂无权限操作数据
     */
    int DB_103 = 103;

    /**
     * SQL执行有误
     */
    int DB_104 = 104;

    /**
     * 数据有误
     */
    int DB_105 = 105;
}
