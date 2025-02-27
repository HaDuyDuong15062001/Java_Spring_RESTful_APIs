package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.constant.JobHunterError;

import com.duyduong.jobhunter.domain.user.User;
import com.duyduong.jobhunter.domain.dto.response.ResultPaginationDTO;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOCreate;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOUpdate;
import com.duyduong.jobhunter.domain.dto.response.UserResDTOCreate;
import com.duyduong.jobhunter.domain.dto.response.UserResDTOFindById;
import com.duyduong.jobhunter.domain.dto.response.UserResDTOUpdate;
import com.duyduong.jobhunter.repository.UserRepository;
import com.duyduong.jobhunter.util.error.IdInvalidException;
import com.duyduong.jobhunter.util.error.JobHunterException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private final ModelMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResDTOCreate handleCreateUser(UserReqDTOCreate userReqDTOCreate) throws IdInvalidException {

        boolean emailIsExist = this.userRepository.existsByEmail(userReqDTOCreate.getEmail());
        if (emailIsExist) {
            throw new JobHunterException(JobHunterError.EMAIL_EXISTED);
        }
        String hashPassword = this.passwordEncoder.encode(userReqDTOCreate.getPassword());
        userReqDTOCreate.setPassword(hashPassword);
        User user = this.mapper.map(userReqDTOCreate, User.class);
        this.userRepository.save(user);
        UserResDTOCreate userResDTOCreate = this.mapper.map(user, UserResDTOCreate.class);

        return userResDTOCreate;
    }

    public void handleDeleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.USER_ID_NOT_FOUND, List.of(id))
        );
        this.userRepository.deleteById(id);
    }

    public UserResDTOFindById handleFindUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.USER_ID_NOT_FOUND, List.of(id))
        );
        UserResDTOFindById userResDTOFindById = this.mapper.map(user, UserResDTOFindById.class);
        return userResDTOFindById;
    }

    public ResultPaginationDTO handleFindAllUser(Specification<User> specification, Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(specification, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        ResultPaginationDTO.MetaDTO meta = new ResultPaginationDTO.MetaDTO();

        meta.setPage(userPage.getNumber() + 1);
        meta.setPageSize(userPage.getSize());

        meta.setPages(userPage.getTotalPages());
        meta.setTotal(userPage.getTotalElements());

        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(userPage.getContent());
        return resultPaginationDTO;
    }

    public UserResDTOUpdate handleUpdateUser(UserReqDTOUpdate userReqDTOUpdate) {

        User user = this.userRepository.findById(userReqDTOUpdate.getId()).orElseThrow(
                () -> new JobHunterException(JobHunterError.USER_ID_NOT_FOUND, List.of(userReqDTOUpdate.getId()))
        );
        this.userMapper.map(userReqDTOUpdate, user);
        this.userRepository.save(user);
        UserResDTOUpdate UserResDTOUpdate  = this.mapper.map(user, UserResDTOUpdate.class);

        return UserResDTOUpdate;
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }

    public boolean isEmailExist(UserReqDTOCreate userReqDTOCreate) {
        return this.userRepository.existsByEmail(userReqDTOCreate.getEmail());
    }

    public boolean isIdExist(UserReqDTOUpdate userReqDTOUpdate) {
        return this.userRepository.existsById(userReqDTOUpdate.getId());
    }

    public void updateUserTolken(String token, String email) {
        User currentUser = this.handleGetUserByUsername(email);
        if (currentUser != null) {
            currentUser.setRefreshToken(token);
            this.userRepository.save(currentUser);
        }
    }

    public User getUserByRefreshTokenAndEmail(String token, String email) {
        return this.userRepository.findByRefreshTokenAndEmail(token, email);
    }
}
