package com.login.controller;

import com.login.service.LoginService;
import com.login.dto.RequesUsuarioDTO;
import com.login.dto.ResponseDTO;
import com.login.dto.VerifyTokenDTO;
import com.login.respuesta.Meta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200, "success");

    @Autowired
    LoginService loginService;

    @PostMapping("/signin")
    public ResponseDTO authenticateUser(@Valid @RequestBody RequesUsuarioDTO loginRequest) {

        return new ResponseDTO(loginService.validarUsuario(loginRequest), meta);
    }

    @PostMapping("/verify")
    public ResponseDTO authenticateUser(@Valid @RequestBody VerifyTokenDTO tokenDTO) {
        return new ResponseDTO(loginService.refrescarToken(tokenDTO.getToken()), meta);
    }
}
