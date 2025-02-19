package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.constant.JobHunterError;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.domain.dto.MetaDTO;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOCreate;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOUpdate;
import com.duyduong.jobhunter.domain.dto.response.UserResDTOCreate;
import com.duyduong.jobhunter.domain.dto.response.UserResDTOUpdate;
import com.duyduong.jobhunter.repository.UserRepository;
import com.duyduong.jobhunter.util.error.JobHunterException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final ModelMapper userMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, ModelMapper userModelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userMapper = userModelMapper;
    }

    public UserResDTOCreate handleCreateUser(UserReqDTOCreate userReqDTOCreate) {

        User user = this.userMapper.map(userReqDTOCreate, User.class);
        this.userRepository.save(user);
        UserResDTOCreate userResDTOCreate = this.modelMapper.map(user, UserResDTOCreate.class);

        return userResDTOCreate;
    }

    public void handleDeleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.USER_ID_NOT_FOUND, List.of(id))
        );
        this.userRepository.deleteById(id);
    }

    public User handleFindUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.USER_ID_NOT_FOUND, List.of(id))
        );
        return user;
    }

    public ResultPaginationDTO handleFindAllUser(Specification<User> specification, Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(specification, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        MetaDTO meta = new MetaDTO();

        meta.setPage(userPage.getNumber() + 1);
        meta.setPageSize(userPage.getSize());

        meta.setPages(userPage.getTotalPages());
        meta.setTotal(userPage.getTotalElements());

        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(userPage.getContent());
        return resultPaginationDTO;
    }

    public UserResDTOUpdate handleUpdateUser(UserReqDTOUpdate userReqDTOUpdate) {

        User user = this.userMapper.map(userReqDTOUpdate, User.class);
        this.userRepository.save(user);
        UserResDTOUpdate UserResDTOUpdate  = this.modelMapper.map(user, UserResDTOUpdate.class);

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

}
