package com.gosaint.framework.exception;

import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: gosaint
 * @Description: 全局异常处理
 * @Date Created in 22:13 2019/12/17
 * @Modified By:
 */
@ControllerAdvice
public class BaseExceptionHandler {

    /***
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
