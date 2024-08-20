package by.vlad.tasktracker.UserService.controller;

import by.vlad.tasktracker.UserService.dto.UserDto;
import by.vlad.tasktracker.UserService.dto.mapper.UserMapper;
import by.vlad.tasktracker.UserService.model.User;
import by.vlad.tasktracker.UserService.service.UserService;
import org.apache.el.stream.Stream;
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

    /**
     * TODO:
     * + GET /users - получить список всех пользователей
     * + GET /users/{id} - получить информацию о пользователе
     * PUT /users/{id} - обновить информацию о пользователе
     * DELETE /users/{id} - удалить пользователя
     */

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
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

}
