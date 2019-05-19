package com.xkazxx.mapper;

import com.xkazxx.bean.UnqualifyApply;
import com.xkazxx.bean.UnqualifyProduct;

import java.util.List;

public interface UnqualifyApplyMapper {
    int deleteByPrimaryKey(String unqualifyApplyId);

    int insert(UnqualifyApply record);

    int insertSelective(UnqualifyApply record);

    UnqualifyApply selectByPrimaryKey(String unqualifyApplyId);

    int updateByPrimaryKeySelective(UnqualifyApply record);

    int updateByPrimaryKey(UnqualifyApply record);

    List<UnqualifyProduct> findAllUnqualifyProducts();
}