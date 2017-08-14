package com.itolla.test.taskhelper.util;

public class JsonResponse {
    public String status;
    public String message;

    public JsonResponse(String status, String message){
        this.status = status;
        this.message = message;
    }
}
