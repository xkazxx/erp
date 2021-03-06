package com.xkazxx.controller.qualitycontroller;

import com.github.pagehelper.PageInfo;
import com.xkazxx.bean.*;
import com.xkazxx.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Zachary Zhao
 * @create 2019-05-17 20:22
 */
@Controller
public class QualityController {

    @Autowired
    QualityService qualityService;

    @Autowired
    QueryVO queryVO;

    Map<String,Object> map = new HashMap<>();

    /**
     * 不合格品管理-分页查询所有不合格品
     * */
    @RequestMapping(path = {"/unqualify/find"})
    public String findUnqualifyProducts(){ return "unqualify_list"; }

    @RequestMapping("/unqualify/list")
    @ResponseBody
    public QueryVO findAllUnqualifyProductsByPage(@RequestParam("page") int page,
                                                  @RequestParam("rows") int rows){
        PageInfo<UnqualifyProduct> allUnqualifyProductsByPage = qualityService.findAllUnqualifyProductsByPage(page, rows);
        return setQueryVO(allUnqualifyProductsByPage);
    }

    /**
     * 不合格品管理-添加新的不合格品
     * */
    @RequestMapping("/unqualify/add_judge")
    @ResponseBody
    public void preAddUnqualifyProduct(){ }

    @RequestMapping("/unqualify/add")
    public String addUnqualifyProduct(){ return  "unqualify_add";}

    @RequestMapping("/unqualify/insert")
    @ResponseBody
    public Map insertUnqualifyProduct(UnqualifyApply unqualifyApply){
        int res = qualityService.insert(unqualifyApply);
        return setMap(res);
    }

    /**
     * 不合格品管理-根据不合格品申请编号模糊查询
     * */
    @RequestMapping("/unqualify/search_unqualify_by_unqualifyId")
    @ResponseBody
    public QueryVO search_unqualify_by_unqualifyId(@RequestParam("searchValue") String searchValue ,
                                                   @RequestParam("page") int page ,
                                                   @RequestParam("rows") int rows ){
        PageInfo<UnqualifyProduct> unqualifyProductsById = qualityService.search_unqualify_by_unqualifyId(searchValue,page, rows);
        return setQueryVO(unqualifyProductsById);
    }

    /**
     * 不合格品管理-根据不合格品产品名模糊查询
     * */
    @RequestMapping("/unqualify/search_unqualify_by_productName")
    @ResponseBody
    public QueryVO search_unqualify_by_productName(@RequestParam("searchValue") String searchValue ,
                                                   @RequestParam("page") int page ,
                                                   @RequestParam("rows") int rows){
        PageInfo<UnqualifyProduct> unqualifyProductsByName = qualityService.search_unqualify_by_productName(searchValue,page, rows);
        return setQueryVO(unqualifyProductsByName);
    }

    /**
     * 不合格品管理-修改不合格品
     * */
    @RequestMapping("/unqualify/edit_judge")
    @ResponseBody
    public void preUpdateUnqualifyProduct(){ }

    @RequestMapping("/unqualify/edit")
    public String updateUnqualifyProduct(){ return  "unqualify_edit";}

    @RequestMapping("/unqualify/update_all")
    @ResponseBody
    public Map updateUnqualifyApply(UnqualifyApply unqualifyApply){
        int res = qualityService.updateUnqualifyApply(unqualifyApply);
        return setMap(res);
    }

    /**
     * 不合格品管理-删除功能
     * */
    @RequestMapping("/unqualify/delete_judge")
    @ResponseBody
    public void deleteUnqualifyApply() { }

    @RequestMapping("/unqualify/delete_batch")
    @ResponseBody
    public Map deleteUnqualifyApplyBatch(@RequestParam("ids") String[] ids){
        int res =qualityService.deleteUnqualifyApplyBatch(ids);
        return setMap(res);
    }

    /**
     * 成品计量质检-分页查询
     * */
    @RequestMapping("/measure/find")
    public String findMeasurements() { return "measurement_list"; }

    @RequestMapping("/measure/list")
    @ResponseBody
    public QueryVO findMeasurementsByPage(@RequestParam("page") int page ,
                                          @RequestParam("rows") int rows){
        PageInfo<FinalMeasureCheck> pageInfo = qualityService.findMeasurementByPage(page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 成品计量质检-根据质检编号模糊查询
     * */
    @RequestMapping("/measure/search_fMeasureCheck_by_fMeasureCheckId")
    @ResponseBody
    public QueryVO search_fMeasureCheck_by_fMeasureCheckId(@RequestParam("searchValue") String searchValue ,
                                                           @RequestParam("page") int page ,
                                                           @RequestParam("rows") int rows){
        PageInfo<FinalMeasureCheck> pageInfo = qualityService.search_fMeasureCheck_by_fMeasureCheckId(searchValue,page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 成品计量质检-根据订单号模糊查询
     * */
    @RequestMapping("/measure/search_fMeasureCheck_by_orderId")
    @ResponseBody
    public QueryVO search_fMeasureCheck_by_orderId(@RequestParam("searchValue") String orderId ,
                                                   @RequestParam("page") int page ,
                                                   @RequestParam("rows") int rows){
        PageInfo<FinalMeasureCheck> pageInfo = qualityService.search_fMeasureCheck_by_orderId(orderId,page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 成品计量质检-新增功能
     * */
    @RequestMapping("/fMeasureCheck/add_judge")
    @ResponseBody
    public void preAddFinalMeasure(){ }

    @RequestMapping("/measure/add")
    public String AddFinalMeasure() { return "measurement_add"; }

    @RequestMapping("/measure/insert")
    @ResponseBody
    public Map addFinalMeasureCheck(FinalMeasureCheck finalMeasureCheck){
        int res = qualityService.addFinalMeasureCheck(finalMeasureCheck);
        return setMap(res);
    }

    /**
     * 成品计量质检-修改功能
     * */
    @RequestMapping("/fMeasureCheck/edit_judge")
    @ResponseBody
    public void fMeasureCheckEdit_judge() { }

    @RequestMapping("/measure/edit")
    public String measureEdit(){ return  "measurement_edit"; }

    @RequestMapping("/measure/update_all")
    @ResponseBody
    public Map updateFinalMeasureCheck(FinalMeasureCheck finalMeasureCheck){
        int res = qualityService.updateFinalMeasureCheck(finalMeasureCheck);
        return setMap(res);
    }

    /**
     * 成品计量质检-删除功能
     * */
    @RequestMapping("/fMeasureCheck/delete_judge")
    @ResponseBody
    public void preDeleteMeasureCheck() { }

    @RequestMapping("/measure/delete_batch")
    @ResponseBody
    public Map deleteMeasure(@RequestParam("ids") String[] ids){
        int res = qualityService.deleteMeasure(ids);
        return setMap(res);
    }

    /**
     *成品计数质检-分页查询
     * */
    @RequestMapping("/f_count_check/find")
    public String preFindAllCountCheckByPage(){ return "f_count_check_list"; }

    @RequestMapping("/f_count_check/list")
    @ResponseBody
    public QueryVO findAllCountCheckByPage(@RequestParam("page") int page ,
                                           @RequestParam("rows") int rows){
        PageInfo<FinalCountCheck> pageInfo = qualityService.findAllCountCheckByPage(page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 成品计数质检-新增功能
     * */
    @RequestMapping("/fCountCheck/add_judge")
    @ResponseBody
    public void fCountCheckAddJudge(){ }

    @RequestMapping("/f_count_check/add")
    public String fCountCheckAdd(){ return "f_count_check_add"; }

    @RequestMapping("/f_count_check/insert")
    @ResponseBody
    public Map fCountCheckInsert(FinalCountCheck finalCountCheck){
        int res = qualityService.addFinalCountCheck(finalCountCheck);
        return setMap(res);
    }

    /**
     * 成品计数质检-修改功能
     * */
    @RequestMapping("/fCountCheck/edit_judge")
    @ResponseBody
    public void fCountCheckEditJudge() { }

    @RequestMapping("/f_count_check/edit")
    public String fCountCheckEdit(){ return "f_count_check_edit"; }

    @RequestMapping("/f_count_check/update_all")
    @ResponseBody
    public Map fCountCheckUpdate(FinalCountCheck finalCountCheck){
        int res = qualityService.updateFinalCountCheck(finalCountCheck);
        return setMap(res);
    }

    /**
     * 成品计数质检-删除功能
     * */
    @RequestMapping("/fCountCheck/delete_judge")
    @ResponseBody
    public void fCountCheckDeleteJudge(){ }

    @RequestMapping("/f_count_check/delete_batch")
    @ResponseBody
    public Map deleteFinalCountCheck(@RequestParam("ids") String[] ids){
        int res = qualityService.deleteFinalCountCheck(ids);
        return setMap(res);
    }

    /**
     * 成品计数质检-根据质检编号模糊查询
     * */
    @RequestMapping("/f_count_check/search_fCountCheck_by_fCountCheckId")
    @ResponseBody
    public QueryVO searchFCountCheckByCountCheckId(@RequestParam("page") int page ,
                                                   @RequestParam("rows") int rows ,
                                                   @RequestParam("searchValue") String fCountCheckId){
        PageInfo<FinalCountCheck> pageInfo = qualityService.searchFCountCheckByCountCheckId(page,rows,fCountCheckId);
        return setQueryVO(pageInfo);
    }

    /**
     * 成品计数质检-根据订单编号模糊查询
     * */
    @RequestMapping("/f_count_check/search_fCountCheck_by_orderId")
    @ResponseBody
    public QueryVO searchFCountCheckByOrerId(@RequestParam("searchValue") String orderId ,
                                             @RequestParam("page") int page ,
                                             @RequestParam("rows") int rows){
        PageInfo<FinalCountCheck> pageInfo = qualityService.searchFCountCheckByOrerId(orderId,page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 工序计量质检-分页查询
     * */
    @RequestMapping("/p_measure_check/find")
    public String findPMeasureCheck() { return "p_measure_check_list"; }

    @RequestMapping("/p_measure_check/list")
    @ResponseBody
    public QueryVO findAllPMeasureCheck(@RequestParam("page") int page,
                                        @RequestParam("rows") int rows){
        PageInfo<ProcessMeasureCheck> pageInfo = qualityService.findAllPMeasureCheck(page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     *工序计量质检-根据质检编号模糊查询
     * */
    @RequestMapping("/p_measure_check/search_pMeasureCheck_by_pMeasureCheckId")
    @ResponseBody
    public QueryVO searchPMeasureCheckByPMeasureCheckId(@RequestParam("searchValue") String pMeasureCheckId ,
                                                        @RequestParam("page") int page ,
                                                        @RequestParam("rows") int rows){
        PageInfo<ProcessMeasureCheck> pageInfo = qualityService.searchPMeasureCheckByPMeasureCheckId(pMeasureCheckId,page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 工序计量质检-新增功能
     * */
    @RequestMapping("/pMeasureCheck/add_judge")
    @ResponseBody
    public void pMeasureCheckAddJudge() { }

    @RequestMapping("/p_measure_check/add")
    public String pMeasureCheckAdd(){ return "p_measure_check_add"; }

    @RequestMapping("/p_measure_check/insert")
    @ResponseBody
    public Map insertPMeasureCheck(ProcessMeasureCheck processMeasureCheck){
        int res = qualityService.insertPMeasureCheck(processMeasureCheck);
        return setMap(res);
    }

    /**
     * 工序计量质检-修改功能
     * */
    @RequestMapping("/pMeasureCheck/edit_judge")
    @ResponseBody
    public void pMeasureCheckEditJudge() { }

    @RequestMapping("/p_measure_check/edit")
    public String pMeasureCheckEdit(){ return "p_measure_check_edit"; }

    @RequestMapping("/p_measure_check/update_all")
    @ResponseBody
    public Map updatePMeasureCheck(ProcessMeasureCheck processMeasureCheck){
        int res = qualityService.updatePMeasureCheck(processMeasureCheck);
        return setMap(res);
    }

    /**
     * 工序计量质检-删除功能
     * */
    @RequestMapping("/pMeasureCheck/delete_judge")
    @ResponseBody
    public void pMeasureCheckDeleteJudge() { }

    @RequestMapping("/p_measure_check/delete_batch")
    @ResponseBody
    public Map deletePMeasureCheckBatch(@RequestParam("ids") String[] ids){
        int res = qualityService.deletePMeasureCheckBatch(ids);
        return setMap(res);
    }

    /**
     * 工序计数质检-分页查询
     * */
    @RequestMapping("/p_count_check/find")
    public String findPCountCheck(){ return "p_count_check_list"; }

    @RequestMapping("/p_count_check/list")
    @ResponseBody
    public QueryVO findAllPCountCheck(@RequestParam("page") int page ,
                                      @RequestParam("rows") int rows){
        PageInfo<ProcessCountCheck> pageInfo = qualityService.findAllPCountCheck(page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 工序计数质检-根据质检编号模糊查询
     * */
    @RequestMapping("/p_count_check/search_pCountCheck_by_pCountCheckId")
    @ResponseBody
    public QueryVO searchPCountCheckByPCountCheckId(@RequestParam("searchValue") String pCountCheckId,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("rows") int rows){
        PageInfo<ProcessCountCheck> pageInfo = qualityService.searchPCountCheckByPCountCheckId(pCountCheckId,page,rows);
        return setQueryVO(pageInfo);
    }

    /**
     * 工序计数质检-新增功能
     * */
    @RequestMapping("/pCountCheck/add_judge")
    @ResponseBody
    public void pCountCheckAddJudge(){ }

    @RequestMapping("/p_count_check/add")
    public String pCountCheckAdd(){ return "p_count_check_add"; }

    @RequestMapping("/p_count_check/insert")
    @ResponseBody
    public Map insertPCountCheck(ProcessCountCheck processCountCheck){
        int res = qualityService.insertPCountCheck(processCountCheck);
        return setMap(res);
    }

    @RequestMapping("/pCountCheck/edit_judge")
    @ResponseBody
    public void pCountCheckEditJudge() { }

    @RequestMapping("/p_count_check/edit")
    public String pCountCheckEdit() { return "p_count_check_edit"; }

    @RequestMapping("/p_count_check/update_all")
    @ResponseBody
    public Map undatePCountCheck(ProcessCountCheck processCountCheck){
        int res = qualityService.undatePCountCheck(processCountCheck);
        return setMap(res);
    }

    /**
     * 工序计数质检-删除功能
     * */
    @RequestMapping("/pCountCheck/delete_judge")
    @ResponseBody
    public void pCountCheckDeleteJudge() { }

    @RequestMapping("/p_count_check/delete_batch")
    @ResponseBody
    public Map deletePCountCheckBatch(@RequestParam("ids") String[] ids){
        int res = qualityService.deletePCountCheckBatch(ids);
        return setMap(res);
    }
    /*
    自定义方法，根据持久层返回的res设置返回给前端的map
     */
    public Map setMap(int res){
        if(res ==1){
            map.put("status",200);
            map.put("msg","OK");
        }else {
            map.put("status",500);
            map.put("msg","ERROR");
        }
        map.put("data",null);
        return map;
    }

    /**
     * 自定义方法，分页查询时使用
     * 传入参数PageInfo
     * 方法体内设置前端需要的total个rows参数
     * 返回值为分页查询工具类
     * */
    public QueryVO setQueryVO(PageInfo pageInfo){
        queryVO.setTotal(Math.toIntExact(pageInfo.getTotal()));
        queryVO.setRows(pageInfo.getList());
        return queryVO;
    }
}
