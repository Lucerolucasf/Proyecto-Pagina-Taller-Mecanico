package com.tallereggs.servicios;

import com.tallereggs.entidades.Presupuesto;
import com.tallereggs.entidades.PresupuestoDetalle;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.repositorios.PresupuestoDetalleRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PresupuestoDetalleServicio {

    @Autowired
    private PresupuestoDetalleRepositorio presupuestoDetalleRepositorio;

    @Autowired
    private PresupuestoServicio presupuestoServicio;

    //Método para agregar detalles al presupuesto
    @Transactional(rollbackFor = Exception.class)
    public void agregar(String detalle, Integer cantidad, Float precio, String idPresupuesto) throws ErrorServicio {

        validar(detalle, cantidad, precio, idPresupuesto);

        PresupuestoDetalle presupuestoDetalle = new PresupuestoDetalle();

        Presupuesto presupuesto = presupuestoServicio.buscarPorId(idPresupuesto);

        presupuestoDetalle.setDetalle(detalle);
        presupuestoDetalle.setCantidad(cantidad);
        presupuestoDetalle.setPrecio(precio);
        presupuestoDetalle.setPresupuesto(presupuesto);

        presupuestoDetalleRepositorio.save(presupuestoDetalle);

    }

    //Método para modificar detalles del presupuesto
    @Transactional(rollbackFor = Exception.class)
    public void modificar(String id, String detalle, Integer cantidad, Float precio, String idPresupuesto) throws ErrorServicio {

        validar(detalle, cantidad, precio, idPresupuesto);

        PresupuestoDetalle presupuestoDetalle = buscarPorId(id);

        Presupuesto presupuesto = presupuestoServicio.buscarPorId(idPresupuesto);

        presupuestoDetalle.setDetalle(detalle);
        presupuestoDetalle.setCantidad(cantidad);
        presupuestoDetalle.setPrecio(precio);
        presupuestoDetalle.setPresupuesto(presupuesto);

        presupuestoDetalleRepositorio.save(presupuestoDetalle);
    }

    //Método para eliminar detalles de presupuesto
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(String id) {
        presupuestoDetalleRepositorio.deleteById(id);
    }

    //Método para listar todos los detalles del presupuesto
    @Transactional(readOnly = true)
    public List<PresupuestoDetalle> listarTodos() {
        return presupuestoDetalleRepositorio.findAll();
    }

    //Método para sumar los precios de los detalles del presupuesto
    @Transactional(rollbackFor = Exception.class)
    public Float sumarPrecios(String id) {
        Float sumaTotal = 0f;
        List<PresupuestoDetalle> detallesPresupuesto = presupuestoDetalleRepositorio.buscarPresupuestoDetallePorPresupuesto(id);
        for (PresupuestoDetalle aux : detallesPresupuesto) {
            sumaTotal =+ aux.getPrecio();
        }
        return sumaTotal;
    }

    //Método para buscar por Id verificando que se encuentre en la base de datos
    @Transactional(readOnly = true)
    public PresupuestoDetalle buscarPorId(String id) throws ErrorServicio {
        Optional<PresupuestoDetalle> respuesta = presupuestoDetalleRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new ErrorServicio("El detalle del presupuesto que intenta buscar no se ha encontrado.");
        }
    }

    //Método para validar que el usuario no ingrese un dato null o vacío
    public void validar(String detalle, Integer cantidad, Float precio, String idPresupuesto) throws ErrorServicio {
        if (detalle == null || detalle.trim().isEmpty()) {
            throw new ErrorServicio("El detalle no puede estar vacío. ");
        }
        if (cantidad == null || cantidad < 0) {
            throw new ErrorServicio("Ingrese una cantidad válida.");
        }
        if (precio == null || precio < 0) {
            throw new ErrorServicio("Ingrese un precio válido.");
        }
        if (idPresupuesto == null || idPresupuesto.trim().isEmpty()) {
            throw new ErrorServicio("Ingrese un ID válido.");
        }
    }

}
