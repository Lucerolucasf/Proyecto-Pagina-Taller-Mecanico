package com.tallereggs.servicios;

import com.tallereggs.entidades.Presupuesto;
import com.tallereggs.entidades.PresupuestoDetalle;
import com.tallereggs.entidades.Usuario;
import com.tallereggs.entidades.Vehiculo;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.repositorios.PresupuestoDetalleRepositorio;
import com.tallereggs.repositorios.PresupuestoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PresupuestoServicio {

    @Autowired
    private PresupuestoRepositorio presupuestoRepositorio;

    @Autowired
    private PresupuestoDetalleRepositorio presupuestoDetalleRepositorio;
    
    @Autowired
    private VehiculoServicio vehiculoServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;


    //Método para agregar presupuestos
    @Transactional(rollbackFor = {Exception.class})
    public Presupuesto agregar(String idVehiculo, String fallaDescripcion, Float total) throws ErrorServicio, Exception {

        validar(idVehiculo, fallaDescripcion, total);

        Presupuesto presupuesto = new Presupuesto();

        Vehiculo vehiculo = vehiculoServicio.buscarPorId(idVehiculo);
        
        presupuesto.setFallaDescripcion(fallaDescripcion);
        presupuesto.setTotal(0f);
        presupuesto.setVehiculo(vehiculo);
        presupuesto.setUsuario(vehiculo.getUsuario());

        presupuestoRepositorio.save(presupuesto);
        
        return presupuesto;
    }

    //Método para modificar un presupuesto.
    @Transactional(rollbackFor = {Exception.class})
    public void modificar(String id, String idVehiculo, String idUsuario, String fallaDescripcion, Float total) throws ErrorServicio {

        validar(idVehiculo, fallaDescripcion, total);

        Presupuesto presupuesto = presupuestoRepositorio.buscarPresupuestoPorId(id);
        //Vehiculo vehiculo = vehiculoServicio.buscarVehiculoPorId(idVehiculo);
        //Usuario usuario = usuarioServicio.buscarUsuarioPorId(idUsuario);

        presupuesto.setFallaDescripcion(fallaDescripcion);
        presupuesto.setTotal(total);
        //presupuesto.setVehiculo(vehiculo);
        //presupuesto.setUsuario(usuario);

        presupuestoRepositorio.save(presupuesto);
    }

    //Método para eliminar presupuesto
    @Transactional(rollbackFor = {Exception.class})
    public void eliminar(String id) throws ErrorServicio {
        if(id == null || id.isEmpty()){
            throw new ErrorServicio("Ingrese un id válido por favor.");
        }
        //buscar todos los presupuestosDetalles
        List<PresupuestoDetalle> presupuestosDetalle = presupuestoDetalleRepositorio.buscarPresupuestoDetallePorPresupuesto(id);
        //eliminar presupuestos detalles
        presupuestoDetalleRepositorio.deleteAll(presupuestosDetalle);
        //eliminar presupuesto
        presupuestoRepositorio.deleteById(id);

    }

    //Método para buscar presupuesto por nombre de usuario.
    @Transactional(readOnly = true)
    public List<Presupuesto> buscarPresupuestosPorIdVehiculo(String id) throws ErrorServicio {
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("Ingrese un nombre válido");
        }
        List<Presupuesto> presupuestos = presupuestoRepositorio.buscarPresupuestosPorIdVehiculo(id);

        if (presupuestos != null) {
            return presupuestos;
        } else {
            throw new ErrorServicio("No se encontró el presupuesto asignado al id del vehiculo ingresado.");
        }

    }
    //Averiguar como traer todos los detalles a un presupuesto en particular. CONSULTAR!!!!!!!!!
    @Transactional(readOnly = true)
    public List<PresupuestoDetalle> listarDetallesEnPresupuesto(String id) throws ErrorServicio{
         if(id == null || id.isEmpty()){
            throw new ErrorServicio("Ingrese un id válido.");
        }
        //buscar todos los presupuestosDetalles
        List<PresupuestoDetalle> presupuestosDetalle = presupuestoDetalleRepositorio.buscarPresupuestoDetallePorPresupuesto(id);
       
        return presupuestosDetalle;
        
    }

    
    //Método para listar todos los presupuestos
    @Transactional(readOnly = true)
    public List<Presupuesto> listarTodos(){
        return presupuestoRepositorio.findAll();
    }
    
    //Método para listar todos los detalles de presupuesto
    @Transactional(readOnly = true)
    public List<PresupuestoDetalle> listarDetalles(){
        return presupuestoDetalleRepositorio.findAll();
    }
    

    //Método para buscar presupuesto por Id
    @Transactional(readOnly = true)
    public Presupuesto buscarPorId(String id) throws ErrorServicio {
        Optional<Presupuesto> respuesta = presupuestoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new ErrorServicio("No se encontró el presupuesto solicitado.");
        }
    }
    
    @Transactional(readOnly = true)
    public Vehiculo buscarVehiculoPorId(String id) throws ErrorServicio{
        Vehiculo vehiculo = vehiculoServicio.buscarPorId(id);
        return vehiculo;
    }
    


    //Método para validar que los datos ingresados no sean nulos ni vengan vacíos
    public void validar(String idVehiculo, String fallaDescripcion, Float total) throws ErrorServicio {
        if (idVehiculo == null || idVehiculo.trim().isEmpty()) { //El método trim(), sirve para que no llenen el form con un espacio vacío presionando barra espaciadora.
            throw new ErrorServicio("Ingrese un vehículo válido.");
        }
        if (fallaDescripcion == null || fallaDescripcion.trim().isEmpty()) {
            throw new ErrorServicio("La falla de la descripción no puede ser nula.");
        }
        if (total == null || total < 0) {
            throw new ErrorServicio("El número ingresado no puede ser nulo o menor a cero.");
        }
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios(){
    return usuarioServicio.listarUsuarios();
}
    
    @Transactional(readOnly = true)
    public List<Vehiculo> listarVehiculos(){
        return vehiculoServicio.mostrarVehiculos();
    }

}
