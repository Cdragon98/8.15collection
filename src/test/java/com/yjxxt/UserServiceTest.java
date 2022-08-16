package com.yjxxt;

import com.yjxxt.pojo.User;
import com.yjxxt.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService=null;

    @Before
    public void init(){
        System.out.println("测试方法操作");
        userService=new UserService();
    }

    @Test
    public void addUser(){
        userService.listUser();
        userService.addUser(new User(3,"admin1","123456","admin","",""));
        userService.listUser();

    }


    @Test
    public void listUser(){
        userService.listUser();
    }

    @Test
    public void login(){
        userService.login("admin","12346");
    }
    @Test
    public void updateUser(){
        System.out.println("记录更新前");
        userService.listUser();
        userService.update(new User(2,"abc","123456","test","test",""));
        System.out.println("记录更新后");
        userService.listUser();
    }


    @Test
    public void delUser(){
        userService.delUser(3);
    }
}
