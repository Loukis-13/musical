package br.com.gft.musical.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
//@Setter
public class Error {
    private String message;
    private String errors;
    private HttpStatus status;

    public Error(String message, String errors, HttpStatus status) {
        this.message = message;
        this.errors = errors;
        this.status = status;
    }
}
