package com.attendanceProject.Isign.Repo;


import com.attendanceProject.Isign.Model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    boolean existsByStudentIdAndSessionId(Long studentId, Long sessionId);

    @Query("SELECT ar.studentId, u.name, u.regnumber, ar.attendanceTime " +
            "FROM AttendanceRecord ar " +
            "JOIN Users u ON ar.studentId = u.id " +
            "WHERE ar.sessionId = :sessionId")
    List<Object[]> findAllBySessionIdWithStudentDetails(@Param("sessionId") Long sessionId);

    @Query("SELECT s.courseTitle, ar.attendanceTime " +
            "FROM AttendanceRecord ar " +
            "JOIN AttendanceSession s ON ar.sessionId = s.id " +
            "WHERE ar.studentId = :studentId")
    List<Object[]> findAttendanceHistoryByStudentId(@Param("studentId") Long studentId);

        List<AttendanceRecord> findBySessionId(Long sessionId);


}

