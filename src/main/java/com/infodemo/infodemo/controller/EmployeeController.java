package com.infodemo.infodemo.controller;

import com.infodemo.infodemo.dto.EmployeeDto;
import com.infodemo.infodemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/import")
    public ResponseEntity<List<EmployeeDto>> importData(){
        return ResponseEntity.ok(employeeService.importData());
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseEntity<List<EmployeeDto>> findAllEmployee(@PathVariable int pageNo, @PathVariable int pageSize){
        return ResponseEntity.ok(employeeService.findAllEmployeeWithPageable(pageNo,pageSize));
    }


}
