package com.itolla.test.taskhelper.util;

public class JsonRequest {
    public int status = 404;
    public String message = "not found";

    public JsonRequest(int status, String message){
        this.status = status;
        this.message = message;
    }
}
