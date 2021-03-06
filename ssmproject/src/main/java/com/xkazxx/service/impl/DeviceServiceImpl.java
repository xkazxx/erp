package com.xkazxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xkazxx.bean.*;
import com.xkazxx.mapper.*;
import com.xkazxx.service.DeviceService;
import com.xkazxx.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Autowired
    DeviceCheckMapper deviceCheckMapper;

    @Autowired
    DeviceFaultMapper deviceFaultMapper;

    @Autowired
    DeviceMaintainMapper deviceMaintainMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public <T> ResponseVo setResponseVo(int page, int rows, Class<?> c){

        PageHelper.startPage(page, rows);

        ResponseVo<T> responseVo = new ResponseVo<>();

        if("Device".equals(c.getSimpleName())) {
            PageInfo<DeviceVo> deviceVoPageInfo = new PageInfo<>(deviceMapper.selectAllDeviceVo());

            responseVo.setTotal(deviceVoPageInfo.getTotal());
            responseVo.setRows((List) deviceVoPageInfo.getList());

        }else if("DeviceType".equals(c.getSimpleName())) {
            PageInfo<DeviceType> deviceTypePageInfo = new PageInfo<>(deviceTypeMapper.selectAllDeviceType());

            responseVo.setTotal(deviceTypePageInfo.getTotal());
            responseVo.setRows((List) deviceTypePageInfo.getList());
        }else if("DeviceCheck".equals((c.getSimpleName()))) {
            PageInfo<DeviceCheckVo> deviceCheckPageInfo = new PageInfo<>(deviceCheckMapper.selectAllDeviceCheck());

            responseVo.setTotal(deviceCheckPageInfo.getTotal());
            responseVo.setRows((List) deviceCheckPageInfo.getList());
        }else if("DeviceFault".equals(c.getSimpleName())){
            PageInfo<DeviceFaultVo> deviceFaultPageInfo = new PageInfo<>(deviceFaultMapper.selectAllDeviceFault());

            responseVo.setTotal(deviceFaultPageInfo.getTotal());
            responseVo.setRows((List) deviceFaultPageInfo.getList());
        }else if("DeviceMaintain".equals(c.getSimpleName())){
            PageInfo<DeviceMaintainVo> deviceMaintainPageInfo = new PageInfo<>(deviceMaintainMapper.selectAllDeviceMaintain());

            responseVo.setTotal(deviceMaintainPageInfo.getTotal());
            responseVo.setRows((List) deviceMaintainPageInfo.getList());
        }else if("EmployeeAndDepartmentVo".equals(c.getSimpleName())){
            /*人员监控模块的人员管理*/
            PageInfo<EmployeeAndDepartmentVo> employeeAndDepartmentVoPageInfo = new PageInfo<>(employeeMapper.selectEmployeeAndDepartment());

            responseVo.setTotal(employeeAndDepartmentVoPageInfo.getTotal());
            responseVo.setRows((List) employeeAndDepartmentVoPageInfo.getList());
        }

        return responseVo;
    }

    @Override
    public <T> List<T> setList(Class<?> c) {

        List<T> list = new ArrayList<>();

        if("Department".equals(c.getSimpleName())){
            list = (List<T>) departmentMapper.selectAllDepartment();
        }else if("DeviceType".equals(c.getSimpleName())){
            list = (List<T>) deviceTypeMapper.selectAllDeviceType();
        }else if("EmployeeAndDepartmentVo".equals(c.getSimpleName())){
            list = (List<T>) employeeMapper.selectEmployeeAndDepartment();
        }else if("Device".equals(c.getSimpleName())){
            list = (List<T>) deviceMapper.selectAllDeviceVo();
        }

        return list;
    }

    @Override
    public DeviceType getDeviceTypeById(String id) {

        return deviceTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Device getDevice(String id) {

        return deviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public EmployeeAndDepartmentVo getEmployeeAndDepartmentVo(String id) {

        return employeeMapper.selectEmployeeAndDepartmentById(id);
    }

    @Override
    public DeviceFault getDeviceFault(String id) {

        return deviceFaultMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insertDevice(Device device) {

        /*可以尝试使用spring aop实现*/
        switch (device.getDeviceStatusId()) {
            case "1" :
                device.setDeviceStatus("良好");
                break;
            case "2" :
                device.setDeviceStatus("故障");
            case "3" :
                device.setDeviceStatus("维修");
        }

        return deviceMapper.insert(device) == 1;
    }

    @Override
    public boolean insertDeviceType(DeviceType deviceType) {

        return deviceTypeMapper.insert(deviceType) == 1;
    }

    @Override
    public boolean insertDeviceCheck(DeviceCheck deviceCheck) {

        return deviceCheckMapper.insert(deviceCheck) == 1;
    }

    @Override
    public boolean insertDeviceFault(DeviceFault deviceFault) {

        return deviceFaultMapper.insert(deviceFault) == 1;
    }

    @Override
    public boolean insertDeviceMaintain(DeviceMaintain deviceMaintain) {

        return deviceMaintainMapper.insert(deviceMaintain) == 1;
    }

    @Override
    public boolean updateDevice(Device device) {

        return deviceMapper.updateByPrimaryKeySelective(device) == 1;
    }

    @Override
    public boolean updateDeviceType(DeviceType deviceType) {

        return deviceTypeMapper.updateByPrimaryKeySelective(deviceType) == 1;
    }

    @Override
    public boolean updateDeviceCheck(DeviceCheck deviceCheck) {

        return deviceCheckMapper.updateByPrimaryKeySelective(deviceCheck) == 1;
    }

    @Override
    public boolean updateDeviceFault(DeviceFault deviceFault) {

        return deviceFaultMapper.updateByPrimaryKeySelective(deviceFault) == 1;
    }

    @Override
    public boolean updateDeviceMaintain(DeviceMaintain deviceMaintain) {

        return deviceMaintainMapper.updateByPrimaryKeySelective(deviceMaintain) == 1;
    }

    @Override
    public boolean deleteDevice(String[] ids) {

        return deviceMapper.deleteDeviceByIds(ids) == ids.length;
    }

    @Override
    public boolean deleteDeviceType(String[] ids) {

        return deviceTypeMapper.deleteDeviceTypeByIds(ids) == ids.length;
    }

    @Override
    public boolean deleteDeviceCheck(String[] ids) {

        return deviceCheckMapper.deleteDeviceCheckByIds(ids) == ids.length;
    }

    @Override
    public boolean deleteDeviceFault(String[] ids) {

        return deviceFaultMapper.deleteDeviceFaultByIds(ids) == ids.length;
    }

    @Override
    public boolean deleteDeviceMaintain(String[] ids) {

        return deviceMaintainMapper.deleteDeviceMaintainByIds(ids) == ids.length;
    }

    @Override
    public ResponseVo searchResponseVo(String searchValue, int page, int rows, String searchMethod) {

        PageHelper.startPage(page, rows);

        ResponseVo responseVo = new ResponseVo<>();

        switch (searchMethod) {
            case "searchDeviceByDeviceId" :
                PageInfo<DeviceVo> deviceVoPageInfo = new PageInfo<>(
                        deviceMapper.searchDeviceByDeviceId(searchValue));

                responseVo.setTotal(deviceVoPageInfo.getTotal());
                responseVo.setRows((List) deviceVoPageInfo.getList());
                break;
            case "searchDeviceTypeByDeviceTypeId" :
                PageInfo<DeviceType> deviceTypePageInfo = new PageInfo<>(
                        deviceTypeMapper.searchDeviceTypeByDeviceTypeId(searchValue));

                responseVo.setTotal(deviceTypePageInfo.getTotal());
                responseVo.setRows((List) deviceTypePageInfo.getList());
                break;
            case "searchDeviceCheckByDeviceCheckId" :
                PageInfo<DeviceCheckVo> deviceCheckVoPageInfo = new PageInfo<>(
                        deviceCheckMapper.searchDeviceCheckVoByDeviceCheckId(searchValue));

                responseVo.setTotal(deviceCheckVoPageInfo.getTotal());
                responseVo.setRows((List) deviceCheckVoPageInfo.getList());
                break;
            case "searchDeviceFaultByDeviceFaultId" :
                PageInfo<DeviceFaultVo> deviceFaultVoPageInfo = new PageInfo<>(
                        deviceFaultMapper.searchDeviceFaultVoByDeviceFaultId(searchValue));

                responseVo.setTotal(deviceFaultVoPageInfo.getTotal());
                responseVo.setRows((List) deviceFaultVoPageInfo.getList());
                break;
            case "searchDeviceMaintainByDeviceMaintainId" :
                PageInfo<DeviceMaintainVo> deviceMaintainVoPageInfo = new PageInfo<>(
                        deviceMaintainMapper.searchDeviceMaintainVoByDeviceMaintainId(searchValue));

                responseVo.setTotal(deviceMaintainVoPageInfo.getTotal());
                responseVo.setRows((List) deviceMaintainVoPageInfo.getList());
                break;
            case "searchDeviceByDeviceName" :
                PageInfo<DeviceVo> deviceVoPage = new PageInfo<>(
                        deviceMapper.searchDeviceByDeviceName(searchValue));

                responseVo.setTotal(deviceVoPage.getTotal());
                responseVo.setRows((List) deviceVoPage.getList());
                break;
            case "searchDeviceTypeByDeviceTypeName" :
                PageInfo<DeviceType> deviceTypePage = new PageInfo<>(
                        deviceTypeMapper.searchDeviceTypeByDeviceTypeName(searchValue));

                responseVo.setTotal(deviceTypePage.getTotal());
                responseVo.setRows((List) deviceTypePage.getList());
                break;
            case "searchDeviceCheckByDeviceCheckName" :
                PageInfo<DeviceCheckVo> deviceCheckVoPage = new PageInfo<>(
                        deviceCheckMapper.searchDeviceCheckVoByDeviceCheckName(searchValue));

                responseVo.setTotal(deviceCheckVoPage.getTotal());
                responseVo.setRows((List) deviceCheckVoPage.getList());
                break;
            case "searchDeviceFaultByDeviceFaultName" :
                PageInfo<DeviceFaultVo> deviceFaultVoPage = new PageInfo<>(
                        deviceFaultMapper.searchDeviceFaultVoByDeviceFaultName(searchValue));

                responseVo.setTotal(deviceFaultVoPage.getTotal());
                responseVo.setRows((List) deviceFaultVoPage.getList());
                break;
            case "searchDeviceMaintainByDeviceFaultId" :
                PageInfo<DeviceMaintainVo> deviceMaintainVoPage = new PageInfo<>(
                        deviceMaintainMapper.searchDeviceMaintainByDeviceFaultId(searchValue));

                responseVo.setTotal(deviceMaintainVoPage.getTotal());
                responseVo.setRows((List) deviceMaintainVoPage.getList());
                break;
            case "searchDeviceByDeviceTypeName" :
                PageInfo<DeviceVo> deviceVoInfo = new PageInfo<>(
                        deviceMapper.searchDeviceByDeviceTypeName(searchValue));

                responseVo.setTotal(deviceVoInfo.getTotal());
                responseVo.setRows((List) deviceVoInfo.getList());
                break;
        }

        return responseVo;
    }

    @Override
    public boolean updateDeviceNoteById(String deviceId, String note) {

        return deviceMapper.updateDeviceNoteById(deviceId, note) == 1;
    }

    @Override
    public boolean updateDeviceCheckResultById(String deviceCheckId, String deviceCheckResult) {

        return deviceCheckMapper.updateDeviceCheckResultById(deviceCheckId, deviceCheckResult) == 1;
    }

    @Override
    public boolean updateDeviceFaultDetailById(String deviceFaultId, String deviceFaultDetail) {

        return deviceFaultMapper.updateDeviceFaultDetailById(deviceFaultId, deviceFaultDetail) == 1;
    }

    @Override
    public boolean updateDeviceMaintainNoteById(String deviceMaintainId, String note) {

        return deviceMaintainMapper.updateDeviceMaintainNoteById(deviceMaintainId, note) == 1;

    }


}
