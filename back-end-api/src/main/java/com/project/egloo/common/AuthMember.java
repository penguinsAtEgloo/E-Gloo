package com.project.egloo.common;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.TYPE})
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member")
@Documented
public @interface AuthMember {
}
