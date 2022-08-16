package com.yjxxt.service;

import com.yjxxt.pojo.NoteType;
import com.yjxxt.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NoteTypeService {
    /**
     * 云记类别管理
     *     云记类别遍历
     *     云记类别添加
     *     云记类别更新
     *     云记类别删除
     */
    private Map<Integer, NoteType> noteTypeMap;
    private UserService userService=new UserService();


    public NoteTypeService() {
        noteTypeMap=new HashMap<Integer,NoteType>();
        noteTypeMap.put(1,new NoteType(1,"java",1));
        noteTypeMap.put(2,new NoteType(2,"python",1));
        noteTypeMap.put(3,new NoteType(3,"Php",1));

    }

    //添加操作
    public void addNoteType(NoteType noteType){
        /**
         * 判断noteType是否为空
         * 判断typeName是否为空
         * 唯一性效验
         */
        if(null==noteType){
            throw new RuntimeException("云记对象不能为空");
        }
        if(null==noteType.getTypeName()) {
            throw new RuntimeException("云记类型不能为空");
        }
        //判断userid要存在
        if(userService.findUserByUserId(noteType.getUserId())==null){
            throw new RuntimeException("userid不存在");
        }
        //id不能存在
        for(Map.Entry<Integer,NoteType> integerNoteTypeEntry:noteTypeMap.entrySet())
        {
            if(integerNoteTypeEntry.getValue().getId().equals(noteType.getId())){
                throw new RuntimeException("id已经和别人的重复");
            }
        }
        for(Map.Entry<Integer,NoteType> integerNoteTypeEntry:noteTypeMap.entrySet())
        {
            if(integerNoteTypeEntry.getValue().getTypeName()==noteType.getTypeName()){
                throw new RuntimeException("typeName已经和别人的重复");
            }
        }
        int id=4;
        noteTypeMap.put(id++,noteType);


    }

    //遍历集合操作
    public void listnoteType(Integer userId){
        for (Map.Entry<Integer, NoteType> integerNoteTypeEntry : noteTypeMap.entrySet()) {
            if (integerNoteTypeEntry.getValue().getUserId().equals(userId)){
                System.out.println(integerNoteTypeEntry);
            }
        }
//        Iterator<NoteType> noteTypeIterator=noteTypeMap.values().iterator();
//        while (noteTypeIterator.hasNext()){
//            NoteType noteType=noteTypeIterator.next();
//            if(noteType.getUserId().equals(userId))
//            {
//                System.out.println(noteType);
//            }
//        }
        noteTypeMap.values().stream().filter(n->n.getUserId().equals(userId)).forEach(n->{
            System.out.println(n);
        });
    }

    public void  updateNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空
         *    用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         *    云记类别id 必须存在
         * 2.当前用户下类别名称不可重复
         * 3.执行更新
         */

        if(StringUtils.isBlank(noteType.getTypeName())){
            throw new RuntimeException("类别名不能为空");
        }
        if(null==noteType.getId()){
            throw new RuntimeException("云记id不存在");
        }
        if(userService.findUserByUserId(noteType.getUserId())==null){
            throw new RuntimeException("用户id不能为空");
        }
        for(Map.Entry<Integer,NoteType> integerNoteTypeEntry:noteTypeMap.entrySet())
        {
            if(integerNoteTypeEntry.getValue().getTypeName().equals(noteType.getTypeName())){
                throw new RuntimeException("名字已经和别人的重复");
            }
        }
        //按照key遍历
         Set keyset=noteTypeMap.keySet();
//        for(Object key:keyset){
//            noteTypeMap.replace((Integer)key,noteTypeMap.get(key),noteType);
//        }
        for(Object key:keyset) {
            for (Map.Entry<Integer, NoteType> integerNoteTypeEntry : noteTypeMap.entrySet()) {
                if (integerNoteTypeEntry.getValue().getId().equals(noteType.getId())) {
                    noteTypeMap.replace((Integer) key,noteType);
                }
            }
        }

    }

    public void delNoteType(Integer noteTypeId){
        /**
         * 1.参数校验
         *    云记类别id 必须存在
         * 2.如果类别下存在云记记录 不允许删除
         * 3.执行删除
         */
    }

}
