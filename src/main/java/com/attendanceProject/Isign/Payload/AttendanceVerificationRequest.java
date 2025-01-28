package com.attendanceProject.Isign.Payload;

public class AttendanceVerificationRequest {
        private String attendanceCode;
        private Long studentId;

        // Getters and setters

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
