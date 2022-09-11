package com.infodemo.infodemo.service;

import com.infodemo.infodemo.dto.LogDto;

import java.util.List;

public interface LogService {

    List<LogDto> getLogByType(String type);
}
