package by.vlad.tasktracker.UserService.controller;

import by.vlad.tasktracker.UserService.dto.UserDto;
import by.vlad.tasktracker.UserService.dto.mapper.UserMapper;
import by.vlad.tasktracker.UserService.model.User;
import by.vlad.tasktracker.UserService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userMapper.convertToEntity(userDto));
        UserDto response = userMapper.convertToDto(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> response = userMapper.convertToListDto(users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDto response = userMapper.convertToDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        User updateUser = userService.updateUser(userMapper.convertToEntity(userDto), id);
        UserDto response = userMapper.convertToDto(updateUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
