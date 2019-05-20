package com.xkazxx.mapper;

import com.xkazxx.bean.Employee;
import com.xkazxx.vo.EmployeeAndDepartmentVo;

import java.util.List;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String empId);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    /*根据人员id返回EmployeeAndDepartmentVo*/
    EmployeeAndDepartmentVo selectEmployeeAndDepartmentById(String empId);

    List<EmployeeAndDepartmentVo> selectEmployeeAndDepartment();
}
