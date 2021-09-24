package com.project.egloo.config.jwt;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    public void createTokenTest() throws JSONException, IOException {
        // given
        Authentication authentication = new TestingAuthenticationToken("user-name", "secure-password-1234");

        // when
        String token = tokenProvider.createToken(authentication);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] headerBytes = decoder.decode(token.split("\\.")[0]);
        byte[] bodyBytes = decoder.decode(token.split("\\.")[1]);

        JSONObject header = new JSONObject(new String(headerBytes));
        JSONObject body = new JSONObject(new String(bodyBytes));

        // then
        assertThat(header.get("alg")).isEqualTo("HS512");

        assertThat(body.get("sub")).isEqualTo("user-name");
        assertThat(body.get("auth")).isEqualTo("");
    }

}