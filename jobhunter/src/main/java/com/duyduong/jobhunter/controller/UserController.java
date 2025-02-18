package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTO;
import com.duyduong.jobhunter.domain.dto.response.UserResDTO;
import com.duyduong.jobhunter.service.UserService;
import com.duyduong.jobhunter.util.annotation.ApiMessage;
import com.duyduong.jobhunter.util.error.IdInvalidException;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiMessage("Create new user")
    @PostMapping
    public ResponseEntity<UserResDTO> createNewUserPostMethod(@Valid @RequestBody UserReqDTO userReqDTO)
            throws IdInvalidException {
        boolean isEmailExist = this.userService.isEmailExist(userReqDTO);
        if (isEmailExist) {
            throw new IdInvalidException(
                    "Email " + userReqDTO.getEmail() + " đã tồn tại. Vui lòng sử dụng email khác!");
        }
        String hashPassword = this.passwordEncoder.encode(userReqDTO.getPassword());
        userReqDTO.setPassword(hashPassword);
        UserResDTO userResDTO = this.userService.handleCreateUser(userReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResDTO);
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
    public ResponseEntity<User> updateUser(@RequestBody User userPut) {
        User user = this.userService.handleUpdateUser(userPut);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
