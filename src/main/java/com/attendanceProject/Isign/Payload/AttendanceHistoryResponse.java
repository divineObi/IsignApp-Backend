package com.attendanceProject.Isign.Payload;

import java.time.LocalDateTime;

public class AttendanceHistoryResponse {
    private String courseTitle;
    private LocalDateTime attendanceTime;

    public AttendanceHistoryResponse(String courseTitle, LocalDateTime attendanceTime) {
        this.courseTitle = courseTitle;
        this.attendanceTime = attendanceTime;
    }

    // Getters and setters

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public LocalDateTime getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(LocalDateTime attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}

