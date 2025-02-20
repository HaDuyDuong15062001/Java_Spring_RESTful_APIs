package com.duyduong.jobhunter.util.error;

import com.duyduong.jobhunter.constant.JobHunterError;

public class IdInvalidException extends Exception {

    JobHunterError jobHunterError;
    public IdInvalidException(String message) {
      super(message);
    }
    public IdInvalidException(JobHunterError jobHunterError) {
        this.jobHunterError = jobHunterError;
    }
}
