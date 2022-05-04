
package com.tallereggs.controladores;

import com.tallereggs.entidades.PresupuestoDetalle;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.servicios.PresupuestoDetalleServicio;
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
@RequestMapping("/presupuestoDetalle")//Definir con el frontend
 class PresupuestoDetalleControlador {
    
    @Autowired
    private PresupuestoDetalleServicio presupuestoDetalleServicio;
    
    
    @GetMapping("/form")//Definir con el frontend
    public String form(){
        
        return "#"; //Definir con frontend
    }
    
    @PostMapping("/form")//Definir con el frontend
    public String crear(RedirectAttributes attr, @RequestParam String detalle, @RequestParam Integer cantidad, @RequestParam Float precio, @RequestParam String idPresupuesto){
        
        try {
            presupuestoDetalleServicio.agregar(detalle, cantidad, precio, idPresupuesto);
            attr.addFlashAttribute("exito", "El detalle se agregó correctamente.");
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }
        
        return "#"; //Definir con el frontend
        
    }
    
    @GetMapping("/lista/{id}")//Definir con el frontend
    public String listaPorIdPresupuesto(ModelMap modelo,@PathVariable String idPresupuesto){
        List<PresupuestoDetalle> presupuestosDetalle =  presupuestoDetalleServicio.listaDeDetallesPorPresupuestoId(idPresupuesto);
        modelo.put("presupuestosDetalle", presupuestosDetalle);
        return "#";//Definir con frontend
    }
    
    @GetMapping("/eliminar/{id}")//Definir con el frontend
    public String eliminar(RedirectAttributes attr, @PathVariable String id){
        presupuestoDetalleServicio.eliminar(id);
        attr.addFlashAttribute("exito", "Se eliminó el detalle correctamente.");
        
        return "#";//Definir con forntend
    }
    
    @GetMapping("/modificar/{id}")
    public String editar(ModelMap modelo, @PathVariable String id){
        
        try {
            PresupuestoDetalle presupuestoDetalle = presupuestoDetalleServicio.buscarPorId(id);
            modelo.put("presupuestoDetalle", presupuestoDetalle);
        } catch (ErrorServicio ex) {
          
        }
        return "#";//Definir con frontend
    }
    
    @PostMapping("/modificar")
    public String modificar(RedirectAttributes attr, @RequestParam String id, @RequestParam String detalle, @RequestParam Integer cantidad, @RequestParam Float precio, @RequestParam String idPresupuesto){
        
        try {
            presupuestoDetalleServicio.modificar(id, detalle, cantidad, precio, idPresupuesto);
            attr.addFlashAttribute("exito", "El detalles se modifió correctamente.");
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }
        return "#";//Definir con frontend
    }
    
    
    
    
    
    
}
