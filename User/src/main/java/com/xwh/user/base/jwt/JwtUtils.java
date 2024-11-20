package com.xwh.user.base.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    private static final String TOKEN_KEY = "4d7ce5b7-eb80-9e8a-bb2e-25852b1175d94d7ce5b7-eb80-9e8a-bb2e-25852b1175d9";
    private static final JWSHeader HEADER = new JWSHeader(JWSAlgorithm.HS256);

    private static <T> String getString(T user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    public <T> String sign(T user) {
        if (user == null) return null;
        try {
            String data = getString(user);
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder() //
                    .subject("user") //
                    .issuer("xwh") //
                    .expirationTime(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) //
                    .claim("user", data) //
                    .build();
            SignedJWT signedJWT = new SignedJWT(HEADER, claimsSet);
            JWSSigner signer = new MACSigner(TOKEN_KEY.getBytes());
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (Exception e) {
            log.debug("错误: {}", e.getMessage());
        }
        return null;
    }

    public String verify(String token) {
        if (token == null) return null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(TOKEN_KEY.getBytes());
            if (signedJWT.verify(verifier)) {
                JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
                return claimsSet.getStringClaim("user");
            }
        } catch (Exception e) {
            log.debug("错误: {}", e.getMessage());
        }
        return null;
    }

}
