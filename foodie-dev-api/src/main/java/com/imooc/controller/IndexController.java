package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVo;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@Api(value = "首页",tags = {"用于首页展示的相关接口"})
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表",notes = "获取首页轮播图列表" ,httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel(){
        List<Carousel> carouselList = carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(carouselList);
    }

    /**
     * 首页分类需求分析：
     * 1.第一次刷新主页查询大分类，渲染到展示页面
     * 2.如果鼠标移动到大分类，则加载其子分类内容，如果已经存在子分类，则不需要加载(懒加载)
     * @return
     */
    @ApiOperation(value = "用于获取商品分类（一级分类）",notes = "用于获取商品分类（一级分类）" ,httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats(){
        List<Category> list = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(list);
    }
    /**
     * 首页分类需求分析：
     * 1.第一次刷新主页查询大分类，渲染到展示页面
     * 2.如果鼠标移动到大分类，则加载其子分类内容，如果已经存在子分类，则不需要加载(懒加载)
     * @return
     */
    @ApiOperation(value = "用于获取一级分类的子分类",notes = "用于获取一级分类的子分类" ,httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")//路径参数
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable
            Integer rootCatId){
        if(rootCatId == null){
            return IMOOCJSONResult.errorMsg("");
        }
        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
    @ApiOperation(value = "用于获取每个大类最新的六个商品",notes = "用于获取每个大类最新的六个商品" ,httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")//路径参数
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable
                    Integer rootCatId){
        if(rootCatId == null){
            return IMOOCJSONResult.errorMsg("");
        }
        List<NewItemsVo> list = categoryService.getSixNewItemLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
}
