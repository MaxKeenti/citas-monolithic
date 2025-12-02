package mx.ipn.upiicsa.web.accesscontrol.service;

import io.vavr.control.Either;
import mx.ipn.upiicsa.web.accesscontrol.mvc.dto.LoginDto;
import mx.ipn.upiicsa.web.accesscontrol.service.model.Persona;

public interface LoginService {
    Either<Integer, Persona> login(LoginDto login);
}
