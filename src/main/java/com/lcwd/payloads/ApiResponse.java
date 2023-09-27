package com.lcwd.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private Boolean status;
    private HttpStatus httpStatus;

    public ApiResponse(String message, boolean b, HttpStatus httpStatus) {
    }
}
