package by.vlad.tasktracker.UserService.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {

    //private Long userId;

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
        //this.userId = id;
    }

}
