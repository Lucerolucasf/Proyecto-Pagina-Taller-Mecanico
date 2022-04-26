
package com.tallereggs.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Vehiculo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String patente;
    private String modelo;
    private String marca;
    private String anio;
    private String km;
    @ManyToOne
    private Usuario usuario;
    private Boolean alta;
    //private Img tarjetaVerde;
    private Enum estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Enum getEstado() {
        return estado;
    }

    public void setEstado(Enum estado) {
        this.estado = estado;
    }
    
    
}
