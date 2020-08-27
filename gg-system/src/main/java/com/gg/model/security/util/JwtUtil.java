package com.gg.model.security.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.gg.model.security.domain.JwtProperties;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.system.domain.SysUser;
import com.gg.util.RedisUtils;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil implements InitializingBean {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    RedisUtils redisUtil;

    private Key key;

    private static final String AUTHORITIES_KEY = "auth";

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 获取登录用户
     *
     * @param token
     * @return SysUserDetails
     */
    public SysUserDetails getLoginUser(String token) {
        Object obj = redisUtil.get(jwtProperties.getOnlineKey()+":"+token);
        if (obj != null) {
            return (SysUserDetails) obj;
        } else {
            return null;
        }
    }

    /**
     * 储存用户信息
     *
     * @param token
     * @return SysUserDetails
     */
    public boolean setLoginUser(String token, SysUserDetails sysUserDetails) {
//        SysUser user = new SysUser();
//        user.setUserId(sysUserDetails.getUser().getUserId());
//        user.setUserName(sysUserDetails.getUsername());
//        user.setPassword(sysUserDetails.getPassword());
        return redisUtil.set(jwtProperties.getOnlineKey()+":"+ token,sysUserDetails,jwtProperties.getTokenValidityInSeconds());
    }

    /**
     * 生成token
     *
     * @param authentication
     * @return token
     */
    public String createToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        claims.put("userId", sysUserDetails.getUser().getUserId());
        claims.put("nickName", sysUserDetails.getUser().getNickName());
        claims.put("userName", sysUserDetails.getUsername());
        claims.put("permission", sysUserDetails.getPermissions());

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
                .signWith(key, SignatureAlgorithm.HS512)
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
    public String getToken(HttpServletRequest request) {
        //与前台约定好的header名
        String token = request.getHeader(jwtProperties.getHeader());
        //验证token是不是空以及token是不是以约定好的Bearer开头
        if (StringUtils.isNotEmpty(token) && token.startsWith(jwtProperties.getTokenStartWith())) {
            //移除"Bearer "只留下token
            token = token.replace(jwtProperties.getTokenStartWith(), "");
        }
        return token;
    }

    /**
     * 解析token
     *
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

        SysUser user = new SysUser();
        Set<String> permission = new HashSet<>();
        user.setNickName(claims.get("nickName").toString());
        user.setUserId(Integer.parseInt(claims.get("userId").toString()));
        user.setUserName(claims.get("userName").toString());
        if(claims.get("permission")!=null){
            permission = new HashSet<>((List<String>) claims.get("permission")) ;
        }
        Collection<? extends GrantedAuthority> authorities =
                permission.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        SysUserDetails principal = new SysUserDetails(user,permission);
        return new UsernamePasswordAuthenticationToken(principal, token,authorities);
    }

}
