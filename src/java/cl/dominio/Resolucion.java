/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.util.Objects;

/**
 *
 * @author CristianFaune
 */
public class Resolucion {
    private String idResolucion;
    private String descripcion;

    public Resolucion(String idResolucion, String descripcion) {
        this.idResolucion = idResolucion;
        this.descripcion = descripcion;
    }

    public Resolucion() {
    }

    public String getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(String idResolucion) {
        this.idResolucion = idResolucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idResolucion);
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
        final Resolucion other = (Resolucion) obj;
        if (!Objects.equals(this.idResolucion, other.idResolucion)) {
            return false;
        }
        return true;
    }
    
    
}
