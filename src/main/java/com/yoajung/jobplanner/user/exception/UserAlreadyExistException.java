package com.yoajung.jobplanner.user.exception;

import com.yoajung.jobplanner.common.exception.BaseException;
import com.yoajung.jobplanner.common.status.enums.FailureStatus;

public class UserAlreadyExistException extends BaseException {

    public UserAlreadyExistException(String message){
        super(FailureStatus.USER_ALREADY_EXISTED, message);
    }
}
