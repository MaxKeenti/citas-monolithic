package mx.ipn.upiicsa.web.accesscontrol.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Persona {
    private Integer id;
    private Integer genderId;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private Usuario user;
}
