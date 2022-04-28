
package com.tallereggs.servicios;

import com.tallereggs.repositorios.PresupuestoDetalleRepositorio;
import com.tallereggs.repositorios.PresupuestoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PresupuestoDetalleServicio {
    
    @Autowired
    private PresupuestoDetalleRepositorio presupuestoDetalleRepositorio;
    
    @Autowired
    private PresupuestoRepositorio presupuestoRepositorio;
    
    
    @Transactional(rollbackFor = {Exception.class})
    public void eliminar(String id){
        //buscar el id de presupuesto vinculado a la descripcion
        //
        
    }
    
}
