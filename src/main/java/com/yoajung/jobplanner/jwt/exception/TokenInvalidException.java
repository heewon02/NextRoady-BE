package com.yoajung.jobplanner.jwt.exception;

import com.yoajung.jobplanner.common.exception.BaseException;
import com.yoajung.jobplanner.common.status.enums.FailureStatus;

public class TokenInvalidException extends BaseException {

    public TokenInvalidException(){
        super(FailureStatus.TOKEN_INVALID);
    }
}
