package com.danske.taxmanager.response;

import lombok.Getter;

@Getter
public class SuccessResponse {

    private int statusCode;

    String responseMessage;

    Object data;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    private int numberOfRecords;

    public SuccessResponse(int statusCode, String responseMessage, Object data, int numberOfRecords) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = data;
        this.numberOfRecords = numberOfRecords;
    }

}
