package com.chinasoft.controller;

import com.chinasoft.pojo.Notice;
import com.chinasoft.service.NoticeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/getAll")
    public List<Notice> getAllNotice(){
        List<Notice> noticeList = noticeService.getAllNotice();
        return noticeList;
    }

    @PostMapping("/update")
    public Integer updateState(@RequestParam("noticeState")Integer noticeState, @Param("noticeId")Integer noticeId){
         return noticeService.update(noticeState, noticeId);
    }

    @PostMapping("/updateAll")
    public void updateAllState(@RequestBody List<Notice> notices){
        noticeService.updateAllState(notices);
    }

    @PostMapping("/deleteAll")
    public void deleteAllNotice(@RequestBody List<Notice> notices){
        noticeService.deleteAllNotice(notices);
    }

    @PostMapping("/add")
    public void addNotice(@RequestBody Notice notice){
        noticeService.addNotice(notice);
    }
}
