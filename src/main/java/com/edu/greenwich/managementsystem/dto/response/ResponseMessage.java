package com.edu.greenwich.managementsystem.dto.response;

public class ResponseMessage {
    private Object message;

    public ResponseMessage(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}