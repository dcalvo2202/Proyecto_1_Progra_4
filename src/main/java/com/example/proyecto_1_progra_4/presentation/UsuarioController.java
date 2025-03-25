package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Service;
import com.example.proyecto_1_progra_4.logic.Usuario;
import org.springframework.http.ResponseEntity;
import com.example.proyecto_1_progra_4.data.UsuarioRepository;
import com.example.proyecto_1_progra_4.logic.Usuario;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; // Para el futuro HTML
    }

    @PostMapping("/registro")
    public String registrarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "registro";
        }

        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuarioRepository.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = "";

        if (principal instanceof UserDetails) {
            login = ((UserDetails) principal).getUsername(); // En tu caso el ID puede actuar como "username"
        }

        if (session.getAttribute("login") == null) {
            session.setAttribute("login", login);
        }

        model.addAttribute("login", login);
        return "home"; // Página inicial
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
