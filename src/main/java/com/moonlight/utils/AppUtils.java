package com.moonlight.utils;

import com.moonlight.constants.AppConstants;
import com.moonlight.dto.PersonResponse;

public class AppUtils {

    public static void getPersonSuccessResult(PersonResponse personResponse, String message) {
        personResponse.setStatus(AppConstants.SUCCESS);
        personResponse.setStatusCode(AppConstants.SUCCESS_CODE);
        personResponse.setMessage(message);
    }

    public static void getPersonFailResult(PersonResponse personResponse, String message) {
        personResponse.setStatusCode(AppConstants.FAILURE_CODE);
        personResponse.setStatus(AppConstants.FAIL);
        personResponse.setMessage(message);
    }
}
