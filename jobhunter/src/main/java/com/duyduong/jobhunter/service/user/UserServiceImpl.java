package com.duyduong.jobhunter.service.user;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.dto.user.UserCreateDTO;
import com.duyduong.jobhunter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(UserCreateDTO dto) {
        User data = new User();
        data.setFullName(dto.getFullName());
        return repository.save(data);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User deleteById(Long id) {
        Optional<User> userOpt = repository.findById(id);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with id :" + id);
        }
        repository.deleteById(id);
        return userOpt.get();
    }
}
