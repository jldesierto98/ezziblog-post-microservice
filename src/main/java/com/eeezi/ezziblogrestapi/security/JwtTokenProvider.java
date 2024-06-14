package com.eeezi.ezziblogrestapi.security;

import com.eeezi.ezziblogrestapi.exception.BlogAPIException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;

    //Jwt Generator
    public String generateToken(Authentication authentication){
        String userName = authentication.getName();

        Date currentDate = new Date();

        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .subject(userName)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(keys())
                .compact();

    }

    private Key keys() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Get the username from the JWT
    public String getUserName(String token){

         return  Jwts.parser()
                .verifyWith((SecretKey) keys())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    /**
     * Validates the provided JWT Token.
     *
     * @param token
     * @return boolean
     */
    public boolean validateToken(String token){

        try{
            Jwts.parser()
                    .verifyWith((SecretKey)keys())
                    .build()
                    .parse(token);
        } catch (MalformedJwtException malformedJwtException){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Tokne");
        } catch(ExpiredJwtException expiredJwtException){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT has already expired");
        } catch (UnsupportedJwtException unsupportedJwtException){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT not supported");
        } catch (IllegalArgumentException illegalArgumentException){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is null or empty");
        }

         return true;
    }




}
