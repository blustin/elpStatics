package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import network.cycan.core.util.DateUtils;
import network.cycan.elpStatics.enums.ChainContractType;
import network.cycan.elpStatics.model.dto.StsDailyDto;
import network.cycan.elpStatics.model.dto.TransactionDto;
import network.cycan.elpStatics.model.entity.StsDailyContract;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.IStsDailyContractService;
import network.cycan.elpStatics.service.ITransactionRecorkService;
import network.cycan.elpStatics.util.HttpBlockChainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
@Slf4j
@Controller
@RequestMapping("/transaction")
public class TransactionRecorkController extends BaseController {

    @Autowired
    private IBlockChainService iBlockChainService;
    @Autowired
    private ITransactionRecorkService iTransactionRecorkService;
    @Autowired
    private IStsDailyContractService iStsDailyContractService;

    @RequestMapping("/pageList")
    public String list(HttpServletRequest request, Model model, TransactionDto dto) {
        IPage<TransactionRecork> pageList =  iTransactionRecorkService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "transaction/pageList";
    }

    @RequestMapping("/refresElpRecord")
    public  String refresElpRecord(HttpServletRequest request)
    {
        iBlockChainService.saveTodayBlockData(DateUtils.today(), HttpBlockChainUtil.ELP_CONTRACT_ADDREES, ChainContractType.ELP.getType());
        return  "ok";
    }

    @RequestMapping("/refresLpRecord")
    public  String refresLpRecord(HttpServletRequest request)
    {
        iBlockChainService.saveTodayBlockData(DateUtils.today(), HttpBlockChainUtil.LP_TOKEN_ADDRESS, ChainContractType.LP.getType());
        return  "ok";
    }
    @RequestMapping("/refreshJobRecord")
    public String refreshJobRecord(HttpServletRequest request){
        try
        {

            iBlockChainService.saveTodayBlockData(DateUtils.today(), HttpBlockChainUtil.ELP_CONTRACT_ADDREES, ChainContractType.ELP.getType());
            iBlockChainService.saveTodayBlockData(DateUtils.today(), HttpBlockChainUtil.LP_TOKEN_ADDRESS, ChainContractType.LP.getType());
            iBlockChainService.saveMovingBalance(DateUtils.today());
            iStsDailyContractService.dailyStatic(DateUtils.today());

        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());

        }
        return  "ok";
    }
}
