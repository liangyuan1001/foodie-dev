package com.imooc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    public List getSubCatList(Integer rootCatId);

    public List getSixNewItemLazy(@Param("paramsMap") Map<String , Object> map);

}