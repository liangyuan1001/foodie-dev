package com.imooc.service.impl;

import com.imooc.mapper.CategoryMapper;
import com.imooc.mapper.CategoryMapperCustom;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVo;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;


    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",1);
        List<Category> result = categoryMapper.selectByExample(example);
        return result;
    }

    @Override
    public List<CategoryVO> getSubCatList(int rootCatId) {
        List<CategoryVO> list = categoryMapperCustom.getSubCatList(rootCatId);

        return list;
    }

    @Override
    public List<NewItemsVo> getSixNewItemLazy(Integer rootCatId) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("rootCatId",rootCatId);
        List<NewItemsVo> list = categoryMapperCustom.getSixNewItemLazy(paramMap);
        return list;
    }
}
