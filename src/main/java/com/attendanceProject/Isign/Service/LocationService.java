package com.attendanceProject.Isign.Service;

import com.attendanceProject.Isign.Model.Location;
import com.attendanceProject.Isign.Repo.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public List<Location> getAllLocations() {
        return locationRepo.findAll();
    }
}
