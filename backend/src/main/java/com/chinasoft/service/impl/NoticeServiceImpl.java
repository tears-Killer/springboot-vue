package com.chinasoft.service.impl;

import com.chinasoft.mapper.NoticeMapper;
import com.chinasoft.pojo.Notice;
import com.chinasoft.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getAllNotice() {
        return noticeMapper.selectAllNotice();
    }

    @Override
    public int update(Integer noticeState, Integer noticeId) {
        return noticeMapper.update(noticeState,noticeId);
    }

    @Override
    public void updateAllState(List<Notice> notices) {
        for (int i = 0; i < notices.size(); i++) {
            noticeMapper.update(notices.get(i).getNoticeState(),notices.get(i).getNoticeId());
        }
    }

    @Override
    public void deleteAllNotice(List<Notice> notices) {
        for (int i = 0; i < notices.size(); i++) {
            noticeMapper.delete(notices.get(i).getNoticeId());
        }
    }

    @Override
    public void addNotice(Notice notice) {
        noticeMapper.addNotice(notice);
    }

}
