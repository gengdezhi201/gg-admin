package com.gg.model.security.util;

import cn.hutool.core.util.IdUtil;
import com.gg.model.security.domain.JwtProperties;
import com.gg.model.security.domain.SysUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil implements InitializingBean {

    @Autowired
    JwtProperties jwtProperties;

    private Key key;

    private static final String AUTHORITIES_KEY = "auth";

    @Override
    public void afterPropertiesSet(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成token
     * @param authentication
     * @return token
     */
    public String createToken(Authentication authentication){
        Map<String, Object> claims = new HashMap<>();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        claims.put("userId",sysUserDetails.getUserId());
        claims.put("nickName",sysUserDetails.getNickName());
        claims.put("userName",sysUserDetails.getUsername());

        /**
         *iss:              |   签发者      |   setIssuer()
         * sub:             |   面向用户    |   setSubject()
         * aud:             |   接收者      |   setAudience()
         * iat(issued at):  |   签发时间    |   setIssuedAt()
         * exp(expires):    |   过期时间    |   setExpiration()
         * nbf(not before)：|    不能被接收处理时间，在此之前不能被接收处理   |setNotBefore()
         * jti：             |   JWT ID为web token提供唯一标识              |setId()
         *
         * setClaims() 把需要的信息先存入map 在存入setClaims()
         */
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setClaims(claims)
                .signWith(key,SignatureAlgorithm.HS512)
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .compact();
    }


    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request)
    {
        //与前台约定好的header名
        String token = request.getHeader(jwtProperties.getHeader());
        //验证token是不是空以及token是不是以约定好的Bearer开头
        if (StringUtils.isNotEmpty(token) && token.startsWith(jwtProperties.getTokenStartWith()))
        {
            //移除"Bearer "只留下token
            token = token.replace(jwtProperties.getTokenStartWith(), "");
        }
        return token;
    }

    /**
     * 解析token
     * @param token
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        //jwtapi0.11.1版本有parserBuilder方法
        Claims claims = Jwts.parserBuilder()
                //放入加密相同的key
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        /**
         * 解析成功后可以取到载荷总存的信息
         * 如userId userName 权限等
         * 用这些信息返回用户实体
         */
        List<GrantedAuthority> authorities;
        Set<String> set = new HashSet<>();
        set.add("ROLE_ADMIN");
        authorities= AuthorityUtils.createAuthorityList(set.toArray(new String[0]));
        //TODO 还没设计角色权限等信息 暂时写死角色
        SysUserDetails principal = new SysUserDetails(Integer.parseInt(claims.get("userId").toString()), claims.get("nickName").toString(), claims.get("userName").toString(),"", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
