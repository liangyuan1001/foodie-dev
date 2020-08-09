package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.ItemInfoVo;
import com.imooc.pojo.vo.NewItemsVo;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "商品接口",tags = {"用于商品信息展示的相关接口"})
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情",notes = "查询商品详情" ,httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(@ApiParam(name = "itemId",value = "一级分类id",required = true)
                                @PathVariable String itemId){
        if(itemId == null){
            return IMOOCJSONResult.errorMsg(null);
        }
        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList =  itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        ItemInfoVo itemInfoVo = new ItemInfoVo();
        itemInfoVo.setItem(item);
        itemInfoVo.setItemImgList(itemsImgList);
        itemInfoVo.setItemSpecList(itemsSpecList);
        itemInfoVo.setItemParams(itemsParam);
        return IMOOCJSONResult.ok(itemInfoVo);
    }

}
