package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.LoginRequest;
import com.example.proyecto_1_progra_4.service.Service;
import com.example.proyecto_1_progra_4.logic.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private final Service service;

    public LoginController(Service service) {
        this.service = service;
    }

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute LoginRequest loginRequest, HttpServletRequest request) {
        Optional<Usuario> usuarioOptional = service.login(loginRequest.getId(), loginRequest.getClave());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            HttpSession session = request.getSession();
            session.setAttribute("usuarioId", usuario.getId()); // Guarda el ID del usuario en la sesión
            session.setAttribute("perfil", usuario.getPerfil().getNombre());

            // Redirige al usuario a la página correspondiente según su rol
            if (usuario.getPerfil().getNombre().equals("ADMIN")) {
                return new RedirectView("/admin/medicos/pendientes");
            } else if (usuario.getPerfil().getNombre().equals("MEDICO")) {
                return new RedirectView("/home");
            } else {
                return new RedirectView("/home");
            }
        } else {
            // Redirige de nuevo a la página de inicio de sesión si las credenciales son incorrectas
            return new RedirectView("/login?error=true");
        }
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }
}

