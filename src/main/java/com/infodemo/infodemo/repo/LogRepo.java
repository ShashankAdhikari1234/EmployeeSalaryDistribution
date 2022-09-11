package com.infodemo.infodemo.repo;

import com.infodemo.infodemo.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepo extends JpaRepository<Log,Integer> {

    List<Log> getLogByType(String type);
}
