package com.project.egloo.config.security;

import com.project.egloo.config.jwt.JwtAccessDeniedHandler;
import com.project.egloo.config.jwt.JwtAuthenticationEntryPoint;
import com.project.egloo.config.jwt.JwtSecurityConfig;
import com.project.egloo.config.jwt.TokenProvider;
import com.project.egloo.member.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2UserService oAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(
                "/h2-console/**"
                , "/favicon.ico"
                , "/error", "/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**"


            );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
            .csrf().disable()

            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // enable h2-console
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            // 세션을 사용하지 않기 때문에 STATELESS로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()

            // health check
            .antMatchers(HttpMethod.GET, "/api/v1/healthCheck").permitAll()

            //user
            .antMatchers("/auth/login").permitAll()
            .antMatchers("/user/signup").permitAll()
            //recipe
            .antMatchers("/api/v1/recipes").permitAll()
            .antMatchers(HttpMethod.DELETE, "/api/v1/recipes/*").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            //ingredient
            .and()
            .apply(new JwtSecurityConfig(tokenProvider))

            .and().oauth2Login().userInfoEndpoint().userService(oAuth2UserService);
    }

}
