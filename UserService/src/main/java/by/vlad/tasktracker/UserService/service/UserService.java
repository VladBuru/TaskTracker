package by.vlad.tasktracker.UserService.service;

import by.vlad.tasktracker.UserService.event.UserDeletedEvent;
import by.vlad.tasktracker.UserService.model.User;
import by.vlad.tasktracker.UserService.repository.UserRepository;
import by.vlad.tasktracker.UserService.util.exception.EmailAlreadyExistsException;
import by.vlad.tasktracker.UserService.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<Long, UserDeletedEvent> kafkaTemplate;

    @Autowired
    public UserService(UserRepository userRepository, KafkaTemplate<Long, UserDeletedEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User updateUser(User user, Long id) {
        User updateUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (userRepository.existsByEmail(user.getEmail()) && !updateUser.getEmail().equals(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        userRepository.save(updateUser);
        return updateUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        User deleteUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(deleteUser);

        UserDeletedEvent event = new UserDeletedEvent(id); // генерируем событие
        kafkaTemplate.send("user-deleted-topic", event); // отправка события в топик
    }
}
