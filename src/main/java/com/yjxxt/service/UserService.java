package com.yjxxt.service;

import com.yjxxt.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


/**
 * 云日记-用户管理模块
 *    用户登录
 *    用户注册
 *    用户列表展示
 *    用户信息更新
 *    用户信息删除
 */
public class UserService {
    /**
     * 无数据库
     *用户记录如何存储
     * list   map
     * set  list<map<k,v>>
     */
    private List<User> users;

    public UserService(){
        users=new ArrayList<User>();
        users.add(new User(1,"admin","123456","admin","",""));
        users.add(new User(2,"test0","123456","test0","",""));
        users.add(new User(3,"test1","123456","test1","",""));

    }

    /**
     * 添加用户操作
     *
     * 参数效验
     * 1：非空效验
     * 2：用户名唯一： & 昵称唯一
     * 3：执行添加  返回结果
     *
     */
    public void addUser(User user){
        /**
         * 1.参数合法校验
         *     用户名 密码 昵称 非空
         * 2.用户名唯一  & 昵称唯一
         * 3.执行添加 返回结果
         */
        if(null == user){
            throw  new RuntimeException("用户记录为空!");
        }
        if(StringUtils.isBlank(user.getUserName())){
            throw  new RuntimeException("用户名称不能为空!");
        }
        if(StringUtils.isBlank(user.getUserPwd())){
            throw  new RuntimeException("用户密码不能为空!");
        }
        /**
         * 用户名唯一校验
         */
        for (User temp : users) {
            if(temp.getUserName().equals(user.getUserName())){
                throw  new RuntimeException("用户名已存在!");
            }
        }
        if(users.contains(user)){
            throw  new RuntimeException("用户名已存在!");
        }
       long count = users.stream()
                            .filter(u->u.getUserName().equals(user.getUserName()))
                            .count();
       if(count==1){
           throw  new RuntimeException("用户名已存在!");
       }
        users.add(user);
    }

    public void listUser(){
        /**
         * 普通for
         * 增强for
         * Iterator迭代器
         *
         */
        for(int i=0;i<users.size();i++){
            System.out.println(users.get(i));
        }
//        for(User uu:users){
//            System.out.println(uu);
//        }
//        Iterator<User> iterator= users.listIterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
    }

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     */
    public void login(String userName,String userPwd){
        /**
         * 1:判空
         *    用户名 密码非空
         * 2：根据用户名查找记录
         * 3：记录存在判断
         *    不存在-->抛异常
         * 4：用户存在
         *     用户密码比较
         *       不正确-->抛异常
         * 5：密码正确
         *     登录成功
         */
        if(StringUtils.isBlank(userName)){
            throw new RuntimeException("用户名不能为空");
        }
        if(StringUtils.isBlank(userPwd)){
            throw  new RuntimeException("密码不能为空");
        }
        Integer index=null;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(userName)){
                index=i;
                break;
            }
        }
        if(index==null){
            throw new RuntimeException("用户记录不存在");
        }
        User temp=users.get(index);


        Optional<User> first = users.stream().filter(u -> u.getUserName().equals(userName))
                .findFirst();
        if(!(first.isPresent())){
            throw  new RuntimeException("用户记录不存在");
        }

        if(!temp.getUserPwd().equals(userPwd)){
            throw new RuntimeException("用户密码输入有误");
        }
        System.out.println("登录成功");

    }



    /**
     * 1：用户名   密码  昵称非空
     * 2：根据id  查询用户记录是否存在
     * 3：记录存在  判断用户名 密码是否有重复
     * @param user
     */
    public void update(User user){
        /**
         * 1。效验
         *    用户名  密码  昵称是否为空
         * 2.根据id  查询用户记录是否存在
         *     不存在，抛出异常
         * 3.记录存在，判断用户名 昵称是否重复
         *      用户名唯一效验
         *      昵称唯一效验
         * 4；执行更新操作
         */
        if(null ==user)
        {
            throw new RuntimeException("用户不存在");
        }
        if(null==user.getUserName()){
            throw new RuntimeException("用户名不能为空");
        }
        if(null==user.getUserPwd()){
            throw new RuntimeException("密码不能为空");
        }
        if(null==user.getNick()){
            throw new RuntimeException("用户昵称不能为空");
        }
        if(user.getId()==null || null==findUserByUserId(user.getId())){
            throw new RuntimeException("待更新的记录不存在");
        }
        /**
         * 用户名改动
         *   改动前：test
         *   改动后：
         *      abc  count=0
         *      test count=1
         *      admin count=1
         */
        long count=users.stream().filter(u->u.getUserName().equals(user.getUserName()))
                .filter(u->!(u.getId().equals(user.getId())))
                .count();
        if(count==1){
            throw new RuntimeException("用户名已经存在");
        }
        //执行更新  根据id查找
        users.set(users.indexOf(findUserByUserId(user.getId())),user);


    }

    public User findUserByUserId(Integer userId){
        Integer  index=null;
        for(int i=0;i<users.size();i++){
              if(userId==users.get(i).getId()){
                  index=i;
                  break;
              }
        }
        return index==null?null:users.get(index);
    }

    public void delUser(Integer userId){
        /**
         * id唯一   用户名唯一  昵称唯一
         * 1、判断用户记录是否存在
         *    不存在，抛异常
         * 2.存在，执行删除
         */
        User user = findUserByUserId(userId);
        if(user==null){
            throw new RuntimeException("待删除记录不存在");
        }
        users.remove(user);

    }
}
