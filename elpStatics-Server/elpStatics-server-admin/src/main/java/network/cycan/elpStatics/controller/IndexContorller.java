package network.cycan.elpStatics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * IndexContorller 类型:
 * </p>
 *
 * @author linjd
 * @since 2021/1/10 10:49
 */
@Controller
public class IndexContorller {
    @RequestMapping("")
    public String index(Model model) {
        return "index";
    }
}
