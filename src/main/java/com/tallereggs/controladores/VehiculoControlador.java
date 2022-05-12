
package com.tallereggs.controladores;

import com.tallereggs.entidades.Vehiculo;
import com.tallereggs.enums.EnumEstado;
import com.tallereggs.servicios.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PostMapping("/registrarVehiculo")
    public String registrarVehiculo(ModelMap model, @RequestParam String patente, @RequestParam String modelo, @RequestParam String marca, @RequestParam String anio, @RequestParam String km, @RequestParam String idUsuario, @RequestParam MultipartFile archivo, @RequestParam EnumEstado estado) {
        try {
            vehiculoServicio.crear(patente, modelo, marca, anio, km, idUsuario, archivo, EnumEstado.EN_ESPERA);
            
        } catch (Exception ex) {

            model.put("error", ex.getMessage());
            
            model.put("patente", patente);
            model.put("String", modelo);
            model.put("String", marca);
            model.put("anio", anio);
            model.put("km", km);
            model.put("idUsuario", idUsuario);
            model.put("archivo", archivo);
            model.put("estado", estado); //estado no se si iría en este método
            
            
            return "registro.html"; 
        }

        return "index.html";

    }
    
    @PostMapping("/editarVehiculo")
    public String editarVehiculo(RedirectAttributes attr, @RequestParam() String id, @RequestParam String patente, @RequestParam String modelo, @RequestParam String marca, @RequestParam String anio, @RequestParam String km, @RequestParam String idUsuario, @RequestParam MultipartFile archivo, @RequestParam EnumEstado estado) {

        try {
            vehiculoServicio.actualizar(id, patente, modelo, marca, anio,km, idUsuario, archivo, estado);
            attr.addFlashAttribute("actualizado", "El vehículo se actualizó correctamente");

        } catch (Exception ex) {
            attr.addFlashAttribute("errorActualizado", ex.getMessage());
        }

        return "redirect:/vehiculos";
    }
    
    @GetMapping("/vehiculos")
    public String vehiculos(ModelMap model, RedirectAttributes attr,@RequestParam(required = false) String search) {
        List<Vehiculo> vehiculos;

        vehiculos = (List<Vehiculo>) vehiculoServicio.buscarPorPatente(search);
        
        return "vehiculos.html"; 
        
    }
    
}
