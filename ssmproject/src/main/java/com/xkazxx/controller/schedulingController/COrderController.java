package com.xkazxx.controller.schedulingController;

import com.xkazxx.bean.COrder;
import com.xkazxx.myAnnotation.SurpriseAnnotation;
import com.xkazxx.service.SchedulingService;
import com.xkazxx.util.PublicMethodPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.*;

@Controller
@RequestMapping("/order")
public class COrderController {

    @Autowired
    SchedulingService schedulingService;

    @RequestMapping("/find")
    public String findOrder() {
        //订单查询
        return "order_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map findOrders(@RequestParam int page,
                          @RequestParam int rows) {
        return schedulingService.findOrders(page, rows);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public COrder findCOrder(@PathVariable("id") String id) {
        return schedulingService.findCOrder(id);

    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List cOrderGetData() {
        return schedulingService.getAllCOrder();
    }

    @RequestMapping("/search_order_by_orderProduct")
    @ResponseBody
    public Map findCOrderByCOrderProduct(String searchValue, int page, int rows) {

        return schedulingService.findCOrderByCOrderProduct(searchValue, page, rows);
    }

    @RequestMapping("/search_order_by_orderCustom")
    @ResponseBody
    public Map findCOrderByCOrderCustom(String searchValue, int page, int rows) {

        return schedulingService.findCOrderByCOrderCustom(searchValue, page, rows);
    }

    @RequestMapping("/search_order_by_orderId")
    @ResponseBody
    public Map findCOrderByCOrderId(String searchValue, int page, int rows) {

        return schedulingService.findCOrderByCOrderId(searchValue, page, rows);
    }

    @RequestMapping("/add_judge")
    @ResponseBody
    public Map add_judge() {
        String msg = null;
        return PublicMethodPart.judgeResult(msg);
    }

    @RequestMapping("/add")
    public String addCOrder() {

        return "order_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map insertCOrder(COrder cOrder) {
        boolean success = schedulingService.insertCOrder(cOrder);
        return PublicMethodPart.optionSuccess(success);
    }


    @RequestMapping("/edit")
    public String editCOrder() {

        return "order_edit";
    }

    @RequestMapping("/edit_judge")
    @ResponseBody
    public Map edit_judge() {
        String msg = null;
        return PublicMethodPart.judgeResult(msg);
    }

    @RequestMapping("/delete_judge")
    @ResponseBody
    public Map delete_judge() {
        String msg = null;
        return PublicMethodPart.judgeResult(msg);
    }

    @SurpriseAnnotation
    @RequestMapping("/delete_batch")
    @ResponseBody
    public Map delete_batch_COrder(@NotNull String[] ids) {
        List<String> list= schedulingService.delete_batch_COrder(ids);
        /*if(list != null) {
            for (String s : list) {
                String[] split = s.split(",");
                if(split == null) continue;
                for (String s1 : split) {
                    String realPath = context.getRealPath(s1);
                    File file = new File(realPath);
                    if (file.exists() && file.isFile()) file.delete();
                }
            }
        }*/
        return PublicMethodPart.optionSuccess(true);
    }

    @RequestMapping("/update_all")
    @ResponseBody
    public Map update_all_COrder(COrder cOrder) {
        boolean success = schedulingService.update_all_COrder(cOrder);
        return PublicMethodPart.optionSuccess(success);
    }

    @RequestMapping("/update_note")
    @ResponseBody
    public Map updateCOrderNote(String note, String orderId) {
        boolean success = schedulingService.updateCOrderNote(note, orderId);
        return PublicMethodPart.optionSuccess(success);
    }

}
