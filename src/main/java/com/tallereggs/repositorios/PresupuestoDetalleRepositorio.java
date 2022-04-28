
package com.tallereggs.repositorios;

import com.tallereggs.entidades.PresupuestoDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PresupuestoDetalleRepositorio extends JpaRepository<PresupuestoDetalle, String> {

@Query("SELECT d FROM PresupuestoDetalle d WHERE d.id LIKE :id")
public PresupuestoDetalle buscarPresupuestoDetallePorId(@Param("id") String id);

@Query("SELECT d FROM PresupuestoDetalle d WHERE d.presupuesto.id LIKE :id")
public List<PresupuestoDetalle> buscarPresupuestoDetallePorPresupuesto(@Param("id") String id);

}
