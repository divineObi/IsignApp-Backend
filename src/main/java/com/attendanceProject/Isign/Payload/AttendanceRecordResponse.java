package com.attendanceProject.Isign.Payload;

import java.time.LocalDateTime;

public class AttendanceRecordResponse {
    private Long studentId;
    private String studentName;
    private String regNumber;
    private LocalDateTime attendanceTime;

    public AttendanceRecordResponse(Long studentId, String studentName, String regNumber, LocalDateTime attendanceTime) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.regNumber = regNumber;
        this.attendanceTime = attendanceTime;
    }

    // Getters and setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public LocalDateTime getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(LocalDateTime attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}

