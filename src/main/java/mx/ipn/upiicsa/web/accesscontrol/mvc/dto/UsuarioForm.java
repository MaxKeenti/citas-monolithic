package mx.ipn.upiicsa.web.accesscontrol.mvc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioForm {
    @NotNull(message = "Seleccione una persona")
    private Integer idPersona;

    @NotNull(message = "Seleccione un rol")
    private Integer idRol;

    @NotBlank(message = "Favor de proporcionar el login (correo)")
    @Email(message = "El login debe ser un correo electrónico válido")
    private String login;

    @NotBlank(message = "Favor de proporcionar la contraseña")
    private String password;

    private Boolean activo = true;

    // getters/setters
    public Integer getIdPersona() { return idPersona; }
    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }
    public Integer getIdRol() { return idRol; }
    public void setIdRol(Integer idRol) { this.idRol = idRol; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
