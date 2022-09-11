package com.infodemo.infodemo.controller;

import com.infodemo.infodemo.dto.LogDto;
import com.infodemo.infodemo.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {

    private final LogService logService;

    @GetMapping("/{type}")
    public ResponseEntity<List<LogDto>> getLogByType(@PathVariable String type){
        return ResponseEntity.ok(logService.getLogByType(type));
    }
}
