package com.example.midtestlms.config;

import com.example.midtestlms.service.MemberService;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberService memberService;

    @Autowired
    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리에 대한 접근에 대해서 접근 권한 인증 절차를 거치지 않도록 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")      // '/admin'으로 시작하는 url은 admin만 접근 가능하도록
                .antMatchers("/member/new").permitAll()
                .antMatchers("/member/login").permitAll()
                .antMatchers("/member/**").hasRole("MEMBER")    // '/member'로 시작하는 url은 member만 접근 가능하도록
                .antMatchers("/**").permitAll()                 // 나머지 url은 모든 사용자가 접근 가능
                .and() // 로그인 설정
                .formLogin()
                .usernameParameter("email") // 로그인 요청 시 아이디에 대한 <input> name을 "username"이 아니라 "email"로 설정하겠다.
                .passwordParameter("pwd")   // 로그인 요청 시 패스워드에 대한 <input> name을 "password"가 아니라 "pwd"로 설정하겠다.
                .loginPage("/member/login") // 로그인 페이지 url을 '/member/login'으로 잡겠다.
                .defaultSuccessUrl("/")     // 로그인 성공 시 index 페이지로 이동하겠다.
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))  // 로그아웃 처리에 대한 spring security와의 url 매핑
                .logoutSuccessUrl("/")          // 로그아웃 성공 시 index 페이지로 이동하겠다.
                .deleteCookies("JSESSIONID")    // 로그아웃 시 쿠키를 제거 : 로그아웃 했는데 로그인이 되어있다거나 하는 예외 상황 발생하지 않도록.
                .invalidateHttpSession(true)    // 로그아웃 시 세션 제거 : 로그아웃 했는데 로그인이 되어있다거나 하는 예외 상황 발생하지 않도록.
                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/member/denied")
                .and()
                .rememberMe()
                .key("uniqueAndSecret");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
