package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.elpStatics.model.dto.TransactionDto;
import network.cycan.elpStatics.model.dto.UserDto;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.service.ITransactionRecorkService;
import network.cycan.elpStatics.service.IUserBalanceService;
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
@Controller
@RequestMapping("/user")
public class UserBalanceController {
    @Autowired
    private IUserBalanceService iUserBalanceService;

    @RequestMapping("/pageList")
    public String list(HttpServletRequest request, Model model, UserDto dto) {
        IPage<UserBalance> pageList =  iUserBalanceService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "user/pageList";
    }
}
