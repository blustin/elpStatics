package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import network.cycan.core.util.StringUtils;
import network.cycan.elpStatics.model.dto.TransactionDto;
import network.cycan.elpStatics.model.entity.StsDailyContract;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.mapper.TransactionRecorkMapper;
import network.cycan.elpStatics.service.ITransactionRecorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
@Service
public class TransactionRecorkServiceImpl extends ServiceImpl<TransactionRecorkMapper, TransactionRecork> implements ITransactionRecorkService {
    @Autowired
   private  TransactionRecorkMapper recorkMapper;
    @Override
    public IPage<TransactionRecork> pageByCondition(TransactionDto dto) {
        IPage<TransactionRecork> transactionRecorkPage=new Page<>();
        transactionRecorkPage.setSize(dto.getPageSize());
        transactionRecorkPage.setCurrent(dto.getPageNo());
        QueryWrapper<TransactionRecork> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("tranactionType",dto.getTranactionType());
        if(StringUtils.isNotEmpty(dto.getStartDate()))
        {
            queryWrapper.ge("transactionTime",dto.getStartDate());
        }
        if(StringUtils.isNotEmpty(dto.getEndDate()))
        {
            queryWrapper.le("transactionTime",dto.getEndDate());
        }
        if(StringUtils.isNotEmpty(dto.getFromAmount()))
        {
            queryWrapper.ge("transactionAmount",dto.getFromAmount());
        }
        if(StringUtils.isNotEmpty(dto.getToAmount()))
        {
            queryWrapper.le("transactionAmount",dto.getToAmount());
        }
        if(StringUtils.isNotEmpty(dto.getFromAddress()))
        {
            queryWrapper.eq("fromUserAddress",dto.getFromAddress());
        }
        if(StringUtils.isNotEmpty(dto.getToAddress())) {
            queryWrapper.eq("toUserAddress",dto.getToAddress());
        }
        queryWrapper.orderByDesc("transactionTime");

        IPage<TransactionRecork> pageList= recorkMapper.selectPage(transactionRecorkPage,queryWrapper);
        return pageList;
    }
}
