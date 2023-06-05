package com.nstop.common.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Data
@ToString
public class Result<T> implements Serializable {

    private static final int SUCCESS_CODE = 1;

    private static final int DEFAULT_ERROR_CODE = -1;

    private int code;

    private String message;

    private T data;

    public String requestId;

    public boolean checkSuccess() {
        return code == 1;
    }

    public boolean checkDataSuccess() {
        if (code != 1 || Objects.isNull(data)) {
            return false;
        }
        boolean success = true;
        if (data instanceof Collection) {
            success = !((Collection) data).isEmpty();
        }
        return success;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(null);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static Result failure(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
