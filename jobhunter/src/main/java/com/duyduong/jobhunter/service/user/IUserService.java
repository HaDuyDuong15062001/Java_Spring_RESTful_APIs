package com.duyduong.jobhunter.service.user;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.dto.user.UserCreateDTO;

import java.util.List;

public interface IUserService {
    User create(UserCreateDTO dto);
    List<User> getAllUsers();
    User deleteById(Long id);
}
