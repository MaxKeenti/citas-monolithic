package mx.ipn.upiicsa.web.accesscontrol.application.service;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.dto.LoginDto;
import mx.ipn.upiicsa.web.accesscontrol.application.port.out.LoginRepository;
import mx.ipn.upiicsa.web.accesscontrol.application.port.in.LoginService;
import mx.ipn.upiicsa.web.accesscontrol.domain.LoginError;
import mx.ipn.upiicsa.web.accesscontrol.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Either<LoginError, Persona> login(LoginDto login) {
        String encodedPassword = encodePassword(login.getPassword());
        var resultadoLogin = loginRepository.findByLoginAndPassword(login.getUsername(), encodedPassword);
        Either<LoginError, Persona> resultado;
        if (resultadoLogin.isPresent()) {
            var persona = resultadoLogin.get();
            log.info("User authenticated successfully: {}", login.getUsername());
            if (!persona.getUser().getActive()) {
                log.warn("Login attempt for inactive user: {}", login.getUsername());
                resultado = Either.left(LoginError.USER_INACTIVE);
            } else {
                resultado = Either.right(persona);
            }
        } else {
            resultado = Either.left(LoginError.INVALID_CREDENTIALS);
            log.warn("Authentication failure for user: {}", login.getUsername());
        }
        return resultado;
    }

    private String encodePassword(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding password", e);
        }
    }
}
