package com.attendanceProject.Isign.Service;

import com.attendanceProject.Isign.Model.Users;
import com.attendanceProject.Isign.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Users login(String regnumber){
        Users users = userRepo.findByRegnumber(regnumber);
        if (users == null) throw new IllegalArgumentException("Cant find this in the database");
        return users;
    }

    public Users createUser(String name, String regnumber, String role) {
        Users users = new Users();
        users.setName(name);
        users.setRegnumber(regnumber);
        users.setRole(Users.Role.valueOf(role));

        return userRepo.save(users);
    }
}


