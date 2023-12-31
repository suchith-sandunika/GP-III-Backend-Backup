package com.Cranco.Cranco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseDTO {
    private String code;
    private String message;
    private Object content;
    private HttpStatus httpStatus;
}
