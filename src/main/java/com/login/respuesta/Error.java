package com.login.respuesta;
import lombok.Data;

//Mensajes de error
@Data
public class Error {
    public  final static String	CONTRA_INCORRECTA = "Contraseña incorrecta!";
    public  final static String	USUARIO_INCORRECTO = "Usuario incorrecto!";
    public  final static String	TOKEN_EXPIRED = "Token expirado!";

}
