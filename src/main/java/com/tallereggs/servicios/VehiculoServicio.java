
package com.tallereggs.servicios;


import com.tallereggs.entidades.Usuario;
import com.tallereggs.entidades.Vehiculo;
import com.tallereggs.enums.EnumROL;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.repositorios.VehiculoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehiculoServicio {
    
    
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo crear(String id, String patente, String modelo, String marca, String anio, String km, String idUsuario, Img tarjetaVerde, EnumEstado estado) throws Exception {

        validar(patente, modelo, marca, anio, km, idUsuario, tarjetaVerde, estado);
        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setId(id);
        vehiculo.setPatente(patente);
        vehiculo.setModelo(modelo);
        vehiculo.setMarca(marca);
        vehiculo.setAnio(anio);
        vehiculo.setKm(km);
        vehiculo.settarjetaVerde(tarjetaVerde); //ver si funciona
        vehiculo.setEstado(estado);
        vehiculo.setUsuario(UsuarioServicio.buscarPorId(idUsuario));
        
        VehiculoRepositorio.save(Vehiculo);
        return null;

    }
    
    
    
    
    //Método para validar que los datos ingresados no sean nulos ni vengan vacíos.
    public void validar(String patente, String modelo, String marca, String anio, String km, String idUsuario, Img tarjetaVerde, EnumEstado estado) throws Exception {

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
        
        if (tarjetaVerde == null || tarjetaVerde.isEmpty()) {
        throw new ErrorServicio ("Ingrese una foto válida.");
        }
        
        if (estado == null || estado.isEmpty()) {
            throw new ErrorServicio ("Ingrese un estado válido.");
        }
    
    
        //metodos siguientes
        
        
    @Transactional(rollbackFor = Exception.class)
    public void actualizar (String id, String patente, String modelo, String marca, String anio, String km, String idUsuario, Img tarjetaVerde, EnumEstado estado) throws Exception {

        Vehiculo vehiculo = buscarPorId(id);
        if (vehiculo == null) {

            throw new Exception("El vehículo no existe");
        }

        if (!vehiculo.getAlta()) {
            throw new Exception("El vehiculo está dado de baja");
        }

        validarActualizar(id, patente, modelo, marca, anio, km, idUsuario, tarjetaVerde, estado);

        vehiculo.setId(id);
        vehiculo.setPatente(patente);
        vehiculo.setModelo(modelo);
        vehiculo.setMarca(marca);
        vehiculo.setAnio(anio);
        vehiculo.setKm(km);
        vehiculo.setUsuario(UsuarioServicio.buscarPorId(idUsuario));
        vehiculo.setTarjetaVerde(tarjetaVerde);
        vehiculo.setEstado(estado);

        VehiculoRepositorio.save(vehiculo);

    }

    @Transactional(readOnly = true)
    public List<Vehiculo> mostrarVehiculos() {
        return VehiculoRepositorio.findAll();
    }

    }

    @Transactional(readOnly = true)
    public Vehiculo buscarPorId(String id) {

        Optional<Vehiculo> v = VehiculoRepositorio.findById(id);

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

        VehiculoRepositorio.save(v);
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

        VehiculoRepositorio.save(v);
    }


    public void validarActualizar(String id, String patente, String modelo, String marca, String anio, String km, String idUsuario, Img tarjetaVerde, EnumEstado estado) throws Exception {

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
        
        Usuario usuario = UsuarioServicio.buscarPorId(idUsuario);
        if (usuario == null) {
            throw new Exception("El usuario no existe");
        }

        if (tarjetaVerde == null || tarjetaVerde.isEmpty()) {
            throw new Exception("Debe ingresar una foto de la tarjeta verde");
        }
        
        if (estado == null) {
            throw new Exception("Debe ingresar el estado del vehiculo");
        }
    }
        
        
        
}
