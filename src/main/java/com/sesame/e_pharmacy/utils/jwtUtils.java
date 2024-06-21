package com.sesame.e_pharmacy.utils;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.sesame.e_pharmacy.entity.User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class jwtUtils {

    public static String createToken(User user, String secretKey) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getUsername())
                .issuer("https://www.sesame.com")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("roles", user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))
                .build();
        var encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(secretKey.getBytes())
        );
        var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(),claims);
        return encoder.encode(params).getTokenValue();
    }

}