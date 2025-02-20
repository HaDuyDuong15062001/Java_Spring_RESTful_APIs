package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.constant.JobHunterError;
import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOCreate;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOUpdate;
import com.duyduong.jobhunter.domain.dto.response.UserResDTOCreate;
import com.duyduong.jobhunter.domain.dto.response.UserResDTOUpdate;
import com.duyduong.jobhunter.service.UserService;
import com.duyduong.jobhunter.util.annotation.ApiMessage;
import com.duyduong.jobhunter.util.error.IdInvalidException;
import com.duyduong.jobhunter.util.error.JobHunterException;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiMessage("Create new user")
    @PostMapping
    public ResponseEntity<UserResDTOCreate> createNewUserPostMethod(@Valid @RequestBody UserReqDTOCreate userReqDTOCreate) throws IdInvalidException {
        UserResDTOCreate userResDTOCreate = this.userService.handleCreateUser(userReqDTOCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResDTOCreate);
    }

    @ApiMessage("Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = this.userService.handleFindUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @ApiMessage("Get all user")
    @GetMapping
    public ResponseEntity<ResultPaginationDTO> getAllUser(
            @Filter Specification<User> spec, Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.handleFindAllUser(spec, pageable));
    }

    @ApiMessage("Delete user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        this.userService.handleDeleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Id " + id + " is deleted");
    }

    @ApiMessage("Update user")
    @PutMapping
    public ResponseEntity<UserResDTOUpdate> updateUser(@Valid @RequestBody UserReqDTOUpdate userReqDTOUpdate) {

        UserResDTOUpdate userResDTOUpdate = this.userService.handleUpdateUser(userReqDTOUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(userResDTOUpdate);
    }
}
