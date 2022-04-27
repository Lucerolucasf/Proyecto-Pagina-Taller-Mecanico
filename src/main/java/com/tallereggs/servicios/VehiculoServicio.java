
package com.tallereggs.servicios;


import com.tallereggs.enums.EnumROL;
import com.tallereggs.errores.ErrorServicio;
import org.springframework.stereotype.Service;

@Service
public class VehiculoServicio {
    
    
    //Método para validar que los datos ingresados no sean nulos ni vengan vacíos.
    public void validar(String patente, String modelo, String marca, String anio, String km, String idUsuario, Img tarjetaVerde, EnumROL estado) throws Exception {

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
   
//        if (alta == null || alta.isEmpty()) {
//            throw new ErrorServicio ("Ingrese una patente válida.");
//        }
        
//        if (tarjetaverde == null || tarjetaverde.isEmpty()) {
//            throw new ErrorServicio ("Ingrese una foto válida.");
//        }
        
        if (estado == null || estado.isEmpty()) {
            throw new ErrorServicio ("Ingrese un estado válido.");
        }

//        private String id;
//    private String patente;
//    private String modelo;
//    private String marca;
//    private String anio;
//    private String km;
//    @ManyToOne
//    private Usuario usuario;
//    private Boolean alta;
//    //private Img tarjetaVerde;
//    private EnumROL estado;

    }
    
    
}
