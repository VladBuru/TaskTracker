package by.vlad.tasktracker.UserService.event;

import lombok.Getter;

@Getter
public class UserDeletionFailedEvent {

    private final Long userId;

    public UserDeletionFailedEvent(Long userId) {
        this.userId = userId;
    }
}
