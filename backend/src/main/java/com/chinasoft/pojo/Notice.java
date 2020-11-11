package com.chinasoft.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Integer noticeId;
    private String title;
    private Integer noticeState;
    private Date date;
}
