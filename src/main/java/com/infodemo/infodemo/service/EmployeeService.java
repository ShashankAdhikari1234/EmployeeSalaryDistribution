package com.infodemo.infodemo.service;

import com.infodemo.infodemo.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> importData();


    List<EmployeeDto> findAllEmployeeWithPageable(int page, int size);

    List<EmployeeDto> findAllEmployee();

}
