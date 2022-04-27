
package com.tallereggs.repositorios;

import com.tallereggs.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{
    
    
    
    //metodo guardar, actualizar-modificar, consultar y eliminar
}
