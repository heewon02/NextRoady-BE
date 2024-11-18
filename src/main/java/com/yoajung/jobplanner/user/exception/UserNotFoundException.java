package com.yoajung.jobplanner.user.exception;

import com.yoajung.jobplanner.common.exception.BaseException;
import com.yoajung.jobplanner.common.status.enums.FailureStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message) {
        super(FailureStatus.USER_NOT_FOUND, message);
    }
}
