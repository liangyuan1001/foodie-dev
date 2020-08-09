package com.imooc.service;


import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVo;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    public List<Category> queryAllRootLevelCat();

    /**
     * 查询每个主分类的子分类
     */
    public List<CategoryVO> getSubCatList(int rootCatId);

    /**
     * 查询首页六条最新商品数据
     * @param rootCatId
     * @return
     */
    public List<NewItemsVo> getSixNewItemLazy(Integer rootCatId);
}
