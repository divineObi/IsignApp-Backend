package com.attendanceProject.Isign.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AttendanceSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Users getLecturer() {
        return lecturer;
    }

    public void setLecturer(Users lecturer) {
        this.lecturer = lecturer;
    }

    @ManyToOne // Many AttendanceSession records can belong to one User (Lecturer)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = false) // Maps to the User entity's ID
    private Users lecturer;

    private String courseTitle;
    private Integer durationMinutes;
    private String locationCoordinates;
    private String failsafeCode;
    private String attendanceCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    // Getters and setters

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}

