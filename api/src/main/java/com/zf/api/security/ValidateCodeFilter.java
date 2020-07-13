package com.zf.api.security;

import com.zf.service.sys.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equalsIgnoreCase("/login", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validateCode(request);
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validateCode(HttpServletRequest request) throws ServletException {
        String validate_code = request.getParameter("imageCode");
        String uuid = request.getParameter("uuid");
        if (StringUtils.isBlank(validate_code)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (StringUtils.isBlank(uuid)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        String validateCode = userService.getValidateCode(uuid);
        if (StringUtils.isEmpty(validateCode)) {
            throw new ValidateCodeException("验证码不存在！");
        }
        if (!StringUtils.equalsIgnoreCase(validate_code, validateCode)) {
            throw new ValidateCodeException("验证码错误！");
        }
    }
}
