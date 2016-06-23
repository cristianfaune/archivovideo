/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author CristianFaune
 */
public class Video {
    private String idVideo;
    private String nombre;
    private Date fechaOrigen;
    private Timestamp fechaSubida;
    private String duracion;
    private String descripcion;
    private String rutaArchivoVideo;
    private String idCategoria;
    private String idResolucion;
    private String rut;

    public Video(String idVideo, String nombre, Date fechaOrigen, Timestamp fechaSubida, String duracion, String descripcion, String rutaArchivoVideo, String idCategoria, String idResolucion, String rut) {
        this.idVideo = idVideo;
        this.nombre = nombre;
        this.fechaOrigen = fechaOrigen;
        this.fechaSubida = fechaSubida;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.rutaArchivoVideo = rutaArchivoVideo;
        this.idCategoria = idCategoria;
        this.idResolucion = idResolucion;
        this.rut = rut;
    }

    public Video() {
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaOrigen() {
        return fechaOrigen;
    }

    public void setFechaOrigen(Date fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public Timestamp getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Timestamp fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaArchivoVideo() {
        return rutaArchivoVideo;
    }

    public void setRutaArchivoVideo(String rutaArchivoVideo) {
        this.rutaArchivoVideo = rutaArchivoVideo;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(String idResolucion) {
        this.idResolucion = idResolucion;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idVideo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (!Objects.equals(this.idVideo, other.idVideo)) {
            return false;
        }
        return true;
    }
    
    
}
