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
public class Cargo {
    private String idCargo;
    private String descripcion;

    public Cargo(String idCargo, String descripcion) {
        this.idCargo = idCargo;
        this.descripcion = descripcion;
    }

    public Cargo() {
    }

    public String getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(String idCargo) {
        this.idCargo = idCargo;
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
        hash = 59 * hash + Objects.hashCode(this.idCargo);
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
        final Cargo other = (Cargo) obj;
        if (!Objects.equals(this.idCargo, other.idCargo)) {
            return false;
        }
        return true;
    }
    
    
}
