package com.example.newsfeed.common.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String SECRET = "thisisspartamotherfucker";
//    private static final String BEARER_PREFIX = "Bearer";

    public String createToken(Long userId, String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(userId.toString())
                    .withClaim("email", email)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public DecodedJWT verifiedToken(String BearerToken) {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
//        DecodedJWT decodedJWT;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(SECRET);
//            JWTVerifier verifier =
//            DecodedJWT verifiedToken = JWT.require(algorithm) // specify any specific claim validations
//                    .withIssuer("auth0") // reusable verifier instance
//                    .build()
//                    .verify(token);
//
//            decodedJWT = verifier.verify(token);
//        } catch (
//                JWTVerificationException exception){
//            // Invalid signature/claims
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = BearerToken.substring(7);
            return JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token);

        } catch (JWTVerificationException jwtVerificationException) {
            throw new RuntimeException(jwtVerificationException);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


//    public String createToken(Long userId, String email) throws UnsupportedEncodingException {
//        Algorithm algorithm = Algorithm.HMAC256(SECRET);
//
//        String token = JWT.create()
//                .withIssuer("example.com")
//                .withSubject(userId.toString())
//                .withClaim("email", email)
//                .withIssuedAt(new Date())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
//                .sign(algorithm);
//        return token;
//    }
//
//    public String extractEmailfromBearerToken(String bearerToken) throws UnsupportedEncodingException {
//        Algorithm algorithm = Algorithm.HMAC256(SECRET);
//        String token = bearerToken.substring(BEARER_PREFIX.length()).trim();
//        DecodedJWT decodedToken = JWT.require(algorithm)
//                .withIssuer("example.com")
//                .build()
//                .verify(token);
//
//        String email = decodedToken.getClaim("email").asString();
//        return email;
//    }
}
