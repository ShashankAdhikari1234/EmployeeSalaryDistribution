package com.infodemo.infodemo.dto;

import com.infodemo.infodemo.entity.Log;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogDto {

    private Integer id;

    private LocalDateTime logTime;

    private String log;

    private String type;

    public LogDto(Log log){
        this.id = log.getId();
        this.logTime = log.getLogTime();
        this.log = log.getLog();
        this.type = log.getType();
    }
}
