package com.example.softxpert_cars.data.models.error;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private Error error;

    public Error getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }


    public static class Error {
        @SerializedName("code")
        private String code;
        @SerializedName("message")
        private String message;

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
