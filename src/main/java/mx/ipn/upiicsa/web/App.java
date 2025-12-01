package mx.ipn.upiicsa.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

/*
GENERATED_SCAFFOLD: CRUD creation pages scaffolding (copy these into new files in the project)

-- FILE: src/main/java/mx/ipn/upiicsa/web/controlacceso/external/mvc/dto/PersonaForm.java
package mx.ipn.upiicsa.web.controlacceso.external.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PersonaForm {
    private Integer id;
    @NotNull(message = "Seleccione un género")
    private Integer idGenero;
    @NotBlank(message = "Favor de proporcionar el nombre")
    private String nombre;
    @NotBlank(message = "Favor de proporcionar el primer apellido")
    private String primerApellido;
    private String segundoApellido;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdGenero() { return idGenero; }
    public void setIdGenero(Integer idGenero) { this.idGenero = idGenero; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/controlacceso/external/jpa/repository/PersonaJpaRepository.java
package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<PersonaJpa,Integer> {
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/controlacceso/external/mvc/controller/PersonaController.java
package mx.ipn.upiicsa.web.controlacceso.external.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.GeneroJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.GeneroJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.PersonaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private GeneroJpaRepository generoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("personaForm", new PersonaForm());
        List<GeneroJpa> generos = generoJpaRepository.findAll();
        model.addAttribute("generos", generos);
        return "personas/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("personaForm") PersonaForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("generos", generoJpaRepository.findAll());
            return "personas/create";
        }
        PersonaJpa p = new PersonaJpa();
        p.setIdGenero(form.getIdGenero());
        p.setNombre(form.getNombre());
        p.setPrimerApellido(form.getPrimerApellido());
        p.setSegundoApellido(form.getSegundoApellido());
        personaJpaRepository.save(p);
        return "redirect:/personas/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("personas", personaJpaRepository.findAll());
        return "personas/list";
    }
}

-- FILE: src/main/resources/templates/personas/create.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Crear Persona</title>
</head>
<body>
<h1>Crear Persona</h1>
<form th:action="@{/personas/create}" th:object="${personaForm}" method="post">
    <div>
        <label>Género</label>
        <select th:field="*{idGenero}">
            <option th:each="g : ${generos}" th:value="${g.id}" th:text="${g.nombre}"></option>
        </select>
        <p th:if="${#fields.hasErrors('idGenero')}" th:errors="*{idGenero}"></p>
    </div>
    <div>
        <label>Nombre</label>
        <input type="text" th:field="*{nombre}" />
        <p th:if="${#fields.hasErrors('nombre')}" th:errors="*{nombre}"></p>
    </div>
    <div>
        <label>Primer Apellido</label>
        <input type="text" th:field="*{primerApellido}" />
        <p th:if="${#fields.hasErrors('primerApellido')}" th:errors="*{primerApellido}"></p>
    </div>
    <div>
        <label>Segundo Apellido</label>
        <input type="text" th:field="*{segundoApellido}" />
    </div>
    <div>
        <button type="submit">Crear</button>
    </div>
</form>
<p><a th:href="@{/personas/list}">Volver a la lista</a></p>
</body>
</html>

-- FILE: src/main/resources/templates/personas/list.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Personas</title>
</head>
<body>
<h1>Personas</h1>
<p><a th:href="@{/personas/create}">Crear nueva persona</a></p>
<table>
    <thead>
        <tr><th>ID</th><th>Nombre</th><th>Primer Apellido</th><th>Segundo Apellido</th></tr>
    </thead>
    <tbody>
        <tr th:each="p : ${personas}">
            <td th:text="${p.id}"></td>
            <td th:text="${p.nombre}"></td>
            <td th:text="${p.primerApellido}"></td>
            <td th:text="${p.segundoApellido}"></td>
        </tr>
    </tbody>
</table>
</body>
</html>

-- NOTES --
The block above is a scaffold for the Persona entity only. Use it as a template to create similar DTOs, repositories, controllers and templates for Usuario, Servicio, Sucursal, Empleado and Cita. Follow the same patterns: create minimal Form DTOs, JPA repositories that extend JpaRepository, controllers under external.mvc.controller with routes, and simple Thymeleaf templates under resources/templates/<entity>/. Make sure to import and autowire repositories for supporting tables (roles, establecimientos, listas, servicios, empleados) when building select lists.

End of GENERATED_SCAFFOLD
*/

/* === USUARIO_SCAFFOLD ===
-- FILE: src/main/java/mx/ipn/upiicsa/web/accesscontrol/mvc/dto/UsuarioForm.java
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

-- FILE: src/main/java/mx/ipn/upiicsa/web/accesscontrol/mvc/controller/UsuarioController.java
package mx.ipn.upiicsa.web.accesscontrol.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.accesscontrol.jpa.repository.RolJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.jpa.repository.UsuarioJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.mvc.dto.UsuarioForm;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import mx.ipn.upiicsa.web.accesscontrol.jpa.model.RolJpa;
import mx.ipn.upiicsa.web.accesscontrol.jpa.model.UsuarioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private RolJpaRepository rolJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("usuarioForm", new UsuarioForm());
        // personas that don't have usuario
        List<PersonaJpa> personas = personaJpaRepository.findAll()
                .stream().filter(p -> p.getUsuario() == null).collect(Collectors.toList());
        List<RolJpa> roles = rolJpaRepository.findAll().stream().filter(r -> Boolean.TRUE.equals(r.getActivo())).collect(Collectors.toList());
        model.addAttribute("personas", personas);
        model.addAttribute("roles", roles);
        return "accesscontrol/usuarios/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("usuarioForm") UsuarioForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("personas", personaJpaRepository.findAll().stream().filter(p -> p.getUsuario()==null).collect(Collectors.toList()));
            model.addAttribute("roles", rolJpaRepository.findAll().stream().filter(r -> Boolean.TRUE.equals(r.getActivo())).collect(Collectors.toList()));
            return "accesscontrol/usuarios/create";
        }
        UsuarioJpa u = new UsuarioJpa();
        u.setId(form.getIdPersona()); // id_usuario == id_persona
        u.setIdRol(form.getIdRol());
        u.setLogin(form.getLogin());
        u.setPassword(form.getPassword());
        u.setActivo(form.getActivo());
        usuarioJpaRepository.save(u);
        return "redirect:/usuarios/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("usuarios", usuarioJpaRepository.findAll());
        return "accesscontrol/usuarios/list";
    }
}

-- FILE: src/main/resources/templates/accesscontrol/usuarios/create.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Crear Usuario</title>
</head>
<body>
<h1>Crear Usuario</h1>
<form th:action="@{/usuarios/create}" th:object="${usuarioForm}" method="post">
    <div>
        <label>Persona</label>
        <select th:field="*{idPersona}">
            <option th:each="p : ${personas}" th:value="${p.id}" th:text="${p.nombre + ' ' + p.primerApellido}"></option>
        </select>
        <p th:if="${#fields.hasErrors('idPersona')}" th:errors="*{idPersona}"></p>
    </div>
    <div>
        <label>Rol</label>
        <select th:field="*{idRol}">
            <option th:each="r : ${roles}" th:value="${r.id}" th:text="${r.nombre}"></option>
        </select>
        <p th:if="${#fields.hasErrors('idRol')}" th:errors="*{idRol}"></p>
    </div>
    <div>
        <label>Login (correo)</label>
        <input type="text" th:field="*{login}" />
        <p th:if="${#fields.hasErrors('login')}" th:errors="*{login}"></p>
    </div>
    <div>
        <label>Password</label>
        <input type="password" th:field="*{password}" />
        <p th:if="${#fields.hasErrors('password')}" th:errors="*{password}"></p>
    </div>
    <div>
        <label>Activo</label>
        <input type="checkbox" th:field="*{activo}" />
    </div>
    <div>
        <button type="submit">Crear</button>
    </div>
</form>
<p><a th:href="@{/usuarios/list}">Volver a la lista</a></p>
</body>
</html>

-- FILE: src/main/resources/templates/accesscontrol/usuarios/list.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Usuarios</title>
</head>
<body>
<h1>Usuarios</h1>
<p><a th:href="@{/usuarios/create}">Crear nuevo usuario</a></p>
<table>
    <thead>
        <tr><th>ID</th><th>Login</th><th>Rol</th><th>Activo</th></tr>
    </thead>
    <tbody>
        <tr th:each="u : ${usuarios}">
            <td th:text="${u.id}"></td>
            <td th:text="${u.login}"></td>
            <td th:text="${u.rol != null ? u.rol.nombre : ''}"></td>
            <td th:text="${u.activo}"></td>
        </tr>
    </tbody>
</table>
</body>
</html>
*/

/* === SERVICIO_SCAFFOLD ===
-- FILE: src/main/java/mx/ipn/upiicsa/web/catalog/mvc/dto/ServicioForm.java
package mx.ipn.upiicsa.web.catalog.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ServicioForm {
    private Integer id;
    @NotBlank(message = "Favor de proporcionar el nombre del servicio")
    private String nombre;
    private String descripcion;
    @NotNull(message = "Favor de proporcionar la duración en minutos")
    private Integer duracion;
    private Boolean activo = true;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/catalog/jpa/repository/ServicioJpaRepository.java
package mx.ipn.upiicsa.web.catalog.jpa.repository;

import mx.ipn.upiicsa.web.catalog.jpa.model.ServicioJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioJpaRepository extends JpaRepository<ServicioJpa,Integer> {
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/catalog/mvc/controller/ServicioController.java
package mx.ipn.upiicsa.web.catalog.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.catalog.jpa.model.ServicioJpa;
import mx.ipn.upiicsa.web.catalog.jpa.repository.ServicioJpaRepository;
import mx.ipn.upiicsa.web.catalog.mvc.dto.ServicioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/servicios")
public class ServicioController {
    @Autowired
    private ServicioJpaRepository servicioJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("servicioForm", new ServicioForm());
        return "catalog/servicios/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("servicioForm") ServicioForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            return "catalog/servicios/create";
        }
        ServicioJpa s = new ServicioJpa();
        s.setNombre(form.getNombre());
        s.setDescripcion(form.getDescripcion());
        s.setNuDuracion(form.getDuracion());
        s.setStActivo(form.getActivo());
        servicioJpaRepository.save(s);
        return "redirect:/servicios/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<ServicioJpa> servicios = servicioJpaRepository.findAll();
        model.addAttribute("servicios", servicios);
        return "catalog/servicios/list";
    }
}

-- FILE: src/main/resources/templates/catalog/servicios/create.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Crear Servicio</title>
</head>
<body>
<h1>Crear Servicio</h1>
<form th:action="@{/servicios/create}" th:object="${servicioForm}" method="post">
    <div>
        <label>Nombre</label>
        <input type="text" th:field="*{nombre}" />
        <p th:if="${#fields.hasErrors('nombre')}" th:errors="*{nombre}"></p>
    </div>
    <div>
        <label>Descripción</label>
        <textarea th:field="*{descripcion}"></textarea>
    </div>
    <div>
        <label>Duración (min)</label>
        <input type="number" th:field="*{duracion}" />
        <p th:if="${#fields.hasErrors('duracion')}" th:errors="*{duracion}"></p>
    </div>
    <div>
        <label>Activo</label>
        <input type="checkbox" th:field="*{activo}" />
    </div>
    <div>
        <button type="submit">Crear</button>
    </div>
</form>
<p><a th:href="@{/servicios/list}">Volver a la lista</a></p>
</body>
</html>

-- FILE: src/main/resources/templates/catalog/servicios/list.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Servicios</title>
</head>
<body>
<h1>Servicios</h1>
<p><a th:href="@{/servicios/create}">Crear nuevo servicio</a></p>
<table>
    <thead>
        <tr><th>ID</th><th>Nombre</th><th>Duración</th><th>Activo</th></tr>
    </thead>
    <tbody>
        <tr th:each="s : ${servicios}">
            <td th:text="${s.id}"></td>
            <td th:text="${s.txNombre}"></td>
            <td th:text="${s.nuDuracion}"></td>
            <td th:text="${s.stActivo}"></td>
        </tr>
    </tbody>
</table>
</body>
</html>
*/

/* === SUCURSAL_SCAFFOLD ===
-- FILE: src/main/java/mx/ipn/upiicsa/web/resources/mvc/dto/SucursalForm.java
package mx.ipn.upiicsa.web.resources.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SucursalForm {
    private Integer id;
    @NotNull(message = "Seleccione un establecimiento")
    private Integer idEstablecimiento;
    @NotBlank(message = "Favor de proporcionar el nombre de la sucursal")
    private String nombre;
    private String ubicacion; // placeholder for geometry

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdEstablecimiento() { return idEstablecimiento; }
    public void setIdEstablecimiento(Integer idEstablecimiento) { this.idEstablecimiento = idEstablecimiento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/resources/jpa/repository/EstablecimientoJpaRepository.java
package mx.ipn.upiicsa.web.resources.jpa.repository;

import mx.ipn.upiicsa.web.resources.jpa.model.EstablecimientoJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablecimientoJpaRepository extends JpaRepository<EstablecimientoJpa,Integer> {
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/resources/jpa/repository/SucursalJpaRepository.java
package mx.ipn.upiicsa.web.resources.jpa.repository;

import mx.ipn.upiicsa.web.resources.jpa.model.SucursalJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalJpaRepository extends JpaRepository<SucursalJpa,Integer> {
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/resources/mvc/controller/SucursalController.java
package mx.ipn.upiicsa.web.resources.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.resources.jpa.model.EstablecimientoJpa;
import mx.ipn.upiicsa.web.resources.jpa.model.SucursalJpa;
import mx.ipn.upiicsa.web.resources.jpa.repository.EstablecimientoJpaRepository;
import mx.ipn.upiicsa.web.resources.jpa.repository.SucursalJpaRepository;
import mx.ipn.upiicsa.web.resources.mvc.dto.SucursalForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sucursales")
public class SucursalController {
    @Autowired
    private SucursalJpaRepository sucursalJpaRepository;
    @Autowired
    private EstablecimientoJpaRepository establecimientoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("sucursalForm", new SucursalForm());
        List<EstablecimientoJpa> establecimientos = establecimientoJpaRepository.findAll();
        model.addAttribute("establecimientos", establecimientos);
        return "resources/sucursales/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("sucursalForm") SucursalForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("establecimientos", establecimientoJpaRepository.findAll());
            return "resources/sucursales/create";
        }
        SucursalJpa s = new SucursalJpa();
        s.setFkIdEstablecimiento(form.getIdEstablecimiento());
        s.setTxNombre(form.getNombre());
        s.setGmUbicacion(null); // placeholder, store null or implement later
        sucursalJpaRepository.save(s);
        return "redirect:/sucursales/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("sucursales", sucursalJpaRepository.findAll());
        return "resources/sucursales/list";
    }
}

-- FILE: src/main/resources/templates/resources/sucursales/create.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Crear Sucursal</title>
</head>
<body>
<h1>Crear Sucursal</h1>
<form th:action="@{/sucursales/create}" th:object="${sucursalForm}" method="post">
    <div>
        <label>Establecimiento</label>
        <select th:field="*{idEstablecimiento}">
            <option th:each="e : ${establecimientos}" th:value="${e.id}" th:text="${e.txNombre}"></option>
        </select>
        <p th:if="${#fields.hasErrors('idEstablecimiento')}" th:errors="*{idEstablecimiento}"></p>
    </div>
    <div>
        <label>Nombre</label>
        <input type="text" th:field="*{nombre}" />
        <p th:if="${#fields.hasErrors('nombre')}" th:errors="*{nombre}"></p>
    </div>
    <div>
        <label>Ubicación (WKT)</label>
        <input type="text" th:field="*{ubicacion}" placeholder="POINT(lon lat)" />
    </div>
    <div>
        <button type="submit">Crear</button>
    </div>
</form>
<p><a th:href="@{/sucursales/list}">Volver a la lista</a></p>
</body>
</html>

-- FILE: src/main/resources/templates/resources/sucursales/list.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Sucursales</title>
</head>
<body>
<h1>Sucursales</h1>
<p><a th:href="@{/sucursales/create}">Crear nueva sucursal</a></p>
<table>
    <thead>
        <tr><th>ID</th><th>Nombre</th><th>Establecimiento</th></tr>
    </thead>
    <tbody>
        <tr th:each="s : ${sucursales}">
            <td th:text="${s.idSucursal}"></td>
            <td th:text="${s.txNombre}"></td>
            <td th:text="${s.establecimiento != null ? s.establecimiento.txNombre : ''}"></td>
        </tr>
    </tbody>
</table>
</body>
</html>
*/

/* === EMPLEADO_SCAFFOLD ===
-- FILE: src/main/java/mx/ipn/upiicsa/web/resources/mvc/dto/EmpleadoForm.java
package mx.ipn.upiicsa.web.resources.mvc.dto;

import jakarta.validation.constraints.NotNull;

public class EmpleadoForm {
    private Integer idEmpleado; // Will be persona id
    @NotNull(message = "Seleccione la persona")
    private Integer idPersona;
    @NotNull(message = "Seleccione la sucursal")
    private Integer idSucursal;

    // getters/setters
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public Integer getIdPersona() { return idPersona; }
    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }
    public Integer getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Integer idSucursal) { this.idSucursal = idSucursal; }
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/resources/jpa/repository/EmpleadoJpaRepository.java
package mx.ipn.upiicsa.web.resources.jpa.repository;

import mx.ipn.upiicsa.web.resources.jpa.model.EmpleadoJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoJpaRepository extends JpaRepository<EmpleadoJpa,Integer> {
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/resources/mvc/controller/EmpleadoController.java
package mx.ipn.upiicsa.web.resources.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.resources.jpa.model.EmpleadoJpa;
import mx.ipn.upiicsa.web.resources.jpa.model.SucursalJpa;
import mx.ipn.upiicsa.web.resources.jpa.repository.EmpleadoJpaRepository;
import mx.ipn.upiicsa.web.resources.jpa.repository.SucursalJpaRepository;
import mx.ipn.upiicsa.web.resources.mvc.dto.EmpleadoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoJpaRepository empleadoJpaRepository;
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private SucursalJpaRepository sucursalJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("empleadoForm", new EmpleadoForm());
        // personas that are not yet employees (simple approach: list all personas and filter by existence of empleado)
        List<PersonaJpa> personas = personaJpaRepository.findAll();
        // leave filtering to user for now
        List<SucursalJpa> sucursales = sucursalJpaRepository.findAll();
        model.addAttribute("personas", personas);
        model.addAttribute("sucursales", sucursales);
        return "resources/empleados/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("empleadoForm") EmpleadoForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("personas", personaJpaRepository.findAll());
            model.addAttribute("sucursales", sucursalJpaRepository.findAll());
            return "resources/empleados/create";
        }
        EmpleadoJpa e = new EmpleadoJpa();
        e.setIdEmpleado(form.getIdPersona()); // employee id equals persona id
        e.setFkIdSucursal(form.getIdSucursal());
        empleadoJpaRepository.save(e);
        return "redirect:/empleados/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("empleados", empleadoJpaRepository.findAll());
        return "resources/empleados/list";
    }
}

-- FILE: src/main/resources/templates/resources/empleados/create.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Crear Empleado</title>
</head>
<body>
<h1>Crear Empleado</h1>
<form th:action="@{/empleados/create}" th:object="${empleadoForm}" method="post">
    <div>
        <label>Persona</label>
        <select th:field="*{idPersona}">
            <option th:each="p : ${personas}" th:value="${p.id}" th:text="${p.nombre + ' ' + p.primerApellido}"></option>
        </select>
        <p th:if="${#fields.hasErrors('idPersona')}" th:errors="*{idPersona}"></p>
    </div>
    <div>
        <label>Sucursal</label>
        <select th:field="*{idSucursal}">
            <option th:each="s : ${sucursales}" th:value="${s.idSucursal}" th:text="${s.txNombre}"></option>
        </select>
        <p th:if="${#fields.hasErrors('idSucursal')}" th:errors="*{idSucursal}"></p>
    </div>
    <div>
        <button type="submit">Crear</button>
    </div>
</form>
<p><a th:href="@{/empleados/list}">Volver a la lista</a></p>
</body>
</html>

-- FILE: src/main/resources/templates/resources/empleados/list.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Empleados</title>
</head>
<body>
<h1>Empleados</h1>
<p><a th:href="@{/empleados/create}">Crear nuevo empleado</a></p>
<table>
    <thead>
        <tr><th>ID</th><th>Persona</th><th>Sucursal</th></tr>
    </thead>
    <tbody>
        <tr th:each="e : ${empleados}">
            <td th:text="${e.idEmpleado}"></td>
            <td th:text="${e.idEmpleado}"></td>
            <td th:text="${e.fkIdSucursal}"></td>
        </tr>
    </tbody>
</table>
</body>
</html>
*/

/* === CITA_SCAFFOLD ===
-- FILE: src/main/java/mx/ipn/upiicsa/web/appointment/mvc/dto/CitaForm.java
package mx.ipn.upiicsa.web.appointment.mvc.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CitaForm {
    private Integer id;
    @NotNull(message = "Seleccione la persona")
    private Integer idPersona;
    @NotNull(message = "Seleccione el servicio")
    private Integer idServicio;
    @NotNull(message = "Seleccione la lista de precio")
    private Integer idListaPrecio;
    @NotNull(message = "Seleccione la sucursal")
    private Integer idSucursal;
    @NotNull(message = "Seleccione el empleado")
    private Integer idEmpleado;
    private LocalDateTime fechaHora;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdPersona() { return idPersona; }
    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    public Integer getIdListaPrecio() { return idListaPrecio; }
    public void setIdListaPrecio(Integer idListaPrecio) { this.idListaPrecio = idListaPrecio; }
    public Integer getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Integer idSucursal) { this.idSucursal = idSucursal; }
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/appointment/jpa/repository/CitaJpaRepository.java
package mx.ipn.upiicsa.web.appointment.jpa.repository;

import mx.ipn.upiicsa.web.appointment.jpa.model.CitaJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaJpaRepository extends JpaRepository<CitaJpa,Integer> {
}

-- FILE: src/main/java/mx/ipn/upiicsa/web/appointment/mvc/controller/CitaController.java
package mx.ipn.upiicsa.web.appointment.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.appointment.jpa.model.CitaJpa;
import mx.ipn.upiicsa.web.appointment.jpa.repository.CitaJpaRepository;
import mx.ipn.upiicsa.web.catalog.jpa.model.ServicioJpa;
import mx.ipn.upiicsa.web.catalog.jpa.repository.ServicioJpaRepository;
import mx.ipn.upiicsa.web.catalog.jpa.repository.ServicioListaPrecioJpaRepository;
import mx.ipn.upiicsa.web.catalog.jpa.model.ServicioListaPrecioJpa;
import mx.ipn.upiicsa.web.catalog.jpa.repository.ListaPrecioJpaRepository;
import mx.ipn.upiicsa.web.resources.jpa.repository.SucursalJpaRepository;
import mx.ipn.upiicsa.web.resources.jpa.repository.EmpleadoJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.appointment.mvc.dto.CitaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private CitaJpaRepository citaJpaRepository;
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private ServicioJpaRepository servicioJpaRepository;
    @Autowired
    private ServicioListaPrecioJpaRepository servicioListaPrecioJpaRepository;
    @Autowired
    private ListaPrecioJpaRepository listaPrecioJpaRepository;
    @Autowired
    private SucursalJpaRepository sucursalJpaRepository;
    @Autowired
    private EmpleadoJpaRepository empleadoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("citaForm", new CitaForm());
        model.addAttribute("personas", personaJpaRepository.findAll());
        model.addAttribute("servicios", servicioJpaRepository.findAll());
        model.addAttribute("sucursales", sucursalJpaRepository.findAll());
        model.addAttribute("empleados", empleadoJpaRepository.findAll());
        // initially no lista precios loaded
        model.addAttribute("listas", List.of());
        return "appointment/citas/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("citaForm") CitaForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("personas", personaJpaRepository.findAll());
            model.addAttribute("servicios", servicioJpaRepository.findAll());
            model.addAttribute("sucursales", sucursalJpaRepository.findAll());
            model.addAttribute("empleados", empleadoJpaRepository.findAll());
            model.addAttribute("listas", servicioListaPrecioJpaRepository.findByFkIdServicio(form.getIdServicio()).stream().map(sp -> listaPrecioJpaRepository.findById(sp.getFkIdListaPrecio()).orElse(null)).filter(lp -> lp!=null).collect(Collectors.toList()));
            return "appointment/citas/create";
        }
        CitaJpa c = new CitaJpa();
        c.setFkIdPersona(form.getIdPersona());
        c.setFkIdServicio(form.getIdServicio());
        c.setFkIdListaPrecio(form.getIdListaPrecio());
        c.setFkIdSucursal(form.getIdSucursal());
        c.setFkIdEmpleado(form.getIdEmpleado());
        citaJpaRepository.save(c);
        return "redirect:/citas/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("citas", citaJpaRepository.findAll());
        return "appointment/citas/list";
    }

    // Endpoint to fetch active listas filtered by servicio (used by AJAX from the form)
    @GetMapping(path = "/listas-by-servicio", produces = "application/json")
    @ResponseBody
    public List<?> listasByServicio(@RequestParam Integer servicioId){
        // find servicio-lista entries for this servicio
        List<ServicioListaPrecioJpa> sps = servicioListaPrecioJpaRepository.findByFkIdServicio(servicioId);
        // for each, load listaPrecio and filter by estado activo (we assume estado id for active is known or Estado has 'st_activo')
        return sps.stream().map(sp -> listaPrecioJpaRepository.findById(sp.getFkIdListaPrecio()).orElse(null)).filter(lp -> lp != null && Boolean.TRUE.equals(lp.getStActivo())).collect(Collectors.toList());
    }
}

-- FILE: src/main/resources/templates/appointment/citas/create.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Crear Cita</title>
    <script th:src="@{/webjars/jquery/3.6.0/jquery.min.js}"></script>
</head>
<body>
<h1>Crear Cita</h1>
<form th:action="@{/citas/create}" th:object="${citaForm}" method="post">
    <div>
        <label>Persona</label>
        <select th:field="*{idPersona}">
            <option th:each="p : ${personas}" th:value="${p.id}" th:text="${p.nombre + ' ' + p.primerApellido}"></option>
        </select>
    </div>
    <div>
        <label>Servicio</label>
        <select th:field="*{idServicio}" id="servicio-select">
            <option th:each="s : ${servicios}" th:value="${s.idServicio}" th:text="${s.txNombre}"></option>
        </select>
    </div>
    <div>
        <label>Lista de Precio</label>
        <select th:field="*{idListaPrecio}" id="lista-select">
            <option th:each="l : ${listas}" th:value="${l.idListaPrecio}" th:text="${l.txNombre}"></option>
        </select>
    </div>
    <div>
        <label>Sucursal</label>
        <select th:field="*{idSucursal}">
            <option th:each="s : ${sucursales}" th:value="${s.idSucursal}" th:text="${s.txNombre}"></option>
        </select>
    </div>
    <div>
        <label>Empleado</label>
        <select th:field="*{idEmpleado}">
            <option th:each="e : ${empleados}" th:value="${e.idEmpleado}" th:text="${e.idEmpleado}"></option>
        </select>
    </div>
    <div>
        <button type="submit">Crear</button>
    </div>
</form>

<script>
    // when servicio changes, fetch listas via AJAX
    document.getElementById('servicio-select').addEventListener('change', function(){
        var servicioId = this.value;
        fetch('/citas/listas-by-servicio?servicioId=' + servicioId)
            .then(res => res.json())
            .then(data => {
                var select = document.getElementById('lista-select');
                select.innerHTML = '';
                data.forEach(function(item){
                    var opt = document.createElement('option');
                    opt.value = item.idListaPrecio || item.id;
                    opt.text = item.txNombre || item.nombre;
                    select.appendChild(opt);
                });
            });
    });
</script>

<p><a th:href="@{/citas/list}">Volver a la lista</a></p>
</body>
</html>

-- FILE: src/main/resources/templates/appointment/citas/list.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Citas</title>
</head>
<body>
<h1>Citas</h1>
<p><a th:href="@{/citas/create}">Crear nueva cita</a></p>
<table>
    <thead>
        <tr><th>ID</th><th>Persona</th><th>Servicio</th><th>ListaPrecio</th><th>Sucursal</th><th>Empleado</th></tr>
    </thead>
    <tbody>
        <tr th:each="c : ${citas}">
            <td th:text="${c.idCita}"></td>
            <td th:text="${c.fkIdPersona}"></td>
            <td th:text="${c.fkIdServicio}"></td>
            <td th:text="${c.fkIdListaPrecio}"></td>
            <td th:text="${c.fkIdSucursal}"></td>
            <td th:text="${c.fkIdEmpleado}"></td>
        </tr>
    </tbody>
</table>
</body>
</html>
*/
