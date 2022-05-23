
package com.tallereggs.controladores;

import com.tallereggs.entidades.Presupuesto;
import com.tallereggs.entidades.PresupuestoDetalle;
import com.tallereggs.errores.ErrorServicio;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/presupuesto")
public class PresupuestoControlador {
    
    @Autowired
    private PresupuestoServicio presupuestoServicio;
    
    
    @GetMapping
    public String menuPresupuesto(){
        
        return "presupuesto.html";
    }
    
//Método para visualizar los detalles del presupuesto
    
    
    @GetMapping("/form")
    public String form(ModelMap modelo){
        
        List<PresupuestoDetalle> detallesPresupuesto = presupuestoServicio.listarDetalles();
        modelo.put("detallesPresupuesto", detallesPresupuesto);
        
      
        return "presupuesto.html";
        
    }
    
    //Método para crear los presupuestos
    
    @PostMapping("/form")
    public String crear(RedirectAttributes attr, @RequestParam String idVehiculo, String idUsuario, @RequestParam String fallaDescripcion,Float total){
        
        try {
            presupuestoServicio.agregar(idVehiculo, idUsuario, fallaDescripcion, total);
            attr.addFlashAttribute("exito", "El presupuesto se agregó exitosamente.");
        } catch (ErrorServicio ex) {
           attr.addFlashAttribute("error", ex.getMessage());
        }
        
        return "redirect:/presupuesto";
    }
    
    //Método para listar todos los presupuestos
    
    @GetMapping("/listarPresupuestos")
    public String listar(ModelMap modelo){
        List<Presupuesto> presupuestos = presupuestoServicio.listarTodos();
        modelo.put("presupuestos", presupuestos);
        
        return "listarPresupuestos.html";
    }
    
    //Método para modificar presupuesto
    
    @GetMapping("/modificar/{id}")
    public String editar(ModelMap modelo, @PathVariable String id){
        
        try {
            List<PresupuestoDetalle> detallesPresupuesto = presupuestoServicio.listarDetalles();
            modelo.put("detallesPresupuesto", detallesPresupuesto);
            Presupuesto presupuesto = presupuestoServicio.buscarPorId(id);
            modelo.put("presupuesto", presupuesto);
        } catch (ErrorServicio ex) {
          
        }
        
        return "editarPresupuesto.html";
    }
    
    //Método para modificar en base de datos un presupuesto
    @PostMapping("/modificar")
    public String modificar(RedirectAttributes attr,@RequestParam String id, @RequestParam String idVehiculo, @RequestParam String idUsuario, @RequestParam String fallaDescripcion, @RequestParam Float precio){
        
        try {
            presupuestoServicio.modificar(idUsuario, idVehiculo, idUsuario, fallaDescripcion, precio);
            attr.addFlashAttribute("exito", "Se modificó el presupuesto correctamente.");
        } catch (ErrorServicio ex) {
           attr.addFlashAttribute("error", ex.getMessage());
        }
        
        return "redirect:/presupuesto/lista";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(RedirectAttributes attr, @PathVariable String id){
        
        try {
            presupuestoServicio.eliminar(id);
            attr.addFlashAttribute("éxito", "El presupuesto fue eliminado exitosamente.");
            
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }
            
            return "redirect:presupuesto/lista";
    }
    
        
}