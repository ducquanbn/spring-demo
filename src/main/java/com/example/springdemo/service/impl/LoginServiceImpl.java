package com.example.springdemo.service.impl;

import com.example.springdemo.common.ErrorCode;
import com.example.springdemo.entity.CustomerEntity;
import com.example.springdemo.handleException.BaseException;
import com.example.springdemo.repository.CustomerRepository;
import com.example.springdemo.request.LoginRequestDTO;
import com.example.springdemo.response.dto.AuthenticationResponseDTO;
import com.example.springdemo.service.LoginService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {


    @Value("${jwt.signerKey}")
    private String MAC_SIGNER;

    private final CustomerRepository customerRepository;

    public LoginServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public AuthenticationResponseDTO login(LoginRequestDTO loginRequest) {
        return AuthenticationResponseDTO.builder()
                .token(generateToken(loginRequest))
                .authenticated(true)
                .build();
    }

    @Override
    public boolean verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(MAC_SIGNER.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verify = signedJWT.verify(verifier);

        return verify && expDate.after(new Date());
    }


    private String generateToken(LoginRequestDTO request) {

        if (!verifyPassword(request)) {
            throw new BaseException(ErrorCode.WRONG_PASSWORD);
        }

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(request.getUsername())
                .issuer("spring.vn")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("phoneNumber", "0123456789")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(MAC_SIGNER.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verifyPassword(LoginRequestDTO request) {
        CustomerEntity customer = customerRepository.findByUsername(request.getUsername());

        if (customer == null) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), customer.getPassword());
    }
}
