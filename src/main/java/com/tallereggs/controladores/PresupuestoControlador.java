
package com.tallereggs.controladores;

import com.tallereggs.entidades.Presupuesto;
import com.tallereggs.entidades.PresupuestoDetalle;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.servicios.PresupuestoDetalleServicio;
import com.tallereggs.servicios.PresupuestoServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PresupuestoControlador {
    
    @Autowired
    private PresupuestoServicio presupuestoServicio;
    
    //Método para visualizar los detalles del presupuesto
    @GetMapping("/presupuesto/form")//A definir con frontend
    public String form(ModelMap modelo){
        
        List<PresupuestoDetalle> detallesPresupuesto = presupuestoServicio.listarDetalles();
        modelo.put("detallesPresupuesto", detallesPresupuesto);
        
        //Definir dirección de HTML para mostrar con frontend
        return "#";
        
    }
    //Método para crear los presupuestos
    @PostMapping("/presupuesto/form")//A definir con frontend
    public String crear(RedirectAttributes attr, @RequestParam String idVehiculo, @RequestParam String idUsuario, @RequestParam String fallaDescripcion, @RequestParam Float total){
        
        try {
            presupuestoServicio.agregar(idVehiculo, idUsuario, fallaDescripcion, total);
            attr.addFlashAttribute("exito", "El presupuesto se agregó exitosamente.");
        } catch (ErrorServicio ex) {
           attr.addFlashAttribute("error", ex.getMessage());
        }
        //Definir dirección de HTML para mostrar con frontend
        return "#";
    }
    
    //Método para listar todos los presupuestos
    @GetMapping("/presupuesto/lista")//A definir con frontend
    public String listar(ModelMap modelo){
        List<Presupuesto> presupuestos = presupuestoServicio.listarTodos();
        modelo.put("presupuestos", presupuestos);
        //Definir dirección de HTML para mostrar con frontend
        return "#";
    }
    
    //Método para modificar presupuesto
    @GetMapping("/presupuesto/modificar/{id}")//A definir con frontend
    public String editar(RedirectAttributes attr, @PathVariable String id){
        
        try {
            List<PresupuestoDetalle> detallesPresupuesto = presupuestoServicio.listarDetalles();
            attr.addFlashAttribute("detallesPresupuesto", detallesPresupuesto);
            Presupuesto presupuesto = presupuestoServicio.buscarPorId(id);
            attr.addFlashAttribute("presupuesto", presupuesto);
        } catch (ErrorServicio ex) {
          
        }
        //Definir dirección de HTML para ir al vista de modificación
        return "#";
    }
    
    //Método para modificar en base de datos un presupuesto
    @PostMapping("/presupuesto/modificar")//A definir con frontend
    public String modificar(ModelMap modelo,@RequestParam String id, @RequestParam String idVehiculo, @RequestParam String idUsuario, @RequestParam String fallaDescripcion, @RequestParam Float precio){
        
        try {
            presupuestoServicio.modificar(idUsuario, idVehiculo, idUsuario, fallaDescripcion, precio);
            modelo.put("exito", "Se modificó el presupuesto correctamente.");
        } catch (ErrorServicio ex) {
           modelo.put("error", ex.getMessage());
        }
        //Definir dirección de HTML para mostrar lista de prespuestos con los cambios realizados
        return "#";
    }
    
    @GetMapping("/presupuesto/eliminar/{id}")//A definir con frontend
    public String eliminar(RedirectAttributes attr, @PathVariable String id){
        
        try {
            presupuestoServicio.eliminar(id);
            attr.addFlashAttribute("éxito", "El presupuesto fue eliminado exitosamente.");
            
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }
            //Definir dirección HTML a donde redireccionar cuando se elimine un presupuesto
            return "#";
    }
    
    
    
}
