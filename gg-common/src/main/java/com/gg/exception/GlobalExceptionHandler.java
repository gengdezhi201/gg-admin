package com.gg.exception;

import com.gg.domain.ResultEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResultEntity validatedBindException(BindException e)
    {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResultEntity.fail(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity validatedBindException(MethodArgumentNotValidException e)
    {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResultEntity.fail(message);
    }
}
