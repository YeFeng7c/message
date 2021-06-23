package com.yefeng.message.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yefeng.message.model.User;

import java.util.Date;
import java.util.List;

public class TokenUtils {
        private static final long EXPIRE_TIME= 10*60*60*1000;
        private static final String TOKEN_SECRET="love";  //密钥盐

        public static String sign(String email){
            String token = null;
            try {
                Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
                token = JWT.create()
                        .withIssuer("auth0")
                        .withClaim("email", email)
                        .withExpiresAt(expiresAt)
                        // 使用了HMAC256加密算法。
                        .sign(Algorithm.HMAC256(TOKEN_SECRET));
            } catch (Exception e){
                e.printStackTrace();
            }
            return token;
        }

        /**
         * 签名验证
         * @param token
         * @return
         */
        public static boolean verify(String token){
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
                DecodedJWT jwt = verifier.verify(token);
                System.out.println("认证通过：");
                System.out.println("email: " + jwt.getClaim("email").asString());
                System.out.println("过期时间：      " + jwt.getExpiresAt());
                return true;
            } catch (Exception e){
                return false;
            }
        }
    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserEmail(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("email").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    }






