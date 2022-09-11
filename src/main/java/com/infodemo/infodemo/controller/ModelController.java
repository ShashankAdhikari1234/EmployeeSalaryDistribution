package com.infodemo.infodemo.controller;
import com.infodemo.infodemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class ModelController {
    private final EmployeeService employeeService;
    @GetMapping("/list")
    public String showEmployee(Model model){
    model.addAttribute("elist",employeeService.findAllEmployee());
        return "employee";
    }

}
