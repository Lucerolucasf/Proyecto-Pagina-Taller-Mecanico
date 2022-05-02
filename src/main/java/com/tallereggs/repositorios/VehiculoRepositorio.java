
package com.tallereggs.repositorios;

import com.tallereggs.entidades.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{

    public static List<Vehiculo> buscarPorPatente(String patente);
    
    
    
    //metodo guardar, actualizar-modificar, consultar y eliminar
}
