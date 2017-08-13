package com.itolla.test.taskhelper.util;

public class JsonRequest {
    public String status = "unknown";
    public String message = "default";

    public JsonRequest(String status, String message){
        this.status = status;
        this.message = message;
    }
}
