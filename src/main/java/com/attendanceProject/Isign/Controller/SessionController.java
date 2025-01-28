package com.attendanceProject.Isign.Controller;

import com.attendanceProject.Isign.Payload.ApiResponse;
import com.attendanceProject.Isign.Payload.AttendanceRecordResponse;
import com.attendanceProject.Isign.Payload.SessionResponse;
import com.attendanceProject.Isign.Service.AttendanceService;
import com.attendanceProject.Isign.Service.SessionService;
import com.attendanceProject.Isign.Utilities.PDFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AttendanceService attendanceService;

    // Endpoint to fetch all sessions
    @GetMapping
    public ResponseEntity<?> getAllSessions() {
        List<SessionResponse> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    // Endpoint to view attendance for a session
    @GetMapping("/{sessionId}/attendance")
    public ResponseEntity<?> viewAttendanceRecords(@PathVariable Long sessionId) {
        List<AttendanceRecordResponse> records = attendanceService.getAttendanceRecords(sessionId);
        return ResponseEntity.ok(records);
    }

    // Endpoint to download attendance for a session
    @GetMapping("/{sessionId}/attendance/download")
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
}

