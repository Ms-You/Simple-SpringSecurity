package com.cos.security.config.auth;

import com.cos.security.model.User;
import com.cos.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// login 요청이 오면 자동으로 UserDetailsService 타입의 loadUserByUsername 메서드가 실행됨
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    // session 내부에 UserDetails 를 갖는 Authentication 이 들어감
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
