package com.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.components.JWT.JwtUtil;
import com.login.dto.RequesUsuarioDTO;
import com.login.dto.SessionDTO;
import com.login.dto.UsuarioDTO;
import com.login.dto.VerifyTokenDTO;
import com.login.entitie.UserSession;
import com.login.exceptions.RequestException;
import com.login.repository.UserRepository;
import com.login.respuesta.Error;
import com.login.respuesta.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService{

    @Value("${jwt.time.expiration}")
    Long expiration;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public SessionDTO validarUsuario(RequesUsuarioDTO validarUser) {

        UserSession user = userRepository.findByUsername(validarUser.getUsuario());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if ( user == null ){

            throw new RequestException(HttpStatus.UNAUTHORIZED,
                    new Meta(UUID.randomUUID().toString(),HttpStatus.UNAUTHORIZED.toString(),
                            423, Error.USUARIO_INCORRECTO));

        }

        if ( !passwordEncoder.matches(validarUser.getPwd(), user.getPassword()) ) {

            throw new RequestException(HttpStatus.UNAUTHORIZED,
                    new Meta(UUID.randomUUID().toString(),HttpStatus.UNAUTHORIZED.toString(),
                            423, Error.CONTRA_INCORRECTA));
        }

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setCorreo(user.getCorreo());
        usuario.setUsername(user.getUsername());
        usuario.setRol(user.getRol());
        usuario.setId(user.get_id());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> usuarioMap = objectMapper.convertValue(usuario, Map.class);

        String token = jwtUtil.generateToken(usuarioMap);


        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return new SessionDTO(token, expiryDate, usuario);
    }

    @Override
    public VerifyTokenDTO refrescarToken(String token) {
        VerifyTokenDTO dataToken = new VerifyTokenDTO();
        String validatoken = jwtUtil.validateToken(token);

        if (validatoken == null) {
            throw new RequestException(HttpStatus.UNAUTHORIZED,
                    new Meta(UUID.randomUUID().toString(),HttpStatus.UNAUTHORIZED.toString(),
                            423, Error.TOKEN_EXPIRED));
        }

        dataToken.setToken(validatoken);

        return dataToken;
    }
}
