package com.xkazxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xkazxx.bean.*;
import com.xkazxx.mapper.*;
import com.xkazxx.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    COrderMapper cOrderMapper;

    @Autowired
    CustomMapper customMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    WorkMapper workMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    ManufactureMapper manufactureMapper;

    @Autowired
    TechnologyMapper technologyMapper;


    @Override
    public Map findOrders(int pageNum,int pageSize) {
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);

        PageInfo<COrder> pageInfo =
                new PageInfo<>(cOrderMapper.findOrders(pageNum,pageSize));

        map.put("rows", pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;

    }

    @Override
    public Custom findCostom(String id) {
        return customMapper.selectByPrimaryKey(id);
    }

    @Override
    public Product findProduct(String id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map findWorks(int pageNum, int pageSize) {
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);

        PageInfo<Work> pageInfo =
                new PageInfo<>(workMapper.findWorks(pageNum,pageSize));
        map.put("rows", pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Map findProducts(int pageNum, int pageSize) {
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);

        PageInfo<Product> pageInfo =
                new PageInfo<>(productMapper.findProducts(pageNum,pageSize));
        map.put("rows", pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Map findTasks(int pageNum, int pageSize) {
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);

        PageInfo<Task> pageInfo = new PageInfo<>(taskMapper.findTasks(pageNum,pageSize));

        map.put("rows", pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Map findManufacture(int pageNum, int pageSize) {
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);

        PageInfo<Manufacture> pageInfo = new PageInfo<>(manufactureMapper.findManufacture(pageNum,pageSize));

        map.put("total",pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }

    @Override
    public Technology findTechnology(String id) {
        return technologyMapper.selectByPrimaryKey(id);
    }

    @Override
    public COrder findCOrder(String id) {
        return cOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List getAllCustom() {
        return customMapper.getAllCustom();
    }

    @Override
    public List getAllProduct() {
        return productMapper.getAllProduct();
    }

    @Override
    public List getAllCOrder() {
        return cOrderMapper.getAllCOrder();
    }

}
