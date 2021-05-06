package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.elpStatics.model.dto.StsDailyDto;
import network.cycan.elpStatics.model.dto.TransactionDto;
import network.cycan.elpStatics.model.entity.StsDailyContract;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import network.cycan.elpStatics.service.ITransactionRecorkService;
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
@RequestMapping("/transaction")
public class TransactionRecorkController {

    @Autowired
    private ITransactionRecorkService iTransactionRecorkService;

    @RequestMapping("/pageList")
    public String list(HttpServletRequest request, Model model, TransactionDto dto) {
        IPage<TransactionRecork> pageList =  iTransactionRecorkService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "transaction/pageList";
    }
}
