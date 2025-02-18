package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.constant.JobHunterError;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.domain.dto.MetaDTO;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTO;
import com.duyduong.jobhunter.domain.dto.response.UserResDTO;
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

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResDTO handleCreateUser(UserReqDTO userReqDTO) {

        //User user = convertUserReqDTO2User(userReqDTO);
        User user = modelMapper(userReqDTO, User.class);
        this.userRepository.save(user);
        UserResDTO userResDTO = convertUser2UserResDTO(user);

        return userResDTO;
    }

    public void handleDeleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.COMPANY_ID_NOT_FOUND, List.of(id))
        );
        this.userRepository.deleteById(id);
    }

    public User handleFindUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new JobHunterException(JobHunterError.COMPANY_ID_NOT_FOUND, List.of(id))
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

    public User handleUpdateUser(User user) {

        User curUser = this.handleFindUserById(user.getId());

        curUser.setEmail(user.getEmail());
        curUser.setFullName(user.getFullName());
        curUser.setPassword(user.getPassword());
        this.userRepository.save(curUser);
        return curUser;
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }

    public boolean isEmailExist(@Valid UserReqDTO userReqDTO) {
        return this.userRepository.existsByEmail(userReqDTO.getEmail());
    }

    public UserResDTO convertUser2UserResDTO (User user) {
        UserResDTO userResDTO = new UserResDTO();
        userResDTO.setId(user.getId());
        userResDTO.setFullName(user.getFullName());
        userResDTO.setEmail(user.getEmail());
        userResDTO.setAge(user.getAge());
        userResDTO.setGender(user.getGender());
        userResDTO.setAddress(user.getAddress());
        userResDTO.setCreatedAt(user.getCreatedAt());
        return userResDTO;
    }

    public User convertUserReqDTO2User (UserReqDTO userReqDTO) {
        User user = new User();
        user.setFullName(userReqDTO.getFullName());
        user.setEmail(userReqDTO.getEmail());
        user.setPassword(userReqDTO.getPassword());
        user.setAge(userReqDTO.getAge());
        user.setGender(userReqDTO.getGender());
        user.setAddress(userReqDTO.getAddress());
        return user;
    }
}
