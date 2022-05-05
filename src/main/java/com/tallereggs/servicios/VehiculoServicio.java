
package com.tallereggs.servicios;


import com.tallereggs.entidades.TarjetaVerde;
import com.tallereggs.entidades.Usuario;
import com.tallereggs.entidades.Vehiculo;
import com.tallereggs.enums.EnumEstado;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.repositorios.VehiculoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VehiculoServicio {
    
    
    @Autowired
    private TarjetaVerdeServicio tarjetaVerdeServicio; 
    
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio; 
    
    @Autowired
    private UsuarioServicio usuarioServicio; 
    
    @Autowired
    private VehiculoServicio vehiculoServicio; 
    
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo crear(String id, String patente, String modelo, String marca, String anio, String km, String idUsuario, MultipartFile archivo, EnumEstado estado) throws Exception {

        validar(patente, modelo, marca, anio, km, idUsuario, archivo, estado);
        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setId(id);
        vehiculo.setPatente(patente);
        vehiculo.setModelo(modelo);
        vehiculo.setMarca(marca);
        vehiculo.setAnio(anio);
        vehiculo.setKm(km);
        vehiculo.setEstado(estado);
        vehiculo.setUsuario(usuarioServicio.buscarPorId(idUsuario));
        
        TarjetaVerde tv = tarjetaVerdeServicio.guardar(archivo);
        vehiculo.setTv(tv);
        
        vehiculoRepositorio.save(vehiculo);
        return null;

    }
    
        
    
    
    //Método para validar que los datos ingresados no sean nulos ni vengan vacíos.
    public void validar(String patente, String modelo, String marca, String anio, String km, String idUsuario, MultipartFile archivo, EnumEstado estado) throws Exception {

        if (patente == null || patente.isEmpty()) {
            throw new ErrorServicio ("Ingrese una patente válida.");
        }
        
        if (modelo == null || modelo.isEmpty()) {
            throw new ErrorServicio ("Ingrese un modelo válido.");
        }
        
        if (marca == null || marca.isEmpty()) {
            throw new ErrorServicio ("Ingrese una marca válida.");
        }
        
        if (anio == null || anio.isEmpty()) {
            throw new ErrorServicio ("Ingrese un año válido.");
        }
        
        if (km == null || km.isEmpty()) {
            throw new ErrorServicio ("Ingrese un dato de kilómetros válida.");
        }
        
        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new ErrorServicio ("Ingrese un usuario válido.");
        }
   
        if (patente == null || patente.isEmpty()) {
            throw new ErrorServicio ("Ingrese una patente válida.");
        }
        
        Vehiculo vehiculo = new Vehiculo();
        
        if (vehiculo.getTv() == null) {
        throw new ErrorServicio ("Ingrese una foto válida.");
        }
        
        if (estado == null) {
            throw new ErrorServicio ("Ingrese un estado válido.");
        }
 
            
    }
        //metodos siguientes
        
        
    @Transactional(rollbackFor = Exception.class)
    public void actualizar (String id, String patente, String modelo, String marca, String anio, String km, String idUsuario, MultipartFile archivo, EnumEstado estado) throws Exception {

        
        if (id == null) {

            throw new Exception("El vehículo no existe");
        }

        Vehiculo vehiculo = vehiculoServicio.buscarPorId(id);
        
        if (!vehiculo.getAlta()) {
            throw new Exception("El vehiculo está dado de baja");
        }

        vehiculoServicio.validarActualizar(id, patente, modelo, marca, anio, km, idUsuario, archivo, estado);
        //validarActualizar(id, patente, modelo, marca, anio, km, idUsuario, archivo, estado);

        vehiculo.setId(id);
        vehiculo.setPatente(patente);
        vehiculo.setModelo(modelo);
        vehiculo.setMarca(marca);
        vehiculo.setAnio(anio);
        vehiculo.setKm(km);
        vehiculo.setUsuario(usuarioServicio.buscarPorId(idUsuario));
        vehiculo.setEstado(estado);

        String idTarjetaVerde = null;
        if(vehiculo.getTv() != null){
            idTarjetaVerde = vehiculo.getTv().getId();
        }
        
        TarjetaVerde tv = tarjetaVerdeServicio.actualizar(idTarjetaVerde, archivo);
        
        
        
        vehiculoRepositorio.save(vehiculo);

    }

    @Transactional(readOnly = true)
    public List<Vehiculo> mostrarVehiculos() {
        return vehiculoRepositorio.findAll();
    }

    

    @Transactional(readOnly = true)
    public Vehiculo buscarPorId(String id) {

        Optional<Vehiculo> v = vehiculoRepositorio.findById(id);

        if (v.isPresent()) {
            Vehiculo vehiculo = v.get();
            return vehiculo;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> buscarPorPatente(String patente) {

        List<Vehiculo> vehiculos = VehiculoRepositorio.buscarPorPatente(patente);

        return vehiculos;

    }

    @Transactional(rollbackFor = Exception.class)
    public void baja(String id) throws Exception {

        Vehiculo v = buscarPorId(id);
        if (v == null) {
            throw new Exception("El vehiculo no existe");
        }

        if (!v.getAlta()) {
            throw new Exception("El vehiculo ya está dado de baja");
        }

        v.setAlta(Boolean.FALSE);

        vehiculoRepositorio.save(v);
    }

    
    @Transactional(rollbackFor = Exception.class) 
    public void alta(String id) throws Exception {

        Vehiculo v = buscarPorId(id);
        if (v == null) {
            throw new Exception("El vehiculo no existe");
        }

        if (v.getAlta()) {
            throw new Exception("El vehiculo ya está dado de alta");
        }

        v.setAlta(Boolean.TRUE);

        vehiculoRepositorio.save(v);
    }


    public void validarActualizar(String id, String patente, String modelo, String marca, String anio, String km, String idUsuario, MultipartFile tarjetaVerde, EnumEstado estado) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar un id");
        }

        if (patente == null || patente.isEmpty()) {
            throw new Exception("Debe ingresar una patente");
        }

        if (modelo == null || modelo.isEmpty()) {
            throw new Exception("Debe ingresar un modelo");
        }

        if (marca == null || marca.isEmpty()) {
            throw new Exception("Debe ingresar una marca");
        }

        if (anio == null) {
            throw new Exception("Debe ingresar un año");
        }
        
        if (km == null || km.isEmpty()) {
            throw new Exception("Debe ingresar los kilómetros");
        }
        
        Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
        if (usuario == null) {
            throw new Exception("El usuario no existe");
        }

        Vehiculo vehiculo = new Vehiculo();
        
        if (vehiculo.getTv() == null) {
            throw new Exception("Debe ingresar una foto de la tarjeta verde");
        }
        
        if (estado == null) {
            throw new Exception("Debe ingresar el estado del vehiculo");
        }
    }
        
        
        
}
