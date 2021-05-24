package network.cycan.elpStatics.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.core.util.StringUtils;
import network.cycan.elpStatics.model.dto.StsDailyDto;
import network.cycan.elpStatics.model.entity.StsDailyContract;
import network.cycan.elpStatics.service.IStsDailyContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.management.Query;
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
@RequestMapping("/daily")
public class StsDailyContractController extends BaseController {
    @Autowired
   private IStsDailyContractService iStsDailyContractService;

    @RequestMapping("/pageList")
    public String list(HttpServletRequest request, Model model, StsDailyDto dto) {
        IPage<StsDailyContract> pageList =  iStsDailyContractService.pageByCondition(dto);
        model.addAttribute("pageList",pageList);
        model.addAttribute("dto",dto);
        return "daily/pageList";
    }


}
