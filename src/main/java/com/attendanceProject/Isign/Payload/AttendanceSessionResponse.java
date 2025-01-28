package com.attendanceProject.Isign.Payload;
import com.attendanceProject.Isign.Model.AttendanceSession;

import java.time.LocalDateTime;

public class AttendanceSessionResponse {
        private Long id;
        private String courseTitle;
        private Integer durationMinutes;
        private String locationCoordinates;
        private String failsafeCode;
        private String attendanceCode;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;


        // Constructor to map the entity fields
        public AttendanceSessionResponse(AttendanceSession session) {
            this.id = session.getId();
            this.courseTitle = session.getCourseTitle();
            this.durationMinutes = session.getDurationMinutes();
            this.locationCoordinates = session.getLocationCoordinates();
            this.failsafeCode = session.getFailsafeCode();
            this.attendanceCode = session.getAttendanceCode();
            this.createdAt = session.getCreatedAt();
            this.expiresAt = session.getExpiresAt();
        }

        // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    public String getFailsafeCode() {
        return failsafeCode;
    }

    public void setFailsafeCode(String failsafeCode) {
        this.failsafeCode = failsafeCode;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }

}


