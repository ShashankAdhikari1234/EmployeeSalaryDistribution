package com.infodemo.infodemo.service;

import com.infodemo.infodemo.dto.LogDto;
import com.infodemo.infodemo.entity.Log;
import com.infodemo.infodemo.repo.LogRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService{

    private final LogRepo logRepo;

    public LogServiceImpl(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public List<LogDto> getLogByType(String type) {
        List<Log> logByType = logRepo.getLogByType(type);
        return logByType.stream().map(log-> new LogDto(log)).collect(Collectors.toList());
    }
}
