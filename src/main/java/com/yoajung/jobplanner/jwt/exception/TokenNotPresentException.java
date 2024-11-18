package com.yoajung.jobplanner.jwt.exception;

import com.yoajung.jobplanner.common.exception.BaseException;
import com.yoajung.jobplanner.common.status.enums.FailureStatus;

public class TokenNotPresentException extends BaseException {
    public TokenNotPresentException(){
        super(FailureStatus.TOKEN_NOT_PRESENT);
    }
}
