package com.attendanceProject.Isign.Repo;

import com.attendanceProject.Isign.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {
    List<Location> findAll();
}

