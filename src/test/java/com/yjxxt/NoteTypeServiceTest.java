package com.yjxxt;

import com.yjxxt.pojo.NoteType;
import com.yjxxt.service.NoteTypeService;
import org.junit.Before;
import org.junit.Test;

public class NoteTypeServiceTest {
    private NoteTypeService noteTypeService =null;

    @Before
    public void init(){
        System.out.println("测试方法操作");
        noteTypeService =new NoteTypeService();

    }

    @Test
    public void addNoteType(){
        System.out.println("添加前");
        noteTypeService.listnoteType(1);
        noteTypeService.addNoteType(new NoteType(5,"ppp",1));
        System.out.println("添加后");
        noteTypeService.listnoteType(1);

    }

    @Test
    public void  listnoteType(){
        noteTypeService.listnoteType(1);
    }

    @Test
    public void updatenotetype(){
        System.out.println("更新前");
        noteTypeService.listnoteType(1);
        noteTypeService.updateNoteType(new NoteType(1,"java1",1));
        System.out.println("更新后");
        noteTypeService.listnoteType(1);
    }

}
