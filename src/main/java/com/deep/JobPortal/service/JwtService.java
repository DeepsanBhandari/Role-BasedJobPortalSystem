package com.deep.JobPortal.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService  {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public String generateToken(UserDetails userDetails){

        //Get current time in milliseconds
        long currentTimeMills =System.currentTimeMillis();

        // Calculate expiration time (current time +24 hours)
        long expirationTimeMills =currentTimeMills+EXPIRATION_TIME;

        //Create dates
        Date issuedAT = new Date(currentTimeMills);
        Date expiresAt= new Date(expirationTimeMills);

        //Build and return token
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAT)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }

    public String extractEmail(String token){
        Claims claims =extractAllClaims(token);
        String email=claims.getSubject();
        return email;
    }

    public Date extractExpiration(String token){
        Claims claims = extractAllClaims(token);
        Date expiration=claims.getExpiration();
        return expiration;
    }

    public boolean isTokenExpired(String token){
        Date expiration=extractExpiration(token);
        Date now=new Date();

        //Check if expiration is before now
        boolean expired = expiration.before(now);
        return expired;
    }

    /*
    Validate token
    1. Extracts email from token
    2. Checks if email matches the user
    3. Checks if token is not expired
    4. Returns true only if both checks pass
     */
    public boolean isTokenValid(String token, UserDetails userDetails){

        // Extract email from token
        String emailFromToken=extractEmail(token);

        //Get email from user
        String emailfromUser= userDetails.getUsername();

        //Check if emails match
        boolean emailMatches= emailFromToken.equals(emailfromUser);

        //Check if token is not expired
        boolean notExpired=!isTokenExpired(token);

        // Both must be true
        boolean valid=emailMatches&&notExpired;

        return valid;
    }

    /*
    Extract all claims from token

    What does it does
    1. Takes JWT token string
    2. Splits it into header.paload.signature
    3. Decodes header and payload
    4. Verify signature using secret key
    5. if signature valid return data else exception
     */

    private Claims extractAllClaims(String token) {
        Claims claims = Jwts.parserBuilder()
               .setSigningKey(getSigningKey())   //use our secret key
                .build()
                .parseClaimsJws(token)        //verify
                .getBody();

        return claims;
    }

    /*
     * Convert secret string to Key object

     * What this does:
     * 1. Our SECRET_KEY is a Base64 string
     * 2. Decode it to bytes
     * 3. Convert bytes to Key object for signing
     */

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }




}
