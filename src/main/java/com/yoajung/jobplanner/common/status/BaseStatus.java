package com.yoajung.jobplanner.common.status;

import com.yoajung.jobplanner.common.response.BaseResponseBody;

public interface BaseStatus {
    <T> BaseResponseBody<T> getBaseResponseBody();

    <T> BaseResponseBody<T> getBaseResponseBody(T result);
}
