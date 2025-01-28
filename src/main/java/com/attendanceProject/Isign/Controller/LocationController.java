package com.attendanceProject.Isign.Controller;

import com.attendanceProject.Isign.Model.Location;
import com.attendanceProject.Isign.Payload.ApiResponse;
import com.attendanceProject.Isign.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("api/locations")
//public class LocationController {
//
//    @Autowired
//    private LocationService locationService;
//
//    @GetMapping ("/list")
//    public ResponseEntity<List<Location>> getLocations() {
//        List<Location> locations = locationService.getAllLocations();
//        System.out.println("Locations fetched: " + locations); // Log locations
//        return ResponseEntity.ok(locations);
//    }
//}

@RestController
@CrossOrigin
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<?> getAllLocations() {
        try {
            List<Location> locations = locationService.getAllLocations();
            return ResponseEntity.ok(new ApiResponse(true, "Locations fetched successfully", locations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error fetching locations: " + e.getMessage()));
        }
    }
}
