package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录",tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在" ,httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    private IMOOCJSONResult userNameIsExist(@RequestParam String username){
        //1.判断入参用户名是否为空
        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空！");
        }
        //2.查找注册的用户名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在！");
        }
        //请求成功，用户名没有重复
        return IMOOCJSONResult.ok();
    }
    @ApiOperation(value = "用户注册",notes = "用户注册" ,httpMethod = "POST")
    @PostMapping("/regist")
    private IMOOCJSONResult register(@RequestBody UserBO userBO){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();


        //1.判断入参用户名是否为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空！");
        }
        //2.查找注册的用户名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在！");
        }
        //3.密码长度不能少于六位
        if(password.length() < 6){
            return IMOOCJSONResult.errorMsg("密码长度不能小于6！");
        }
        //4.两次密码一致
        if(!password.equals(confirmPassword)){
            return IMOOCJSONResult.errorMsg("两次密码不一致！");
        }
        //实现注册
        Users user = userService.createUser(userBO);
        return IMOOCJSONResult.ok();
    }
    @ApiOperation(value = "用户登录",notes = "用户登录" ,httpMethod = "POST")
    @PostMapping("/login")
    private IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();


        //1.判断入参用户名是否为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空！");
        }
        //实现登录

        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        if(userResult == null){
            return IMOOCJSONResult.errorMsg("用户名或密码不正确！");
        }
        setNullProperty(userResult);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(userResult),true);

        return IMOOCJSONResult.ok(userResult);
    }
    private Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
