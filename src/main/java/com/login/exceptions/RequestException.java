package com.login.exceptions;

import com.login.respuesta.Meta;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestException extends RuntimeException{
    private HttpStatus status;
    private Meta meta;

    public RequestException(HttpStatus status, Meta meta) {
        this.status = status;
        this.meta = meta;
    }
}