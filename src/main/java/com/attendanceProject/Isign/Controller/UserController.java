package com.attendanceProject.Isign.Controller;

import com.attendanceProject.Isign.Model.Users;
import com.attendanceProject.Isign.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserController {

     @Autowired
     private UserService userService;

     @PostMapping("/login")
     public ResponseEntity<?> loginUser(@RequestBody Map<String, String> requestBody) {
         String regNumber = requestBody.get("regnumber");
         if (regNumber == null || regNumber.isEmpty()) {
             return ResponseEntity.badRequest().body("Registration number is required.");
         }

         try {
             Users users = userService.login(regNumber);
             // Controller builds the response
             Map<String, Object> response = new HashMap<>();
             response.put("id", users.getId());
             response.put("name", users.getName());
             response.put("role", users.getRole()); // Role: "Student" or "Lecturer"
             response.put("regnumber", users.getRegnumber());
             return ResponseEntity.ok(users);
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
         }
     }

        @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
         Users createdUsers = userService.createUser(users.getName(), users.getRegnumber(), String.valueOf(users.getRole()));
         return ResponseEntity.ok(createdUsers);
        }


}
