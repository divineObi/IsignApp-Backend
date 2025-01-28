package com.attendanceProject.Isign.Service;

import com.attendanceProject.Isign.Model.AttendanceRecord;
import com.attendanceProject.Isign.Model.AttendanceSession;
import com.attendanceProject.Isign.Model.Location;
import com.attendanceProject.Isign.Model.Users;
import com.attendanceProject.Isign.Payload.*;
import com.attendanceProject.Isign.Repo.AttendanceRecordRepository;
import com.attendanceProject.Isign.Repo.AttendanceSessionRepository;
import com.attendanceProject.Isign.Repo.LocationRepo;
import com.attendanceProject.Isign.Repo.UserRepo;
import com.attendanceProject.Isign.Utilities.GeoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceSessionRepository sessionRepository;

    @Autowired
    private LocationRepo locationRepository;

    @Autowired
    private AttendanceRecordRepository recordRepository;

    @Autowired
    private GeoUtil geoUtils;

    @Autowired
    private UserRepo userRepo;

    private static final double GEOFENCE_RADIUS_KM = 0.1; // 100 meters (0.1 km)


    public AttendanceSession createSession(AttendanceRequest request) {
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid location ID"));

        // Fetch the lecturer from the Users repository
        Users lecturer = userRepo.findById(request.getLecturerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid lecturer ID"));


        AttendanceSession session = new AttendanceSession();
        session.setCourseTitle(request.getCourseTitle());
        session.setDurationMinutes(request.getDurationMinutes());
        session.setLocationCoordinates(location.getLatitude() + "," + location.getLongitude());
        session.setFailsafeCode(request.getFailsafeCode());
        session.setAttendanceCode(generateUniqueCode());
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusMinutes(request.getDurationMinutes()));

        // Set the lecturer for the session
        session.setLecturer(lecturer);

        return sessionRepository.save(session);
    }


    private String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public AttendanceVerificationResponse verifyAttendanceCode(AttendanceVerificationRequest request) {
        // Check if the session exists and is still active
        AttendanceSession session = sessionRepository
                .findByAttendanceCodeOrFailsafeCode(request.getAttendanceCode(), request.getAttendanceCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid attendance code"));

        if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Attendance session has expired");
        }

        // Return session details
        return new AttendanceVerificationResponse(
                true,
                "Attendance session is valid",
                session.getCourseTitle(),
                session.getId()
        );
    }

    public ApiResponse submitAttendance(AttendanceSubmissionRequest request) {
        // Fetch the attendance session
        AttendanceSession session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid session ID"));

        // Check if the submitted code is the failsafe code
        if (request.getAttendanceCode() != null && request.getAttendanceCode().equals(session.getFailsafeCode())) {
            // Bypass geofencing
            return markAttendance(request.getStudentId(), request.getSessionId());
        }

        // Get the lecturer's location coordinates
        String[] locationCoordinates = session.getLocationCoordinates().split(",");
        double lecturerLatitude = Double.parseDouble(locationCoordinates[0]);
        double lecturerLongitude = Double.parseDouble(locationCoordinates[1]);

        // Calculate the distance between student and lecturer
        double distance = GeoUtil.calculateDistance(
                lecturerLatitude, lecturerLongitude,
                request.getStudentLatitude(), request.getStudentLongitude());

        // Check if the student is within the geofence radius
        if (distance > GEOFENCE_RADIUS_KM) {
            return new ApiResponse(false, "You are outside the attendance geofence area");
        }

        // Check if attendance already exists for the student and session
        return markAttendance(request.getStudentId(), request.getSessionId());
    }

    // Helper method to mark attendance
    private ApiResponse markAttendance(Long studentId, Long sessionId) {
        boolean alreadyMarked = recordRepository.existsByStudentIdAndSessionId(studentId, sessionId);
        if (alreadyMarked) {
            return new ApiResponse(false, "Attendance already recorded");
        }

        // Record attendance
        AttendanceRecord record = new AttendanceRecord();
        record.setSessionId(sessionId);
        record.setStudentId(studentId);
        record.setAttendanceTime(LocalDateTime.now());
        recordRepository.save(record);

        return new ApiResponse(true, "Attendance recorded successfully");
    }



    public List<AttendanceRecordResponse> getAttendanceRecords(Long sessionId) {
        List<Object[]> records = recordRepository.findAllBySessionIdWithStudentDetails(sessionId);

        if (records.isEmpty()) {
            throw new IllegalArgumentException("No attendance records found for this session.");
        }

        // Map the query result to the DTO
        return records.stream()
                .map(record -> new AttendanceRecordResponse(
                        (Long) record[0],        // studentId
                        (String) record[1],     // studentName
                        (String) record[2],     // regNumber
                        (LocalDateTime) record[3] // attendanceTime
                ))
                .toList();


    }

    public String getCourseTitle(Long sessionId) {
        // Fetch the course title based on sessionId
        return sessionRepository.findById(sessionId)
                .map(session -> session.getCourseTitle())
                .orElseThrow(() -> new IllegalArgumentException("Invalid session ID: " + sessionId));
    }

    public List<AttendanceSessionResponse> getSessionsByLecturerId(Long lecturerId) {
        List<AttendanceSession> sessions = sessionRepository.findByLecturer_Id(lecturerId);
        return sessions.stream()
                .map(AttendanceSessionResponse::new) // Map each session to the response class
                .toList();
    }



    public List<AttendanceHistoryResponse> getAttendanceHistory(Long studentId) {
        List<Object[]> records = recordRepository.findAttendanceHistoryByStudentId(studentId);

        if (records.isEmpty()) {
            throw new IllegalArgumentException("No attendance records found for the given student ID.");
        }

        return records.stream()
                .map(record -> new AttendanceHistoryResponse(
                        (String) record[0],       // Course Title
                        (LocalDateTime) record[1] // Attendance Time
                ))
                .toList();
    }





}

