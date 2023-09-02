package com.example.ctftreasuremapservice.manager;

import com.example.ctftreasuremapservice.Exception.UserAlreadyExistException;
import com.example.ctftreasuremapservice.model.dto.UserDto;
import com.example.ctftreasuremapservice.model.entity.UserEntity;
import com.example.ctftreasuremapservice.model.pojo.User;
import com.example.ctftreasuremapservice.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserManager {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        if (userRepository.getUserByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        } else {
            userRepository.save(
                    new UserEntity(user.getId(),
                    user.getUsername(),
                    user.getPassword()));
        }
    }

    public User fromDto(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .id(UUID.randomUUID())
                .build();
    }

    public boolean isAdmin() {
        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if (authority.getAuthority().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
