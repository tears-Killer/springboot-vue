package com.chinasoft.mapper;

import com.chinasoft.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeMapper {

    List<Notice> selectAllNotice();

    int update(@Param("noticeState")Integer noticeState,@Param("noticeId")Integer noticeId);

    int delete(@Param("noticeId")Integer noticeId);

    int addNotice(@Param("notice")Notice notice);
}
