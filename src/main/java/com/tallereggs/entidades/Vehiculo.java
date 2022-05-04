
package com.tallereggs.entidades;

import com.tallereggs.enums.EnumEstado;
import com.tallereggs.enums.EnumROL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

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
    
    @OneToOne
    private TarjetaVerde tv;
    private EnumEstado estado;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the patente
     */
    public String getPatente() {
        return patente;
    }

    /**
     * @param patente the patente to set
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * @return the km
     */
    public String getKm() {
        return km;
    }

    /**
     * @param km the km to set
     */
    public void setKm(String km) {
        this.km = km;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the alta
     */
    public Boolean getAlta() {
        return alta;
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    /**
     * @return the tv
     */
    public TarjetaVerde getTv() {
        return tv;
    }

    /**
     * @param tv the tv to set
     */
    public void setTv(TarjetaVerde tv) {
        this.tv = tv;
    }

    /**
     * @return the estado
     */
    public EnumEstado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }

    

    
    
    
}
