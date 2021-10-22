package com.project.egloo.member.service;

import com.project.egloo.config.CookieUtils;
import com.project.egloo.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.Cookie;

import static com.project.egloo.config.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_REDIRECT;
import static com.project.egloo.config.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_STATE;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final String REDIRECT_URL = "http://localhost:3000";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Optional<String> redirect = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_REDIRECT).map(Cookie::getValue);
        Optional<String> state = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_STATE).map(Cookie::getValue);


        String token = tokenProvider.createToken(authentication);
        String targetUrl = UriComponentsBuilder.fromUriString(REDIRECT_URL + redirect.get())
            .queryParam("token", token)
            .queryParam("state", state)
            .build().toUriString();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}