package com.example.ctftreasuremapservice.model.dto;


import com.example.ctftreasuremapservice.model.pojo.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;

    public User fromDto(UserDto userDto) {
        return User.builder()
                .username(userDto.username)
                .password(userDto.password)
                .build();
    }
}
