package org.clkg.breezes.web;

public class Response {

    boolean success;
    String code, message;
    Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    private Response(boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response success() {
        return new Response(true, "", "", null);
    }

    public static Response success(Object data) {
        return new Response(true, "", "", data);
    }

    public static Response success(Object data, String message) {
        return new Response(true, "", message, data);
    }

    public static Response error(String code, String message) {
        return new Response(false, code, message, null);
    }
}
