package com.gg.model.security.util;

import com.gg.model.security.domain.JwtProperties;
import org.springframework.security.core.Authentication;


public class JwtUtil {

    private final JwtProperties jwtproperties = new JwtProperties();

    public String createToken(Authentication authentication){
        return null;
    }
}
