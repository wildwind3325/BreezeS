package org.clkg.breezes.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Jwt {

    @Value("${jwt.expire}")
    private long expire;

    @Value("${jwt.secret}")
    private String secret;

    public String create(Object obj) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder().setSubject("obj").signWith(key).compact();
    }
}
