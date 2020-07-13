package com.zf.api.security;

import com.zf.service.sys.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, AuthenticationUserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user= userService.getUserByLoginName(userName);
        if(user==null)
        {
            throw new UsernameNotFoundException("用户名不存在");
        }
        else
        {
            if(!user.isEnabled())
            {
                throw new UsernameNotFoundException("用户名已停用");
            }
        }
        return new User(userName, passwordEncoder.encode(user.getPassword()), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        //String pwd= passwordEncoder.encode(user.getPassword());
        //user.setPassWord(pwd);
        //return user;
    }

    @Override
    public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {
        return null;
    }
}
