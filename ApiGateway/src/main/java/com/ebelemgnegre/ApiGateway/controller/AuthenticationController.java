package com.ebelemgnegre.ApiGateway.controller;

import com.ebelemgnegre.ApiGateway.model.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @GetMapping("/")
    public ResponseEntity<AuthenticationResponse> login(
            @AuthenticationPrincipal OidcUser oidcUser,
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client) {
        String refreshTokenValue = client.getRefreshToken() != null ? client.getRefreshToken().getTokenValue() : null;

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .userId(oidcUser.getEmail())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(refreshTokenValue)
                .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .auhorityList(oidcUser.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
