package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.data.UsuarioRepository;
import com.example.proyecto_1_progra_4.logic.Medico;
import com.example.proyecto_1_progra_4.logic.Perfile;
import com.example.proyecto_1_progra_4.service.Service;
import com.example.proyecto_1_progra_4.logic.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private Service service;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro-medico")
    public String mostrarFormularioRegistroMedico(Model model) {
        model.addAttribute("medico", new Medico());
        return "registro-medico";
    }

    @PostMapping("/registro-medico")
    public String registrarMedico(@ModelAttribute("medico") Medico medico,
                                  @RequestParam String clave2,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) return "registro-medico";

        Usuario usuario = medico.getUsuario();
        if (!usuario.getClave().equals(clave2)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "registro-medico";
        }

        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setPerfil(new Perfile()); // Asume que el perfil médico tiene ID 2
        usuario.getPerfil().setId(2);
        service.registrarMedico(usuario, medico);
        return "redirect:/login";
    }

    @GetMapping("/registro-paciente")
    public String mostrarFormularioRegistroPaciente(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro-paciente";
    }

    @PostMapping("/registro-paciente")
    public String registrarPaciente(@ModelAttribute("usuario") Usuario usuario,
                                    @RequestParam String clave2,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) return "registro-paciente";

        if (!usuario.getClave().equals(clave2)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "registro-paciente";
        }

        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setPerfil(new Perfile());
        usuario.getPerfil().setId(3); // Asume que 3 = paciente
        usuario.setEstado("APROBADO"); // Paciente entra aprobado directamente
        service.guardarUsuario(usuario);
        return "redirect:/login";
    }
}
