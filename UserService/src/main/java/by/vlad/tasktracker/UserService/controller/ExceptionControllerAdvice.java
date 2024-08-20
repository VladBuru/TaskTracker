package by.vlad.tasktracker.UserService.controller;

import by.vlad.tasktracker.UserService.util.ErrorResponse;
import by.vlad.tasktracker.UserService.util.ErrorType;
import by.vlad.tasktracker.UserService.util.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ErrorType.RESOURCE_NOT_FOUND.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
