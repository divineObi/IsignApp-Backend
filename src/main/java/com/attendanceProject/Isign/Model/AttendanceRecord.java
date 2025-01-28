package com.attendanceProject.Isign.Model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "attendance_records")
    public class AttendanceRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Primary key for the attendance record

        @Column(name = "student_id", nullable = false)
        private Long studentId; // Foreign key referencing the "users" table

        @Column(name = "session_id", nullable = false)
        private Long sessionId; // Foreign key referencing the "attendance_sessions" table

        @Column(name = "attendance_time", nullable = false)
        private LocalDateTime attendanceTime; // Time the attendance was recorded

        // Getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public Long getSessionId() {
            return sessionId;
        }

        public void setSessionId(Long sessionId) {
            this.sessionId = sessionId;
        }

        public LocalDateTime getAttendanceTime() {
            return attendanceTime;
        }

        public void setAttendanceTime(LocalDateTime attendanceTime) {
            this.attendanceTime = attendanceTime;
        }
    }

