package com.stome.auth.controller;

import com.stome.auth.vo.LoginRequest;
import com.stome.auth.vo.LoginResponse;
import com.stome.auth.vo.RefreshRequest;
import com.stome.commons.entity.ResponseResult;
import com.stome.commons.enums.ResponseCodeEnum;
import com.stome.commons.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc 登陆controller
 * @date 2020/5/26 23:17
 */

@RestController
public class LoginController {

    /**
     * Apollo 或 Nacos

    @Value("${secretKey:123456}")
     */
    private String secretKey="123456";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Validated LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseResult.error(ResponseCodeEnum.PARAMETER_ILLEGAL.getResCode(), ResponseCodeEnum.PARAMETER_ILLEGAL.getResMsg());
        }

        String username = request.getUserName();
        String password = request.getPassWord();
        //  假设查询到用户ID是1001
        String userId = "1001";
        if ("hello".equals(username) && "world".equals(password)) {
            //  生成Token
            String token = JWTUtil.generateToken(userId, secretKey);

            //  生成刷新Token
            String refreshToken = UUID.randomUUID().toString().replace("-", "");

            //  放入缓存
            HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
//            hashOperations.put(refreshToken, "token", token);
//            hashOperations.put(refreshToken, "user", username);
//            stringRedisTemplate.expire(refreshToken, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

            /**
             * 如果可以允许用户退出后token如果在有效期内仍然可以使用的话，那么就不需要存Redis
             * 因为，token要跟用户做关联的话，就必须得每次都带一个用户标识，
             * 那么校验token实际上就变成了校验token和用户标识的关联关系是否正确，且token是否有效
             */

//            String key = MD5Encoder.encode(userId.getBytes());

            String key = userId;
            hashOperations.put(key, "token", token);
            hashOperations.put(key, "refreshToken", refreshToken);
            stringRedisTemplate.expire(key, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setRefreshToken(refreshToken);
            loginResponse.setUserName(userId);

            return ResponseResult.success(loginResponse);
        }

        return ResponseResult.error(ResponseCodeEnum.LOGIN_ERROR.getResCode(), ResponseCodeEnum.LOGIN_ERROR.getResMsg());
    }

    /**
     * 退出
     */
    @GetMapping("/logout")
    public ResponseResult logout(@RequestParam("userId") String userId) {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String key = userId;
        hashOperations.delete(key);
        return ResponseResult.success();
    }

    /**
     * 刷新Token
     */
    @PostMapping("/refreshToken")
    public ResponseResult refreshToken(@RequestBody @Validated RefreshRequest request, BindingResult bindingResult) {
        String userId = request.getUserId();
        String refreshToken = request.getRefreshToken();
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String key = userId;
        String originalRefreshToken = hashOperations.get(key, "refreshToken");
        if (StringUtils.isBlank(originalRefreshToken) || !originalRefreshToken.equals(refreshToken)) {
            return ResponseResult.error(ResponseCodeEnum.REFRESH_TOKEN_INVALID.getResCode(), ResponseCodeEnum.REFRESH_TOKEN_INVALID.getResMsg());
        }

        //  生成新token
        String newToken = JWTUtil.generateToken(userId, secretKey);
        hashOperations.put(key, "token", newToken);
        stringRedisTemplate.expire(userId, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        return ResponseResult.success(newToken);
    }
}