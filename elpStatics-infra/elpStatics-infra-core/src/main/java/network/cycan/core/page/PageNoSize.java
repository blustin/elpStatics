package network.cycan.core.page;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * PageNoSize 类型:
 * </p>
 *
 * @author linjd
 * @since 2021/2/17 18:18
 */

public class PageNoSize implements Serializable {
    private static final long serialVersionUID = -8404032327062382029L;
    private Integer pageNo = 1; // 当前页，默认第一页
    private Integer pageSize = 10; //每页大小，默认每页12条
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
