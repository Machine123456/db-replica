package com.cap.bna.dbreplica.controller;

import java.util.List;
import java.util.UUID;

import com.cap.bna.dbreplica.dto.UtilizadorRequest;
import com.cap.bna.dbreplica.dto.UtilizadorResponse;
import com.cap.bna.dbreplica.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<String> createUser(@RequestBody UtilizadorRequest userRequest) {

        try {
            userService.createUser(userRequest);
            return ResponseEntity.ok("User Created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Failed to create role \"" + userRequest.getNome() + "\" : " + e.getMessage());
        }
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<UtilizadorResponse>> getUsers() {

        try {
            var users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @PutMapping("/users/updateById/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") UUID id, @RequestBody UtilizadorRequest userRequest) {

        try {
            userService.updateUser(id, userRequest);
            return ResponseEntity.ok("User Updated");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user " + id + " : " + e.getMessage());
        }

    }

    @PutMapping("/users/updateByName/{name}")
    public ResponseEntity<String> updateUser(@PathVariable("name") String name, @RequestBody UtilizadorRequest userRequest) {

        try {
            userService.updateUser(name, userRequest);
            return ResponseEntity.ok("User Updated");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user " + name + " : " + e.getMessage());
        }

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete user " + id + " : " + e.getMessage());
        }
    }

}