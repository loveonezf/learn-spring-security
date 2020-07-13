package com.zf.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final String LOGIN_URL = "/login.html";

    private final String VALIDATE_CODE = "/login/getValidateCode";

//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    @Autowired
    private UserAuthenticationFailureHandler userAuthenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring()
                // system
                .antMatchers("/error")
                .antMatchers("/actuator/**")
                .antMatchers("/system/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/js/**")
                // swagger
                .antMatchers("/swagger-ui.html")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/images/**")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/configuration/ui")
                .antMatchers("/configuration/security")
                .antMatchers("/csrf");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
                .formLogin() // 表单登录
                // http.httpBasic() // HTTP Basic
                .loginPage(LOGIN_URL)      //指定了跳转到登录页面的请求URL
                .loginProcessingUrl("/login")   //对应登录页面form表单的action="/login"
                //.successHandler(userAuthenticationSuccessHandler)             // 处理登录成功
                .failureHandler(userAuthenticationFailureHandler)             //处理登录失败
                .and()
                .authorizeRequests()   // 授权配置
                .antMatchers(LOGIN_URL).permitAll()    //表示跳转到登录页面的请求不被拦截
                .antMatchers(VALIDATE_CODE).permitAll()
                .anyRequest()      // 所有请求
                .authenticated()    // 都需要认证
                .and().csrf().disable();
    }


}
