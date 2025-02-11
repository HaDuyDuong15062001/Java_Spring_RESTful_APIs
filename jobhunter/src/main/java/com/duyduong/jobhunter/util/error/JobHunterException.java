package com.duyduong.jobhunter.util.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class JobHunterException extends RuntimeException {

    private final transient Object errorCode;
    private final transient Object errorList;

    public JobHunterException(String message, Object errorCode, Object errorList) {
        super(message);
        this.errorCode = errorCode;
        this.errorList = errorList;
    }

    public JobHunterException(Object errorCode, Object errorList) {
        this.errorCode = errorCode;
        this.errorList = errorList;
    }

    public JobHunterException(Object errorCode) {
        this.errorCode = errorCode;
        this.errorList = null;
    }
}
