package by.vlad.tasktracker.UserService.event;

import lombok.Getter;

@Getter
public class UserDeletedEvent {
    private final Long userId;

    public UserDeletedEvent(Long userId) {
        this.userId = userId;
    }
}
