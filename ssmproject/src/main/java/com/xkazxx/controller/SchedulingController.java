package com.xkazxx.controller;

import com.github.pagehelper.PageInfo;
import com.xkazxx.bean.COrder;
import com.xkazxx.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SchedulingController {

    @Autowired
    SchedulingService schedulingService;

    @RequestMapping("/order/find")
    public String findOrder(Model model) {
        /*model.addAttribute("page",1);
        model.addAttribute("rows",10);*/
        //return "forward:/order/list";
        return "order_list";
    }


    @RequestMapping("/order/list")
    public String findOrder(@RequestParam int page,
                            @RequestParam int rows,
                            Model model) {
        PageInfo<COrder> orderList = schedulingService.findOrder(page,rows);
        //orderList.getList()
        model.addAttribute("orderList",orderList);
        //订单查询
        return "order_list";
    }

    @RequestMapping("/custom/find")
    public String findCustom() {

        //客户查询
        return "custom_list";
    }
    @RequestMapping("product/find")
    public String findProduct() {

        //产品查询
        return "product_list";
    }

    @RequestMapping("/work/find")
    public String findWork() {

        //作业查询
        return "work_list";
    }



    @RequestMapping("/manufacture/find")
    public String findManufacture() {
        //生产计划查询
        return "manufacture_list";
    }

    @RequestMapping("/task/find")
    public String findTask() {

        //生产派工查询
        return "task_list";
    }

}
