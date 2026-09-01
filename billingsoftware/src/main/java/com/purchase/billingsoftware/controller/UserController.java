package com.purchase.billingsoftware.controller;

import com.purchase.billingsoftware.dto.UserRequest;
import com.purchase.billingsoftware.dto.UserResponse;
import com.purchase.billingsoftware.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to create User "+e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> readUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.readUsers());
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not Found");
        }
    }
}
