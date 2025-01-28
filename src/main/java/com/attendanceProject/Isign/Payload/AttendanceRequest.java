package com.attendanceProject.Isign.Payload;

public class AttendanceRequest {
        private String courseTitle;
        private Integer durationMinutes;
        private Long locationId;
        private String failsafeCode;
        private Long lecturerId;

        // Getters and setters

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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getFailsafeCode() {
        return failsafeCode;
    }

    public void setFailsafeCode(String failsafeCode) {
        this.failsafeCode = failsafeCode;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }
}
