package com.login.dto;

import com.login.respuesta.Meta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private Meta meta;
}
