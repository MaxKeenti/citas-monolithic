package mx.ipn.upiicsa.web.accesscontrol.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Usuario {
    private Integer id;
    private Integer idRol;
    private String login;
    private String password;
    private Boolean activo;
}
