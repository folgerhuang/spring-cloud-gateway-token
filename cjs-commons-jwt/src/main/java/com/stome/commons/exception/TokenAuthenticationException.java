package com.stome.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc xxxx
 * @date 2020/5/26 21:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenAuthenticationException extends Throwable {
    private int exCode;
    private String exMsg;
}
