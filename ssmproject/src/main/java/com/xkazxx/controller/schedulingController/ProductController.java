package com.xkazxx.controller.schedulingController;

import com.xkazxx.bean.Product;
import com.xkazxx.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {


    @Autowired
    SchedulingService schedulingService;

    @RequestMapping("product/find")
    public String findProduct() {

        //产品查询
        return "product_list";
    }

    @RequestMapping("product/list")
    @ResponseBody
    public Map findProducts(@RequestParam int page,
                            @RequestParam int rows) {
        //产品查询
        return schedulingService.findProducts(page,rows);
    }

    @RequestMapping("/product/get/{id}")
    @ResponseBody
    public Product findProduct(@PathVariable("id") String id) {
        Product product = schedulingService.findProduct(id);
        //客户查询
        return product;
    }

    @RequestMapping("/product/get_data")
    @ResponseBody
    public List productGetData(@RequestParam int page,
                               @RequestParam int rows) {
        return schedulingService.getAllProduct();
    }


}
