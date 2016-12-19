package com.kaishengit.dto;

public class JsonResult {

    private String state;
    private String message;
    private Object data;

    public JsonResult(String state, String message, Object data) {
            this.state = state;
            this.message = message;
            this.data = data;
    }
    public JsonResult() {
    }

    public JsonResult(Object data) {
        this.data="success";
        this.data = data;
    }

    public JsonResult(String message) {
        this.state = "error";
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
