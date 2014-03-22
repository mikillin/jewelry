package com.project.controllers.orders;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: S.Rogachevsky
 * Date: 24.02.14
 * Time: 21:56
 */
public interface OrderService {

    public ModelAndView saveOrder(
            @RequestParam Integer material,
            @RequestParam String orderDateCompletion,
            @RequestParam String fio,
            @RequestParam String homeAddress,
            @RequestParam Integer phone,
            @RequestParam Integer loses,
            @RequestParam String productName,
            @RequestParam Integer productCost,
            @RequestParam Integer advance,
            @RequestParam Float materialContent,
            @RequestParam String celerity,
            @RequestParam String materialContentTest,
            @RequestParam String service
    );
}
