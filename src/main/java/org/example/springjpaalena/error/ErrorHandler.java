package org.example.springjpaalena.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(InvalidPageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidPageHandle(final InvalidPageException e){
        return new ErrorResponse("Ошибка!Страница не должна быть меньше нуля" ,e.getMessage());
    }

    @ExceptionHandler(InvalidSizeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse inavalidSizeHandle(final InvalidSizeException e){
        return new ErrorResponse("Ошибка!Размер должен быть положительным" ,e.getMessage());
    }
}
