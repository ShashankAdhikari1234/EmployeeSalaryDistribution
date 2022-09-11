package com.infodemo.infodemo.dto;

import com.infodemo.infodemo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer id;

    private String fullName;

    private String mobileNumber;

    private LocalDate dateOfJoin;

    private  Long salary;

    private String designation;

    public EmployeeDto(Employee employee){
        this.id = employee.getId();
        this.fullName = employee.getFullName();
        this.mobileNumber = employee.getMobileNumber();
        this.salary = employee.getSalary();
        this.dateOfJoin = employee.getDateOfJoin();
        this.designation = employee.getDesignation();
    }
}
