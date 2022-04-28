
package com.tallereggs.repositorios;

import com.tallereggs.entidades.Presupuesto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PresupuestoRepositorio extends JpaRepository<Presupuesto, String> {
    
    @Query("SELECT p FROM Presupuesto p WHERE p.id = :id")
    public Presupuesto buscarPresupuestoPorId(@Param("id") String id);
    
    @Query("SELECT p FROM Presupuesto p WHERE p.presupuestodetalle.id = :id")
    public List<Presupuesto> buscarPresupuestoPorPresupuestoDetalle(@Param("id") String id);
}
