package com.infodemo.infodemo.service;

import com.infodemo.infodemo.dto.EmployeeDto;
import com.infodemo.infodemo.entity.Employee;
import com.infodemo.infodemo.entity.Log;
import com.infodemo.infodemo.repo.EmployeeRepo;
import com.infodemo.infodemo.repo.LogRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final LogRepo logRepo;
    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(LogRepo logRepo, EmployeeRepo employeeRepo) {
        this.logRepo = logRepo;
        this.employeeRepo = employeeRepo;
    }
    @Override
    public List<EmployeeDto> importData() {
        String fileLocation = "e://employee.csv";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        File file = new File(fileLocation);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<String> collect = bufferedReader.lines().collect(Collectors.toList());
            Log log = new Log();
            log.setLogTime(LocalDateTime.now());
            log.setLog("data-imported");
            log.setType("imported");
            logRepo.save(log);
            System.out.println(collect);
            for (int i = 0; i < collect.size(); i++) {
                String e = collect.get(i);
                String[] split = e.split(",");
                Employee employee = new Employee();
                employee.setFullName(split[0]);
                employee.setMobileNumber(split[1]);
                employee.setLocation(split[2]);
                employee.setDateOfJoin(LocalDate.parse(split[3], dateTimeFormatter));
                employee.setSalary(Long.parseLong(split[4]));
                employee.setDesignation(split[5]);
                employee.setBonusDistributionDate(LocalDate.now());
                try {
                    employeeRepo.save(employee);
                    Log log1 = new Log();
                    log1.setLogTime(LocalDateTime.now());
                    log1.setLog("Data saved: "+employee);
                    log1.setType("saved");
                    logRepo.save(log1);
                }catch (Exception ex){
                    Log log2 = new Log();
                    log2.setType("database-error");
                    log2.setLogTime(LocalDateTime.now());
                    log2.setLog("Unable to save data in database: "+ employee);
                    logRepo.save(log2);
                }
            }
        } catch (Exception e) {
            Log log = new Log();
            log.setLogTime(LocalDateTime.now());
            log.setLog("Unable to read file");
            log.setType("import-error");
            logRepo.save(log);
        }

        return employeeRepo.findAll().stream().map(employee -> new EmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> findAllEmployeeWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Employee> allEmployee = employeeRepo.findAllEmployeeWithPageable(pageable);
        return allEmployee.stream().map(employee -> new EmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> findAllEmployee() {
        return employeeRepo.findAll().stream().map(employee -> new EmployeeDto(employee)).collect(Collectors.toList());
    }
}