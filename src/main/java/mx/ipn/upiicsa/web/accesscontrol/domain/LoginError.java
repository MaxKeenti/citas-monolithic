package mx.ipn.upiicsa.web.accesscontrol.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginError {
    INVALID_CREDENTIALS("Invalid credentials provided"),
    USER_INACTIVE("User account is inactive");

    private final String message;
}
