package com.neuq.info.authorization.manage;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.neuq.info.authorization.model.TokenModel;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;

/**
 * Created by lihang on 2017/4/4.
 */
public class TokenManagerImpl{
    public static void main(String[] args) {
        String token=createToken();
        checkToken(token);
    }
    public static String createToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withArrayClaim("name",new String[]{"1","2"})
                    .withIssuedAt(new Date())//在什么时候签发的(UNIX时间)
                    .withExpiresAt(new Date(System.currentTimeMillis()+60*60*24)) //什么时候过期，这里是一个Unix时间戳
                    .withIssuer("auth0")// iss 该JWT的签发者
                    .withNotBefore(new Date())//如果当前时间在nbf里的时间之前，则Token不被接受
                    .withAudience("接收该JWT的一方")//aud
                    .withSubject("该JWT所面向的用户")//sub
                    .sign(algorithm);

            System.out.println(token);
            return token;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkToken(String token) {
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiLmjqXmlLbor6VKV1TnmoTkuIDmlrkiLCJzdWIiOiLor6VKV1TmiYDpnaLlkJHnmoTnlKjmiLciLCJuYmYiOjE0OTEzMDcyODgsIm5hbWUiOlsiMSIsIjIiXSwiaXNzIjoiYXV0aDAiLCJleHAiOjE0OTEzMDcyODgsImlhdCI6MTQ5MTMwNzI4OH0.xHvrBdf3D4Qz11cptxhCeCHJOpcLyTjVcL86U2qjq4w";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            System.out.println();

            DecodedJWT jwt1 = JWT.decode(jwt.getToken());
            System.out.println(jwt1.getAlgorithm());
            System.out.println(jwt1.getClaims());
            System.out.println(jwt1.getExpiresAt());
            System.out.println(jwt1.getClaim("name").asArray(String.class).length);
            System.out.println(jwt1.getContentType());
            System.out.println(jwt1.getType());
            jwt1.getAlgorithm();
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }
        return false;
    }

    public TokenModel getToken(String authentication) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }
        return null;
    }

    public void deleteToken(long userId) {

    }
}
