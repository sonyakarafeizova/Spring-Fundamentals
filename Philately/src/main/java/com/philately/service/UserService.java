package com.philately.service;

import com.google.gson.Gson;
import com.philately.controller.UserSession;
import com.philately.model.dto.UserRegisterDTO;
import com.philately.model.dto.UserDTO;
import com.philately.model.entity.User;
import com.philately.repository.UserRepository;
import com.philately.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final Gson gson;
    private final HttpSession session;
    private static final String USERS_FILE_PATH = "src/main/resources/static/files/users.json";


    public UserService(UserRepository userRepository, UserSession userSession, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser, Gson gson, HttpSession session) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.gson = gson;
        this.session = session;
    }
    public String readUsersFileContent() throws IOException {
        return Files.readString(Path.of(USERS_FILE_PATH));
    }

    public UserDTO findUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        return this.mapUserDTO(user);
    }
    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
    }

    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    public void register(UserRegisterDTO registerDTO) {
        this.userRepository.save(this.mapUser(registerDTO));
        this.login(registerDTO.getUsername());
    }

    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    private User getUSerById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow();
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    private User mapUser(UserRegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return user;
    }

    private UserDTO mapUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername());
    }

    public void initUsers() throws IOException {
        Arrays.stream(gson.fromJson(readUsersFileContent(), UserDTO[].class))
                .map(userDTO -> {
                    User user = modelMapper.map(userDTO, User.class);
                    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    return user;
                }).forEach(userRepository::save);
    }


    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);

    }
}
