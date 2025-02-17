package com.duyduong.jobhunter.service;

import com.duyduong.jobhunter.constant.JobHunterError;

import com.duyduong.jobhunter.domain.Company;
import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.domain.dto.MetaDTO;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.repository.UserRepository;
import com.duyduong.jobhunter.util.error.JobHunterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
