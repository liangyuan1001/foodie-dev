package com.imooc.service;


import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVo;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品id查询详情
     * @param id
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询图片
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);

}
