package com.tallereggs.controladores;

import com.tallereggs.entidades.Usuario;
import com.tallereggs.enums.EnumROL;
import com.tallereggs.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuario/form")
    public String form() {
        return "inicioPersonal.html";
    }

    @PostMapping("/usuario/form")
    public String crearUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String celular, @RequestParam String direccion, @RequestParam String username, String password) throws Exception {

        try {
            usuarioServicio.crear(nombre, apellido, celular, direccion, username, password, EnumROL.CLIENTE);
            modelo.put("Exito", "El usuario '" + username + "' se cargó exitosamente.");
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }

        return "inicioPersonal.html";
    }

    @GetMapping("/usuario/lista")
    public String listaUsuarios(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.put("Usuarios", usuarios);

        return "usuario-lista.html";
    }

    @GetMapping("/usuario/editarUsuario")
    public String editarUsuario() {
        return "usuario-editarUsuario.html";

    }

    @PostMapping("/usuario/editarUsuario")
    public String editarUsuario(ModelMap modelo, @RequestParam String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String celular, @RequestParam String direccion, @RequestParam String username, String password) throws Exception {

        try {
            usuarioServicio.modificarUsuario(id, nombre, apellido, celular, direccion, username, password);
            modelo.put("Actualizar", "El usuario '" + username + "' se actualizó exitosamente.");
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }

        return "usuario-editar.html";
    }

    @GetMapping("/usuario/bajaUsuario")
    public String bajaUsuario() {
        return "usuario-bajaUsuario.html";

    }

    @PostMapping("/usuario/bajaUsuario")
    public String darBajaUsuario(ModelMap modelo, @RequestParam String id) throws Exception {

        try {
            usuarioServicio.darBajaUsuario(id);
            modelo.put("Baja", "El usuario fue dado de baja exitosamente.");
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }

        return "usuario-baja.html";
    }

    @GetMapping("/usuario/altaUsuario")
    public String altaUsuario() {
        return "usuario-altaUsuario.html";

    }

    @PostMapping("/usuario/altaUsuario")
    public String darAltaUsuario(ModelMap modelo, @RequestParam String id) throws Exception {

        try {
            usuarioServicio.darAltaUsuario(id);
            modelo.put("Alta", "El usuario fue dado de alta exitosamente.");
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }

        return "usuario-alta.html";
    }

    @GetMapping("/usuario/personalAlta")
    public String personalAlta() {
        return "usuario-personalAlta.html";

    }

    @PostMapping("/usuario/personalAlta")
    public String personalAlta(ModelMap modelo, @RequestParam String id) throws Exception {

        try {
            usuarioServicio.personalAlta(id);
            modelo.put("AltaPersonal", "El usuario fue dado de alta como personal exitosamente.");
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }

        return "usuario-personalAlta.html";
    }

    ////modificacion del front////
    @GetMapping("/inicioPersonal")
    public String inicioPersonal() {

        return "inicioPersonal.html";
    }

    @PostMapping("/buscador")
    public String buscador(ModelMap modelo, RedirectAttributes attr, @RequestParam(required = false) String searchAuto, @RequestParam(required = false) String searchCliente) {

        if (searchAuto != null) {
            attr.addFlashAttribute("searchAuto", searchAuto);
        }
        if (searchCliente != null) {
            attr.addFlashAttribute("searchCliente", searchCliente);
        }
        System.out.println(searchAuto);
        System.out.println(searchCliente);
        
        return "redirect:/search";
    }

    @GetMapping("/search")
    public String search() {

        return "search.html";
    }

}
