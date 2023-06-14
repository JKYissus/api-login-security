package com.login.service;

import com.login.dto.RequesUsuarioDTO;
import com.login.dto.SessionDTO;
import com.login.dto.VerifyTokenDTO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    SessionDTO validarUsuario(RequesUsuarioDTO validarUser);

    VerifyTokenDTO refrescarToken(String token);

}
