package com.login.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Data
public class UsuarioDTO {

    private String id;
    private String username;
    private String correo;
    private String rol;
}
