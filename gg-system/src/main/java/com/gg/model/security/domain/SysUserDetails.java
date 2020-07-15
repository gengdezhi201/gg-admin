package com.gg.model.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class SysUserDetails extends User {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickName;

    public SysUserDetails(Integer userId,String nickName,String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.nickName = nickName;
    }

    public SysUserDetails(Integer userId,String nickName,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
        this.nickName = nickName;
    }
}
