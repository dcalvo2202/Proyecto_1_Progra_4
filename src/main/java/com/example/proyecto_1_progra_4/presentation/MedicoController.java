package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Medico;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.example.proyecto_1_progra_4.data.UsuarioRepository;
import com.example.proyecto_1_progra_4.logic.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Optional;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private Service service;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            Usuario user = usuarioOptional.get();
            model.addAttribute("medico", user);
            return "medico/perfil"; // Nombre de la plantilla Thymeleaf (ej: medico_perfil.html)
        }
        return "redirect:/home"; //Si no existe te manda al home

    }

    @PostMapping("/perfil")
    public String guardarPerfil(@Valid @ModelAttribute Medico medico,
                                BindingResult result,
                                Model model,
                                HttpServletRequest request,
                                @RequestParam("file") MultipartFile foto) { //Para poder subir la foto

        if (result.hasErrors()) {
            model.addAttribute("errores", result.getAllErrors()); // Agregar errores al modelo para mostrarlos en la vista
            return "medico/perfil";
        }

        if (!foto.isEmpty()) {
            // Guardar la foto en el disco
            Path directorioImagenes = Paths.get("src//main//resources//static//images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = foto.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + foto.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                medico.setRutaFoto(foto.getOriginalFilename());
            } catch (IOException e) {
                // Manejar la excepción
            }
        }

        service.guardarMedico(medico);
        return "redirect:/medicos/perfil"; // Redirige de nuevo al perfil
    }

    @GetMapping("/{id}/primera-vez")
    public ResponseEntity<Boolean> esPrimeraVez(@PathVariable Integer id) {
        boolean primeraVez = service.esPrimeraVezMedico(id);
        return ResponseEntity.ok(primeraVez);
    }

}
