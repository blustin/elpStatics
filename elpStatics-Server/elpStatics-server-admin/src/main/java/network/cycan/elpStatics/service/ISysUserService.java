package network.cycan.elpStatics.service;

import network.cycan.elpStatics.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blustin
 * @since 2021-05-24
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser findByUsername(String userName);
}
