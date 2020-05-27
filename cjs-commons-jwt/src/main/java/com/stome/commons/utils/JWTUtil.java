package com.stome.commons.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stome.commons.exception.TokenAuthenticationException;

import java.util.Date;

import static com.stome.commons.enums.ResponseCodeEnum.TOKEN_EXPIRED;
import static com.stome.commons.enums.ResponseCodeEnum.TOKEN_INVALID;
import static com.stome.commons.enums.ResponseCodeEnum.TOKEN_SIGNATURE_INVALID;
import static com.stome.commons.enums.ResponseCodeEnum.UNKNOWN_ERROR;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc Token 工具类
 * @date 2020/5/26 21:41
 */
public class JWTUtil {
    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;
    private static final String ISSUER = "stone";

    /**
     * 生成Token
     *
     * @param userName  用户标识（不一定是用户名，有可能是用户ID或者手机号)
     * @param secretKey
     * @return
     */
    public static String generateToken(String userName, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        String token = JWT.create().withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withClaim("userName", userName)
                .sign(algorithm);
        return token;
    }

    public static void verifyToken(String token, String secretKey) throws TokenAuthenticationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            jwtVerifier.verify(token);
        } catch (JWTDecodeException jwtDecodeException) {
            throw new TokenAuthenticationException(TOKEN_INVALID.getResCode(), TOKEN_INVALID.getResMsg());
        } catch (SignatureVerificationException signatureVerificationException) {
            throw new TokenAuthenticationException(TOKEN_SIGNATURE_INVALID.getResCode(), TOKEN_SIGNATURE_INVALID.getResMsg());
        } catch (TokenExpiredException tokenExpiredException) {
            throw new TokenAuthenticationException(TOKEN_EXPIRED.getResCode(), TOKEN_EXPIRED.getResMsg());
        } catch (Exception exception) {
            throw new TokenAuthenticationException(UNKNOWN_ERROR.getResCode(), UNKNOWN_ERROR.getResMsg());
        }
    }


    public static String getUserInfo(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String userName = decodedJWT.getClaim("userName").toString();
        return userName;
    }
}
