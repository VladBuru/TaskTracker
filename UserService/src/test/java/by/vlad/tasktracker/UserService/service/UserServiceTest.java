package by.vlad.tasktracker.UserService.service;

import by.vlad.tasktracker.UserService.model.User;
import by.vlad.tasktracker.UserService.repository.UserRepository;
import by.vlad.tasktracker.UserService.util.exception.EmailAlreadyExistsException;
import by.vlad.tasktracker.UserService.util.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User(1L, "testUser", "testEmail@test.com");
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        verify(userRepository, times(1)).save(user);
        assertEquals(1L, createdUser.getId());
        assertEquals("testUser", createdUser.getName());
        assertEquals("testEmail@test.com", createdUser.getEmail());
    }

    @Test
    public void testCreateUserThrowsExceptionEmailAlreadyExistsException() {
        String email = "test@test.com";
        User user = new User(1L, "test", email);
        when(userRepository.existsByEmail(email)).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(user));

        verify(userRepository, never()).save(user);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1L, "user1", "user1@test.com");
        User user2 = new User(2L, "user2", "user2@test.com");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> returnedUsers = userService.getAllUsers();

        assertEquals(2, returnedUsers.size());
        assertEquals(user1, returnedUsers.get(0));
        assertEquals(user2, returnedUsers.get(1));
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L,"user", "user1@test.com");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testGetUserByIdThrowsExceptionUserNotFoundException() {
        Long notExistentId = 1L;
        when(userRepository.findById(notExistentId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }
}
