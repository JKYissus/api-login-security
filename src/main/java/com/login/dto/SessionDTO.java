package com.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionDTO {
    private String session;
    private Date  timeExpire;
    private UsuarioDTO user;
}
