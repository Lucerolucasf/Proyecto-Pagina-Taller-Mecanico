package com.tallereggs.controladores;

import com.tallereggs.entidades.Presupuesto;
import com.tallereggs.entidades.PresupuestoDetalle;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.servicios.PresupuestoDetalleServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/presupuestoDetalle")
class PresupuestoDetalleControlador {

    @Autowired
    private PresupuestoDetalleServicio presupuestoDetalleServicio;

    //Método para enviar a vista para agregar datos
    @GetMapping("/form")
    public String form() {

        return "AdministrarPresupuestoDetalle.html";
    }

    //Método para crear detalles de presupuesto
      @PreAuthorize("hasAnyRole('ROLE_ADMIN') || hasAnyRole('ROLE_PERSONAL')")
    @PostMapping("/form")
    public String crear(RedirectAttributes attr, @RequestParam String detalle, @RequestParam Integer cantidad, @RequestParam Float precio, @RequestParam String idPresupuesto) {

        try {
            presupuestoDetalleServicio.agregar(detalle, cantidad, precio, idPresupuesto);
            attr.addFlashAttribute("exito", "El detalle se agregó correctamente.");

        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/presupuesto/form/" + idPresupuesto;

    }

    //Método para listar los detalles concretos de un Id presupuesto
    /**
     * Para este autocompletado colocar "/** + ENTER"
     *
     * @param modelo
     * @param idPresupuesto
     * @return
     */
//    @GetMapping("/presupuesto/form/lista/{idPresupuesto}")//Este Id es del presupuesto que se quiere listar sus detalles
//    public String listaPorIdPresupuesto(RedirectAttributes attr, @PathVariable String idPresupuesto) {
//        List<PresupuestoDetalle> presupuestoDetalles = presupuestoDetalleServicio.listaDeDetallesPorPresupuestoId(idPresupuesto);
//        attr.addFlashAttribute("presupuestosDetalle", presupuestoDetalles);
//
//        Float total = presupuestoDetalleServicio.sumarPrecios(idPresupuesto);
//        attr.addFlashAttribute("total", total);
//
//        return "redirect:/presupuesto/form/lista/" + idPresupuesto;
//    }
    //Método para eliminar detalles de presupuesto por Id de DetallePresupuesto
      @PreAuthorize("hasAnyRole('ROLE_ADMIN') || hasAnyRole('ROLE_PERSONAL')")
    @GetMapping("/eliminar/{id}")
    public String eliminar(RedirectAttributes attr, @PathVariable String id) {
        PresupuestoDetalle presupuestoDetalle = null;
        Presupuesto presupuesto = null;

        try {
            presupuestoDetalle = presupuestoDetalleServicio.buscarPorId(id);
            presupuesto = presupuestoDetalle.getPresupuesto();
            presupuestoDetalleServicio.eliminar(id);
            attr.addFlashAttribute("exito", "Se eliminó el detalle correctamente.");
            return "redirect:/presupuesto/form/" + presupuesto.getId();
        } catch (ErrorServicio ex) {
            Logger.getLogger(PresupuestoDetalleControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/presupuesto/form/" + presupuesto.getId();
    }
    //Método para ir a la vista de de lo que se modifique  
  @PreAuthorize("hasAnyRole('ROLE_ADMIN') || hasAnyRole('ROLE_PERSONAL')")
    @GetMapping("/modificar/{id}")
    public String editar(ModelMap modelo, @PathVariable String id) {

        try {
            PresupuestoDetalle presupuestoDetalle = presupuestoDetalleServicio.buscarPorId(id);
            modelo.put("presupuestoDetalle", presupuestoDetalle);
        } catch (ErrorServicio ex) {

        }
        return "EditarDetallePresupuesto.html";
    }

    //Método para modificar los detalles de un presupuesto
      @PreAuthorize("hasAnyRole('ROLE_ADMIN') || hasAnyRole('ROLE_PERSONAL')")
    @PostMapping("/modificar")
    public String modificar(RedirectAttributes attr, @RequestParam String idDetalle, @RequestParam String detalle, @RequestParam Integer cantidad, @RequestParam Float precio, @RequestParam String idPresupuesto) {

        PresupuestoDetalle presupuestoDetalle = null;
        Presupuesto presupuesto = null;

        try {
            presupuestoDetalle = presupuestoDetalleServicio.buscarPorId(idDetalle);
            presupuesto = presupuestoDetalle.getPresupuesto();

            presupuestoDetalleServicio.modificar(idDetalle, detalle, cantidad, precio, idPresupuesto);
            attr.addFlashAttribute("exito", "El detalles se modifió correctamente.");

            return "redirect:/presupuesto/form/" + presupuesto.getId();

        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/presupuesto/form/" + presupuesto.getId();
    }

}
