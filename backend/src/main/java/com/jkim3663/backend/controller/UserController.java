package com.jkim3663.backend.controller;

import com.jkim3663.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllUserNames() {
        return new ResponseEntity<>(userService.getAllUserNames(), HttpStatus.OK);
    }
}
