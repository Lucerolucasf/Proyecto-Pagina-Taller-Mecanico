package com.tallereggs.controladores;

import com.tallereggs.entidades.Usuario;
import com.tallereggs.entidades.Vehiculo;
import com.tallereggs.enums.EnumEstado;
import com.tallereggs.servicios.UsuarioServicio;
import com.tallereggs.servicios.VehiculoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class VehiculoControlador {

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
 
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') || hasAnyRole('ROLE_PERSONAL')")
    @PostMapping("/registrarVehiculo")
    public String registrarVehiculo(ModelMap model, RedirectAttributes attr, @RequestParam String patente, @RequestParam String modelo, @RequestParam String marca, @RequestParam String anio, @RequestParam String km, @RequestParam String idUsuario, @RequestParam MultipartFile archivo) {
        try {
            vehiculoServicio.crear(patente, modelo, marca, anio, km, idUsuario, archivo, EnumEstado.EN_ESPERA);
            attr.addFlashAttribute("Exito", "El patente '" + patente + "' se cargó exitosamente.");
        } catch (Exception ex) {

            attr.addFlashAttribute("Error", ex.getMessage());

            attr.addFlashAttribute("patente", patente);
            attr.addFlashAttribute("String", modelo);
            attr.addFlashAttribute("String", marca);
            attr.addFlashAttribute("anio", anio);
            attr.addFlashAttribute("km", km);
            attr.addFlashAttribute("idUsuario", idUsuario);
            attr.addFlashAttribute("archivo", archivo);

        }
       
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        attr.addFlashAttribute("usuarios", usuarios);

        return "redirect:/inicioPersonal";

    }

      @PreAuthorize("hasAnyRole('ROLE_ADMIN') || hasAnyRole('ROLE_PERSONAL')")
       @PostMapping("/editarVehiculo")
    public String editarVehiculo(RedirectAttributes attr, @RequestParam() String id, @RequestParam String patente, @RequestParam String modelo, @RequestParam String marca, @RequestParam String anio, @RequestParam String km, @RequestParam String idUsuario, @RequestParam MultipartFile archivo, @RequestParam EnumEstado estado) {

        try {
            vehiculoServicio.actualizar(id, patente, modelo, marca, anio, km, idUsuario, archivo, estado);
            attr.addFlashAttribute("actualizado", "El vehículo se actualizó correctamente");

        } catch (Exception ex) {
            attr.addFlashAttribute("errorActualizado", ex.getMessage());
        }

        return "redirect:/vehiculos";
    }

    @GetMapping("/vehiculos")
    public String vehiculos(ModelMap model, RedirectAttributes attr, @RequestParam(required = false) String search) {
        List<Vehiculo> vehiculos;

        vehiculos = (List<Vehiculo>) vehiculoServicio.buscarPorPatente(search);

        return "vehiculos.html";

    }

    
 ////modificacion del front////
   

    @PostMapping("/buscadorAuto")
    public String buscador(ModelMap modelo, RedirectAttributes attr, @RequestParam(required = false) String searchAuto) {

        if (searchAuto != null) {
            attr.addAttribute("searchAuto", searchAuto);
        }
        
        return "redirect:/searchAuto";
    }

    @GetMapping("/searchAuto")
    public String searchAuto(ModelMap modelo, RedirectAttributes attr, @RequestParam(required = false) String searchAuto) {
        List<Vehiculo> vehiculos;
        vehiculos = vehiculoServicio.buscarPorPatente(searchAuto);

        if (vehiculos == null || vehiculos.isEmpty()) {
            modelo.put("vacio", searchAuto + " no se encontro en la base de datos");
            return "inicioPersonal.html";
        }
        modelo.put("searchAuto", searchAuto);
        modelo.put("vehiculos", vehiculos);

        return "search.html";
    }


}