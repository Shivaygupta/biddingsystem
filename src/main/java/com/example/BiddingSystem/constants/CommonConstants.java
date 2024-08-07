package com.example.BiddingSystem.constants;


import com.example.BiddingSystem.exception.ErrorCodes;

public class CommonConstants {

    public static final String ERROR_CODE_DELIMITER = "-";
    public static final String SERVICE_CODE = "bidding-service";
    public static final String SERVICE_TOP_CONTEXT = "BIDDING";
    public static final String FIELD_ERROR_CODE = String.join(CommonConstants.ERROR_CODE_DELIMITER, SERVICE_CODE,
            String.valueOf(ErrorCodes.BUSINESS_VALIDATION_ERROR.getCode()));
    public static final String FIELD_ERROR_MESSAGE = "Invalid field values, Please update and try again";

    private CommonConstants() {
    }
}
