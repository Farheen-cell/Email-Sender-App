package com.email.Email.sender.App.paylod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomeResponse {
    private String message;
    private HttpStatus httpStatus;
    private boolean success = false;
}
