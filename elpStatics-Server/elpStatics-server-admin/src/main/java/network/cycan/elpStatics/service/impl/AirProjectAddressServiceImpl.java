package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import network.cycan.core.util.StringUtils;
import network.cycan.elpStatics.model.dto.AirProjectAddressDto;
import network.cycan.elpStatics.model.entity.AirProjectAddress;
import network.cycan.elpStatics.mapper.AirProjectAddressMapper;
import network.cycan.elpStatics.model.entity.StsDailyContract;
import network.cycan.elpStatics.service.IAirProjectAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blustin
 * @since 2021-05-21
 */
@Service
public class AirProjectAddressServiceImpl extends ServiceImpl<AirProjectAddressMapper, AirProjectAddress> implements IAirProjectAddressService {


    @Override
    public IPage<AirProjectAddress> pageByCondition(AirProjectAddressDto dto) {
        IPage<AirProjectAddress> stsDailyContractPage=new Page<>();
        stsDailyContractPage.setSize(dto.getPageSize());
        stsDailyContractPage.setCurrent(dto.getPageNo());
        QueryWrapper<AirProjectAddress> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getProjectId())){
            queryWrapper.eq("projectId",dto.getProjectId());
        }
        if(StringUtils.isNotEmpty(dto.getUserAdderss())) {
            queryWrapper.eq("userAdderss", dto.getUserAdderss());
        }
        IPage<AirProjectAddress> pageList= this.getBaseMapper().selectPage(stsDailyContractPage,queryWrapper);
        return pageList;
    }
    @Override
    public List<String> selectAddressList(AirProjectAddressDto dto) {
        QueryWrapper<AirProjectAddress> lambdaQueryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getProjectId())){
            lambdaQueryWrapper.eq("projectId",dto.getProjectId());
        }
        if(StringUtils.isNotEmpty(dto.getUserAdderss())) {
            lambdaQueryWrapper.eq("userAdderss", dto.getUserAdderss());
        }
        lambdaQueryWrapper.select(AirProjectAddress.class, e -> "userAdderss".equals(e.getColumn()));
        List<AirProjectAddress> list=this.getBaseMapper().selectList(lambdaQueryWrapper);

        return  list.stream().map(item->item.getUserAdderss()).collect(Collectors.toList());
    }

    @Override
    public List<String> selectExistAddressList(Long projectId,List<String> addressList) {
        QueryWrapper<AirProjectAddress> lambdaQueryWrapper = new QueryWrapper<>();
        lambdaQueryWrapper.in("userAdderss",addressList);
        lambdaQueryWrapper.eq("projectId",projectId);
        lambdaQueryWrapper.select(AirProjectAddress.class, e -> "userAdderss".equals(e.getColumn()));
        List<AirProjectAddress> list=this.getBaseMapper().selectList(lambdaQueryWrapper);
        return list.stream().map(item->item.getUserAdderss()).collect(Collectors.toList());
    }
    @Override
    public  int  getAdderessCount(Long projectId,String userAdderss)
    {
        QueryWrapper<AirProjectAddress> queryCount=new QueryWrapper<>();
        queryCount.eq("projectId",projectId);
        queryCount.eq("userAdderss",userAdderss);
        return baseMapper.selectCount(queryCount);
    }

}
