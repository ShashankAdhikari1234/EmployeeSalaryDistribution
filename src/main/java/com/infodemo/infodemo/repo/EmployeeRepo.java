package com.infodemo.infodemo.repo;

import com.infodemo.infodemo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    @Query(nativeQuery = true, value = "select * from employee")
    List<Employee> findAllEmployeeWithPageable(Pageable pageable);

}
