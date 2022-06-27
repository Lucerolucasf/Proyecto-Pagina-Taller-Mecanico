
package com.tallereggs.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortalControlador {
    
    @GetMapping("/")
    public String index(ModelMap modelo, @RequestParam(required = false) String error, @RequestParam(required = false) String logout){
        
        if(error != null) {
            modelo.put("error", "Usuario o clave incorrectos.");
        }
        
        if(logout != null) {
            modelo.put("logout", "La sesión se cerró correctamente.");
        }
        
        return "index.html";
    }
    
    
    
    
}
