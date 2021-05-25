package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import network.cycan.core.util.StringUtils;
import network.cycan.elpStatics.model.dto.SysUserDto;
import network.cycan.elpStatics.model.entity.AirProject;
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

    @Override
    public IPage<SysUser> pageByCondition(SysUserDto dto) {
        IPage<SysUser> page=new Page<>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getPageNo());
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getUserName())) {
            queryWrapper.like("userName", dto.getUserName());
        }
        IPage<SysUser> pageList= this.baseMapper.selectPage(page,queryWrapper);
        return pageList;
    }
}
