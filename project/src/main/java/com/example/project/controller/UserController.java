package com.example.project.controller;



import com.example.project.dto.BillDTO;
import com.example.project.dto.UserDTO;
import com.example.project.entity.User;
import com.example.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMe() {
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/me/{name}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String name) {
        UserDTO updatedUser = userService.updateUser(name);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser() {
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}