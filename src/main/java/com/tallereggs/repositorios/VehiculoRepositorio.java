
package com.tallereggs.repositorios;

import com.tallereggs.entidades.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{

    @Query("SELECT v FROM Vehiculo v WHERE v.patente LIKE %:patente% ")
    public List<Vehiculo> buscarPorPatente(@Param("patente")String patente);
    
    
    
    //metodo guardar, actualizar-modificar, consultar y eliminar
}
