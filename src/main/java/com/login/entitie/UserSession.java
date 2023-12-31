package com.login.entitie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "usuario")
public class UserSession {
    @Id
    private String _id;
    private String username;
    private String password;
    private String correo;
    private String nombres;
    private String apellidos;
    private String rol;
}
