/*
VICTOR PRUEBA
 */
package com.tallereggs.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioCrontrolador {

    @GetMapping("/search")
    public String search(ModelMap modelo) {

        return "search.html";
    }

}
