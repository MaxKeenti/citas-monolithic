package mx.ipn.upiicsa.web.controlacceso.internal.input;

import io.vavr.control.Either;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.LoginDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;

public interface LoginService {
    Either<Integer, Persona> login(LoginDto login);
}
