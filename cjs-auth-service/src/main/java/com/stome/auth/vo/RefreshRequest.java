package com.stome.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc xxxx
 * @date 2020/5/26 23:32
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshRequest {
    private String userId;
    private String refreshToken;
}
