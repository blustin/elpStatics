package network.cycan.core.util;

import java.util.List;
/**
 * @author 林坚丁
 * @date 2020/5/12 16:12
 * @Description
 */
public abstract class ListUtils {
    /**
     * 获取集合的长度，若为空则返回0
     *
     * @param list 集合
     * @return 集合的长度
     */
    public static int getSize(List<?> list) {
        return list != null ? list.size() : 0;
    }


    /**
     * 从List 从0开始截取size个数组 List 不变
     * @param list
     * @param size
     * @return
     */
    public static List<?> getList(List<?> list,int size){
        List<?> subList;
        if (size <= list.size())
        {
            subList = list.subList(0,size);
        }else{
            subList = list.subList(0, list.size());
        }
        return  subList;
    }

}
