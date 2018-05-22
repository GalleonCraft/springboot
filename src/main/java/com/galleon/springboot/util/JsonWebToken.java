package com.galleon.springboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.galleon.springboot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by  406525372@qq.com
 * on 2018/3/12
 **/
public class JsonWebToken {
    private static final Logger logger = LoggerFactory.getLogger(JsonWebToken.class);
    private static final String SECRET = "Winner!Winner!Chicken!Dinner!";
    /**
     * Create Token
     *
     * @throws Exception
     */
    public static String createToken(User user,String requesterIp) throws Exception{

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
                //添加过气时间
                .withExpiresAt(expiresTime)
                //添加签发时间
                .withIssuedAt(initDate)
                .sign(Algorithm.HMAC256(SECRET+requesterIp));
        return token;
    }

    public static Map<String,Claim> verifyToken(String token,String requesterIp) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET+requesterIp)).build();

        try{
            DecodedJWT jwt=verifier.verify(token);
            return jwt.getClaims();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
