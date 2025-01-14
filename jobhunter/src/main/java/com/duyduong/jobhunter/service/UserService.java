package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void handleDeleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public User handleFindUserById(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent())
            return userOptional.get();
        return null;
    }

    public List<User> handleFindAllUser() {
        return this.userRepository.findAll();
    }

    public User handleUpdateUser(User user) {
        User curUser = this.handleFindUserById(user.getId());
        if (curUser != null) {
            curUser.setEmail(user.getEmail());
            curUser.setFullName(user.getFullName());
            curUser.setPassword(user.getPassword());
            this.userRepository.save(curUser);
        }
        return curUser;
    }
}
