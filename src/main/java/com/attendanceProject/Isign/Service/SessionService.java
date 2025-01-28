package com.attendanceProject.Isign.Service;

import com.attendanceProject.Isign.Payload.SessionResponse;
import com.attendanceProject.Isign.Repo.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<SessionResponse> getAllSessions() {
        List<Object[]> sessions = sessionRepository.findAllSessions();
        return sessions.stream()
                .map(session -> new SessionResponse(
                        (Long) session[0],           // sessionId
                        (String) session[1],        // courseTitle
                        (LocalDateTime) session[2]  // creationDate
                ))
                .toList();
    }
}

