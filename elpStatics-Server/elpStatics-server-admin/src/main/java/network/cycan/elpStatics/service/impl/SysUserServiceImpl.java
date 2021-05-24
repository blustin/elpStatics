package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import network.cycan.elpStatics.model.entity.SysUser;
import network.cycan.elpStatics.mapper.SysUserMapper;
import network.cycan.elpStatics.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blustin
 * @since 2021-05-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser findByUsername(String userName) {
        QueryWrapper<SysUser>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userName",userName);
        SysUser sysUser= this.getBaseMapper().selectOne(queryWrapper);
        return sysUser;
    }
}
