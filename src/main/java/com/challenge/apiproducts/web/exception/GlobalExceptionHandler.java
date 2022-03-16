package com.challenge.apiproducts.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody
    public ErrorResponse resourceNotFound(HttpServletRequest request, Exception e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ErrorResponse badRequest(HttpServletRequest request, Exception e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
    }
}
