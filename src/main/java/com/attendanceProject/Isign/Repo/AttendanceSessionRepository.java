package com.attendanceProject.Isign.Repo;

import com.attendanceProject.Isign.Model.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {
    Optional<AttendanceSession> findByAttendanceCodeOrFailsafeCode(String attendanceCode, String failsafeCode);
        List<AttendanceSession> findAll();

    List<AttendanceSession> findByLecturer_Id(Long lecturerId);

}

