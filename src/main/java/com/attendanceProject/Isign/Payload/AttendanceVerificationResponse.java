package com.attendanceProject.Isign.Payload;

public class AttendanceVerificationResponse {
        private boolean success;
        private String message;
        private String courseTitle;
        private Long sessionId;

        public AttendanceVerificationResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public AttendanceVerificationResponse(boolean success, String message, String courseTitle, Long sessionId) {
            this.success = success;
            this.message = message;
            this.courseTitle = courseTitle;
            this.sessionId = sessionId;
        }

        // Getters and setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
