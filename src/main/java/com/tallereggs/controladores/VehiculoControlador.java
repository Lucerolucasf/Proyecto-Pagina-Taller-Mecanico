
package com.tallereggs.controladores;

import com.tallereggs.enums.EnumEstado;
import com.tallereggs.servicios.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
@RequestMapping("/")
public class VehiculoControlador {
    
    @Autowired
    private VehiculoServicio vehiculoServicio; 
    
    @PostMapping("/crearVehiculo")
    public String crearVehiculo(ModelMap modelo, @RequestParam String id, @RequestParam String patente, @RequestParam String modelo, @RequestParam String marca, @RequestParam String anio, @RequestParam String km, @RequestParam String idUsuario, @RequestParam MultipartFile archivo, @RequestParam EnumEstado estado) {
        try {
            vehiculoServicio.crear(id, patente, modelo, marca, anio, km, idUsuario, archivo, EnumEstado.EN_ESPERA);
            modelo.put("Su vehículo se cargó correctamente");
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
//            modelo.put("isbn", isbn);
//            modelo.put("titulo", titulo);
//            modelo.put("anio", anio);
//            modelo.put("ejemplares", ejemplares);

            return "index.html";
        }

        return "index.html";

    }
    
    // public Vehiculo crear(String id, String patente, String modelo, String marca, String anio, String km, String idUsuario, MultipartFile archivo, EnumEstado estado) throws Exception {
//        validar(patente, modelo, marca, anio, km, idUsuario, archivo, estado);
//        Vehiculo vehiculo = new Vehiculo();
//
//        vehiculo.setId(id);
//        vehiculo.setPatente(patente);
//        vehiculo.setModelo(modelo);
//        vehiculo.setMarca(marca);
//        vehiculo.setAnio(anio);
//        vehiculo.setKm(km);
//        vehiculo.setEstado(estado);
//        vehiculo.setUsuario(usuarioServicio.buscarPorId(idUsuario));
//        
//        TarjetaVerde tv = tarjetaVerdeServicio.guardar(archivo);
//        vehiculo.setTv(tv);
//        
//        vehiculoRepositorio.save(vehiculo);
//        return null;
//
//    }
    
}
