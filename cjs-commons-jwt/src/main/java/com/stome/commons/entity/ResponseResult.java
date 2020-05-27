package com.stome.commons.entity;

import com.stome.commons.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.stome.commons.enums.ResponseCodeEnum.FAIL;
import static com.stome.commons.enums.ResponseCodeEnum.SUCCESS;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc xxxx
 * @date 2020/5/26 22:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult success() {
        return new ResponseResult(SUCCESS.getResCode(), SUCCESS.getResMsg());
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(SUCCESS.getResCode(), SUCCESS.getResMsg(), data);
    }

    public static ResponseResult error() {
        return new ResponseResult(FAIL.getResCode(), FAIL.getResMsg());
    }

    public static ResponseResult error(int code, String msg) {
        return new ResponseResult(code, msg);
    }

    public static <T> ResponseResult<T> error(T data) {
        return new ResponseResult<>(FAIL.getResCode(), FAIL.getResMsg(), data);
    }

    public static <T> ResponseResult<T> error(int code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
