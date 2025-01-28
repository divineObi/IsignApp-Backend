package com.attendanceProject.Isign.Repo;

import com.attendanceProject.Isign.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
       Users findByRegnumber(String regnumber);

        @Query("SELECT u FROM Users u WHERE u.id = :id")
        Optional<Users> findById(@Param("id") Long id);

}
