package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import network.cycan.core.util.StringUtils;
import network.cycan.elpStatics.model.dto.AirProjectDto;
import network.cycan.elpStatics.model.entity.AirProject;
import network.cycan.elpStatics.mapper.AirProjectMapper;
import network.cycan.elpStatics.model.entity.AirProjectAddress;
import network.cycan.elpStatics.service.IAirProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 空头项目名称 服务实现类
 * </p>
 *
 * @author blustin
 * @since 2021-05-21
 */
@Service
public class AirProjectServiceImpl extends ServiceImpl<AirProjectMapper, AirProject> implements IAirProjectService {

    @Override
    public IPage<AirProject> pageByCondition(AirProjectDto dto) {
        IPage<AirProject> page=new Page<>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getPageNo());
        QueryWrapper<AirProject> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getProjectName())) {
            queryWrapper.like("projectName", dto.getProjectName());
        }
        if(StringUtils.isNotEmpty(dto.getBatchNo()))
        {
            queryWrapper.like("projectBatchNo",dto.getBatchNo());
        }
        IPage<AirProject> pageList= this.baseMapper.selectPage(page,queryWrapper);
        return pageList;

    }
}
