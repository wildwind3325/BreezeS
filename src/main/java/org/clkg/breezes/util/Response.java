package org.clkg.breezes.util;

public record Response(int code, String msg, Object data) {

    public static Response success() {
        return new Response(0, "", null);
    }

    public static Response success(Object data) {
        return new Response(0, "", data);
    }

    public static Response success(Object data, String msg) {
        return new Response(0, msg, data);
    }

    public static Response error(int code, String msg) {
        return new Response(code, msg, null);
    }

    public static Response error(int code, String msg, Object data) {
        return new Response(code, msg, data);
    }
}
