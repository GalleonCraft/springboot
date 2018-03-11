package com.galleon.springboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.galleon.springboot.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by  406525372@qq.com
 * on 2018/3/12
 **/
public class JsonWebToken {
    private static final String SECRET = "Winner!Winner!Chicken!Dinner!";
    /**
     * Create Token
     *
     * @throws Exception
     */
    public static String createToken(User user) throws Exception{

        Date initDate = new Date();
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.MINUTE,1);
        Date expiresTime = currentTime.getTime();

        Map<String,Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("type","JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("name",user.getUsername())
                .withClaim("role",user.getRole().getName())
                .withExpiresAt(expiresTime)//添加过气时间
                .withIssuedAt(initDate)//添加签发时间
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static Map<String,Claim> verifyToken(String token) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt;
        try{
            jwt=verifier.verify(token);
        }catch (Exception e){
            throw new RuntimeException("凭证已过气！");
        }
        return jwt.getClaims();
    }
}
