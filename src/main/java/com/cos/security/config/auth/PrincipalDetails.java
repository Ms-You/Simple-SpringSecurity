package com.cos.security.config.auth;

import com.cos.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// SecurityConfig 로 인해 시큐리티가 login 요청이 오면 대신 로그인 해줌
// 로그인이 완료되면 시큐리티가 session 을 만들어 주는데 Security ContextHolder 에 세션 정보를 저장시킴
// session 에 들어갈 수 있는 타입은 Authentication 객체가 필요하고
// 또 Authentication 안에 User 정보가 있어야 하는데 UserDetails 타입이 되어야 넣을 수 있음

// 즉, Security Session => Authentication => UserDetails 임

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }

    // 해당 user의 권한을 리턴해줌
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 일정 기간동안 로그인을 하지 않으면 휴먼 계정으로 전환

        return true;
    }
}
