package jwt;

import java.util.Date;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 9:34 2020/2/3
 * @Modified By:
 */
public class JwtTestInfo {

    public String createToken() {
        JwtBuilder builder = Jwts.builder();
        builder.setIssuer("曹志国");
        builder.setIssuedAt(new Date());
        builder.setSubject("Jwt测试令牌");
        builder.signWith(SignatureAlgorithm.HS256, "caozg");
        return builder.compact();
    }

    public static String parseToken(String token) {
        Claims caozg = Jwts.parser().setSigningKey("caozg").parseClaimsJws(token).getBody();
        return caozg.toString();

    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiLmm7nlv5flm70iLCJpYXQiOjE1ODA2OTQyMDksInN1YiI6Ikp3dOa1i-ivleS7pOeJjCJ9.p_6Fi7Pz78Uri0RFQEgAg1y8jn27h2ZLUTfg3Eh0UjA";
        String s = parseToken(token);
        System.out.println(s);
    }
}
