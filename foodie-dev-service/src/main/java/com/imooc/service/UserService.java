package com.imooc.service;


import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

public interface UserService {

    /**
     * charge user name is exist
     */
    public boolean queryUserNameIsExist(String userName);

    /**
     * charge user name is exist
     */
    public Users createUser(UserBO userBO);

    /**
     * 检索用户名密码是否匹配
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username,String password);
}
