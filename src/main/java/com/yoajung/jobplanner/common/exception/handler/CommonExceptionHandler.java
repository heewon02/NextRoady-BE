package com.yoajung.jobplanner.common.exception.handler;

import com.yoajung.jobplanner.common.response.BaseResponseBody;
import com.yoajung.jobplanner.common.status.enums.FailureStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseBody<?>> userExHandler(Exception exception){
        log.info(exception.getMessage());
        return ResponseEntity.status(FailureStatus.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(FailureStatus.INTERNAL_SERVER_ERROR.getBaseResponseBody());
    }
}
