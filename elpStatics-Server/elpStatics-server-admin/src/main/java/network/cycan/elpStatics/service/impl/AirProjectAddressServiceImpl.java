package network.cycan.elpStatics.service.impl;

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
        queryWrapper.eq("userAdderss",dto.getUserAdderss());
        IPage<AirProjectAddress> pageList= this.baseMapper.selectPage(stsDailyContractPage,queryWrapper);
        return pageList;
    }
}
