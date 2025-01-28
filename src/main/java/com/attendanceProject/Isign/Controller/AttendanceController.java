package com.attendanceProject.Isign.Controller;
import com.attendanceProject.Isign.Model.AttendanceSession;
import com.attendanceProject.Isign.Payload.*;
import com.attendanceProject.Isign.Service.AttendanceService;
import com.attendanceProject.Isign.Utilities.PDFUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
//@RequestMapping("/api/attendance")
//public class AttendanceController {
//
//    @Autowired
//    private AttendanceService attendanceService;
//
//    @PostMapping("/initiate")
//    public ResponseEntity<?> initiateAttendance(@RequestBody Map<String, Object> request) {
//        // Extract other parameters
//        String courseTitle = (String) request.get("courseTitle");
//        Long locationId = ((Number) request.get("locationId")).longValue();
//        Integer duration = (Integer) request.get("duration");
//        String failsafeCode = (String) request.get("failsafeCode");
//
//        // Call the service method to initiate the attendance
//        Attendance attendance = attendanceService.initiateAttendance(
//                courseTitle, locationId, duration, failsafeCode);
//
//        return ResponseEntity.ok("attendance successfully initiated");
//    }
//}

@RestController
@CrossOrigin
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/create")
    public ResponseEntity<?> createAttendanceSession(@RequestBody AttendanceRequest request) {
        try {
            AttendanceSession session = attendanceService.createSession(request);
            return ResponseEntity.ok(new ApiResponse(true, "Session created successfully!", session));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error creating session: " + e.getMessage()));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyAttendance(@RequestBody AttendanceVerificationRequest request) {
        try {
            AttendanceVerificationResponse response = attendanceService.verifyAttendanceCode(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAttendance(@RequestBody AttendanceSubmissionRequest request) {
        ApiResponse response = attendanceService.submitAttendance(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<?> viewAttendanceRecords(@PathVariable Long sessionId) {
        List<AttendanceRecordResponse> records = attendanceService.getAttendanceRecords(sessionId);
        return ResponseEntity.ok(records);
    }


    @GetMapping("/session/{sessionId}/download")
    public ResponseEntity<?> downloadAttendanceRecords(@PathVariable Long sessionId) {
        try {
            List<AttendanceRecordResponse> records = attendanceService.getAttendanceRecords(sessionId);

            // Generate the PDF
            String courseTitle = attendanceService.getCourseTitle(sessionId);
            byte[] pdfContent = PDFUtil.generateAttendancePDF(records, courseTitle);

            // Return the PDF as a downloadable response
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=attendance_report.pdf")
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdfContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error generating PDF: " + e.getMessage()));
        }
    }

    @GetMapping("/sessions")
    public ResponseEntity<?> getSessionsByLecturerId(@RequestParam Long lecturerId) {
        List<AttendanceSessionResponse> sessions = attendanceService.getSessionsByLecturerId(lecturerId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/history/{studentId}")
    public ResponseEntity<?> getAttendanceHistory(@PathVariable Long studentId) {
        List<AttendanceHistoryResponse> history = attendanceService.getAttendanceHistory(studentId);
        return ResponseEntity.ok(history);
    }

}

