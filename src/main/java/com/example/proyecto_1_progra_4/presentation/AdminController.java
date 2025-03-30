package com.example.proyecto_1_progra_4.presentation;
import com.example.proyecto_1_progra_4.logic.Medico;
import com.example.proyecto_1_progra_4.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

@Autowired
    private Service service;

@GetMapping("/medicos/{id}/aprobar")
    public String aprobarMedico(@PathVariable Integer id,
                                RedirectAttributes redirectAttributes){
    Optional<Medico> medicoAprobado = service.aprobarMedico(id);
    if(medicoAprobado.isPresent()){
        redirectAttributes.addFlashAttribute("mensaje", "Medico aprobado");
    } else{
        redirectAttributes.addFlashAttribute("error", "No se pudo aprobar el medico");
    }
    return "redirect:/admin/medicos/pendientes";
}
@PostMapping ("/medicos/{id}/rechazar")
    public String rechazarMedico(@PathVariable Integer id,
                                 RedirectAttributes redirectAttributes){
    Optional<Medico> medicoRechazado=service.rechazarMedico(id);
    if (medicoRechazado.isPresent()){
        redirectAttributes.addFlashAttribute("mensaje", "Medico rechazado correctamente");
    } else{
        redirectAttributes.addFlashAttribute("error", "No se pudo rechazar al medico");
    }
    return "redirect:/admin/medicos/pendientes";
}
}
