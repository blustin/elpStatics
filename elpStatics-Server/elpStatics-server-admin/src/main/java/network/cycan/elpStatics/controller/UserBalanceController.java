package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.core.util.DateUtils;
import network.cycan.elpStatics.model.dto.TransactionDto;
import network.cycan.elpStatics.model.dto.UserDto;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.IBlockChainService;
import network.cycan.elpStatics.service.ITransactionRecorkService;
import network.cycan.elpStatics.service.IUserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
@Controller
@RequestMapping("/user")
public class UserBalanceController {
    @Autowired
    private IUserBalanceService iUserBalanceService;
    @Autowired
    private IBlockChainService iBlockChainService;
    @RequestMapping("/pageList")
    public String list(HttpServletRequest request, Model model, UserDto dto) {
        IPage<UserBalance> pageList =  iUserBalanceService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "user/pageList";
    }

    @RequestMapping("/refreshMovingBalance")
    public  String refreshMovingBalance(HttpServletRequest request)
    {
         iBlockChainService.saveMovingBalance(DateUtils.today());
        return  "ok";
    }

}
