package by.vlad.tasktracker.UserService.controller;

import by.vlad.tasktracker.UserService.dto.UserDto;
import by.vlad.tasktracker.UserService.dto.mapper.UserMapper;
import by.vlad.tasktracker.UserService.model.User;
import by.vlad.tasktracker.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userMapper.convertToEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.convertToDto(createdUser));
    }

}
