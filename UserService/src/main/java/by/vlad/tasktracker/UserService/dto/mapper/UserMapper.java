package by.vlad.tasktracker.UserService.dto.mapper;

import by.vlad.tasktracker.UserService.dto.UserDto;
import by.vlad.tasktracker.UserService.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
    }

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public List<UserDto> convertToListDto(List<User> users) {
        return users.stream().map(this::convertToDto).toList();
    }

}
