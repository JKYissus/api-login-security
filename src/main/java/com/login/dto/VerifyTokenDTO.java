package com.login.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class VerifyTokenDTO {
    private String token;
}
