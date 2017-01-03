package com.kawnayeen.logger.security.token.auth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawnayeen.logger.model.LoggerUser;
import com.kawnayeen.logger.model.entity.Account;
import com.kawnayeen.logger.model.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.*;

/**
 * Created by kawnayeen on 1/3/17.
 */
public class JwtUtil {

    private static final String ISSUER = "LoggerApplication";
    private static final String ROLE = "Roles";
    private static final String SIGNATURE_KEY = "angel";
    private byte[] signingByte = SIGNATURE_KEY.getBytes();
    ObjectMapper mapper = new ObjectMapper();

    public String generateToken(LoggerUser loggerUser){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(loggerUser.getUsername())
                .setId(loggerUser.getId().toString())
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()));
        try {
            String roleStr = mapper.writeValueAsString(loggerUser.getRoles());
            jwtBuilder.claim(ROLE,roleStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jwtBuilder.claim(ROLE,"{}");
        }

        jwtBuilder.signWith(SignatureAlgorithm.HS256, signingByte);
        return jwtBuilder.compact();
    }

    public LoggerUser getLoggerUser(String jsonWebToken){
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(signingByte).parseClaimsJws(jsonWebToken).getBody();
        }catch (Exception e){
            return null;
        }
        Account account = new Account();
        account.setId(Long.parseLong(claims.getId()));
        account.setUsername(claims.getSubject());
        try {
            account.setRoles( mapper.readValue(claims.get(ROLE).toString(), new TypeReference<ArrayList<Role>>(){}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LoggerUser(account,true);
    }
}
