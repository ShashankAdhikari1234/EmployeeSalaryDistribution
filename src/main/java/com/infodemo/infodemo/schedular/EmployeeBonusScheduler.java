package com.infodemo.infodemo.schedular;

import com.infodemo.infodemo.entity.Employee;
import com.infodemo.infodemo.entity.Log;
import com.infodemo.infodemo.repo.EmployeeRepo;
import com.infodemo.infodemo.repo.LogRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class EmployeeBonusScheduler {
    private final EmployeeRepo employeeRepo;
    private final LogRepo logRepo;

    public EmployeeBonusScheduler(EmployeeRepo employeeRepo, LogRepo logRepo) {
        this.employeeRepo = employeeRepo;
        this.logRepo = logRepo;
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void employeeBonusScheduler() {
        List<Employee> all = employeeRepo.findAll();
        for (Employee employee : all) {
            Integer months = employee.getBonusDistributionDate().until(LocalDate.now()).getMonths();
            System.out.println(months);
            if(months < 3){
                continue;
            }
            int period = employee.getDateOfJoin().until(LocalDate.now()).getYears();
            Long salary = employee.getSalary();
            if (period < 2) {
                Long upgradedSalary = salary + (long) (salary * 0.1);
                employee.setSalary(upgradedSalary);
            } else if (period < 4) {
                Long upgradedSalary = salary + (long) (salary * 0.15);
                employee.setSalary(upgradedSalary);
            } else if (period < 6) {
                Long upgradedSalary = salary + (long) (salary * 0.2);
                employee.setSalary(upgradedSalary);

            } else {
                Long upgradedSalary = salary + (long) (salary * 0.25);
                employee.setSalary(upgradedSalary);
            }
            employee.setBonusDistributionDate(LocalDate.now());
            employeeRepo.save(employee);
            Log log = new Log();
            log.setLogTime(LocalDateTime.now());
            log.setLog("Salary of "+ employee.getFullName() + " incremented. Current salary: "+ employee.getSalary());
            log.setType("salary-incremented");
            logRepo.save(log);
            System.out.println("Salary increment of " + employee.getFullName() + ". Salary: "+employee.getSalary());
            System.out.println("*****************************************************************");
        }

    }
}
