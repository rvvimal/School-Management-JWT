package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SignInRequestDTO;
import com.school_management.dto.SignUpRequestDTO;
import com.school_management.entity.User;
import com.school_management.service.UserService;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO adminSignUp(@RequestBody final SignUpRequestDTO signUpRequestDTO) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.userService.adminCreate(signUpRequestDTO));
    }

    @PostMapping("/auth/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO teacherSignUp(@RequestBody final SignUpRequestDTO signUpRequestDTO) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.userService.teacherCreate(signUpRequestDTO));
    }

    @PostMapping("/auth/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO studentSignUp(@RequestBody final SignUpRequestDTO signUpRequestDTO) {

        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.userService.studentCreate(signUpRequestDTO));
    }

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO login(@RequestBody final SignInRequestDTO signInRequestDTO) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.TOKEN, this.userService.signIn(signInRequestDTO));
    }

    @PostMapping("/auth/refresh")
    public ResponseDTO token(@RequestParam final String refreshToken) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.TOKEN, this.userService.refreshToken(refreshToken));
    }

    @GetMapping("/admin/{id}")
    public ResponseDTO getById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.getById(id));
    }

    @GetMapping("/admin")
    public ResponseDTO findByAll() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.findByAllAndId());
    }

    @DeleteMapping("/admin/{id}")
    public ResponseDTO deleteByAdminId(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.userService.deleteById(id));
    }

}