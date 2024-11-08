package com.xwh.user.base.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    private static final String TOKEN_KEY = "4d7ce5b7-eb80-9e8a-bb2e-25852b1175d94d7ce5b7-eb80-9e8a-bb2e-25852b1175d9";
    private final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

    public <T> String sign(T user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(user);
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder() //
                    .subject("user") //
                    .issuer("xwh") //
                    .expirationTime(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) //
                    .claim("username", data) //
                    .build();
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);
            JWSSigner signer = new MACSigner(TOKEN_KEY.getBytes());
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException | JsonProcessingException e) {
            log.debug(e.getMessage());
        }
        return null;
    }

    public String verify(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(TOKEN_KEY.getBytes());
            Assert.isTrue(signedJWT.verify(verifier), "error");
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            return claimsSet.getStringClaim("username");
        } catch (ParseException | JOSEException e) {
            log.debug(e.getMessage());
        }
        return null;
    }

}
