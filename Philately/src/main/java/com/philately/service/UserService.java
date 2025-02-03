package com.philately.service;

import com.philately.controller.UserSession;
import com.philately.model.dto.UserLoginDTO;
import com.philately.model.dto.UserRegisterDTO;
import com.philately.model.dto.UserResponseDTO;
import com.philately.model.entity.User;
import com.philately.repository.UserRepository;
import com.philately.util.LoggedUser;
import com.philately.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, UserSession userSession, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public UserResponseDTO registerUser(UserRegisterDTO userDTO) {
        if (!ValidationUtil.isValidUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Invalid username. Must be between 3 and 20 characters.");
        }
        if (!ValidationUtil.isValidPassword(userDTO.getPassword())) {
            throw new IllegalArgumentException("Invalid password. Must be between 3 and 20 characters.");
        }
        if (!ValidationUtil.isValidEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);

        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), new ArrayList<>());
    }

//    public UserResponseDTO loginUser(UserLoginDTO loginDTO) {
//        User user = userRepository.findByUsername(loginDTO.getUsername())
//                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
//
//        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("Invalid password!");
//        }
//
//        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), new ArrayList<>());
//    }

    public void logout() {
        userSession.logout();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);

    }

    public boolean register(UserRegisterDTO data) {
        this.userRepository.save(this.modelMapper.map(data, User.class));
        this.login(data.getUsername());
        return false;
    }

    private void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }


}


