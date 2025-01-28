package com.attendanceProject.Isign.Payload;

import java.time.LocalDateTime;

public class SessionResponse {
    private Long sessionId;
    private String courseTitle;
    private LocalDateTime creationDate;

    public SessionResponse(Long sessionId, String courseTitle, LocalDateTime creationDate) {
        this.sessionId = sessionId;
        this.courseTitle = courseTitle;
        this.creationDate = creationDate;
    }

    // Getters and setters

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

