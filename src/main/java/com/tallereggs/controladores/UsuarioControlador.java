package com.tallereggs.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioControlador {

    @GetMapping("/usuario/form")
    public String form() {
        return "usuario-form.html";

    }
    
    @PostMapping("/usuario/form")
    public String crearUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String celular, @RequestParam String direccion, @RequestParam String username, @RequestParam String password){
        
        
        
        
        return "usuario-form.html";
    }
    
    

}
