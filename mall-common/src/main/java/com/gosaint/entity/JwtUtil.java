package com.gosaint.entity;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 10:04 2020/2/3
 * @Modified By:
 */
public class JwtUtil {
    /**
     * 有效时间30min
     */
    public static final Long JWT_TTL = 3600000L / 2;
    /**
     * Jwt令牌信息
     */
    public static final String JWT_KEY = "CAOZG";

    /**
     * 创建令牌
     *
     * @param id
     * @param sub
     * @param expire
     * @return
     */
    public static String createJwt(String id, String sub, Long expire) {
        SignatureAlgorithm signa = SignatureAlgorithm.HS256;
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        if (expire == null) {
            expire = JWT_TTL;
        }
        Long expireMill = currentTimeMillis + expire;
        SecretKey key = generalKey();
        Date expDate = new Date(expireMill);
        JwtBuilder builder = Jwts.builder().setId(id).setSubject(sub).setIssuer("曹志国").setIssuedAt(now)
                .signWith(signa, key).setExpiration(expDate);
        return builder.compact();

    }

    private static SecretKey generalKey() {
        byte[] encode = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKey key=new SecretKeySpec(encode,0,encode.length,"AES");
        return key;
    }

    /**
     * 解析Jwt
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt) {
        SecretKey key = generalKey();
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
    }
}
