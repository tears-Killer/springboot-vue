package com.chinasoft.service;

import com.chinasoft.pojo.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getAllNotice();

    int update(Integer noticeState,Integer noticeId);

    public void updateAllState(List<Notice> notices);

    public void deleteAllNotice(List<Notice> notices);

    public void addNotice(Notice notice);
}
