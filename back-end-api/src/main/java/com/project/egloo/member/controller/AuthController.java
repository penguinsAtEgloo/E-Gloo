package com.project.egloo.member.controller;

import com.project.egloo.config.jwt.JwtFilter;
import com.project.egloo.config.jwt.TokenProvider;
import com.project.egloo.member.dto.request.LoginRequest;
import com.project.egloo.member.dto.response.TokenResponse;
import com.project.egloo.member.dto.response.UserProfile;
import com.project.egloo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody LoginRequest loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenResponse(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity memberInfo(String token) throws ParseException {
        UserProfile member = memberService.memberInfo(token);
        return ResponseEntity.ok(member);
    }
}
