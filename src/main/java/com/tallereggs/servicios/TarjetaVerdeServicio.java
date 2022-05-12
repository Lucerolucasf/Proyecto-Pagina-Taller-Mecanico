
package com.tallereggs.servicios;

import com.tallereggs.entidades.TarjetaVerde;
import com.tallereggs.errores.ErrorServicio;
import com.tallereggs.repositorios.TarjetaVerdeRepositorio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TarjetaVerdeServicio {
    
    @Autowired 
    private TarjetaVerdeRepositorio tarjetaVerdeRepositorio;
    
    public TarjetaVerde guardar(MultipartFile archivo) throws ErrorServicio{
       
        if(archivo != null){
        
            try{
            TarjetaVerde tv = new TarjetaVerde();
            tv.setMime(archivo.getContentType());
            tv.setNombre(archivo.getName());
            tv.setContenido(archivo.getBytes());
            
            return tarjetaVerdeRepositorio.save(tv);
           } catch(Exception e){
               System.err.println(e.getMessage());
           }
        } 
            return null;
        }
        
    public TarjetaVerde actualizar(String idTarjetaVerde, MultipartFile archivo) throws ErrorServicio{
        
        if(archivo != null){
        
            try{
            TarjetaVerde tv = new TarjetaVerde();
            
            if(idTarjetaVerde != null){
                Optional<TarjetaVerde> respuesta = tarjetaVerdeRepositorio.findById(idTarjetaVerde);
                if(respuesta.isPresent()){
                    tv = respuesta.get();
                }
            }
            tv.setMime(archivo.getContentType());
            tv.setNombre(archivo.getName());
            tv.setContenido(archivo.getBytes());
            
            return tarjetaVerdeRepositorio.save(tv);
           } catch(Exception e){
               System.err.println(e.getMessage());
           }
        } 
            return null;
    }
        
}
