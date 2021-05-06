package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import network.cycan.core.util.StringUtils;
import network.cycan.elpStatics.model.dto.UserDto;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.mapper.UserBalanceMapper;
import network.cycan.elpStatics.service.IUserBalanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
@Service
public class UserBalanceServiceImpl extends ServiceImpl<UserBalanceMapper, UserBalance> implements IUserBalanceService {
    @Autowired
    private UserBalanceMapper userBalanceMapper;
    @Override
    public Long getMaxBlockNo(String userType) {
        QueryWrapper<UserBalance>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userType",userType);
        queryWrapper.orderByDesc("blockNum").last("limit 1");
        UserBalance userBalance=  getOne(queryWrapper);
        if(userBalance==null) {
            return  0L;
        }
        return userBalance.getBlockNum();
    }

    @Override
    public IPage<UserBalance> pageByCondition(UserDto dto) {
        IPage<UserBalance> transactionRecorkPage=new Page<>();
        transactionRecorkPage.setSize(dto.getPageSize());
        transactionRecorkPage.setCurrent(dto.getPageNo());
        QueryWrapper<UserBalance> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userType",dto.getUserType());
        if(StringUtils.isNotEmpty(dto.getStartDate()))
        {
            queryWrapper.ge("updateTime",dto.getStartDate());
        }
        if(StringUtils.isNotEmpty(dto.getEndDate()))
        {
            queryWrapper.le("updateTime",dto.getEndDate());
        }
        if(StringUtils.isNotEmpty(dto.getFromAmount()))
        {
            queryWrapper.ge("balanceAmount",dto.getFromAmount());
        }
        if(StringUtils.isNotEmpty(dto.getToAmount()))
        {
            queryWrapper.le("balanceAmount",dto.getToAmount());
        }
        if(StringUtils.isNotEmpty(dto.getUserAddress()))
        {
            queryWrapper.eq("userAddress",dto.getUserAddress());
        }

        queryWrapper.orderByDesc("updateTime");

        IPage<UserBalance> pageList= userBalanceMapper.selectPage(transactionRecorkPage,queryWrapper);
        return pageList;
    }
}
