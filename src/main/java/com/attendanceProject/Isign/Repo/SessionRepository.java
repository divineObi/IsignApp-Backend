package com.attendanceProject.Isign.Repo;
import com.attendanceProject.Isign.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT s.id, s.courseTitle, s.creationDate FROM Session s")
    List<Object[]> findAllSessions();
}

