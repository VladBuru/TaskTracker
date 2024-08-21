package by.vlad.tasktracker.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Name must not be empty")
    private String name;

    @Email
    private String email;
}
