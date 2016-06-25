/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Categoria;
import cl.dominio.Resolucion;
import cl.dominio.Video;

/**
 *
 * @author CristianFaune
 */
public class VideoCatResDTO {
    private Video video;
    private Categoria categoria;
    private Resolucion resolucion;

    public VideoCatResDTO(Video video, Categoria categoria, Resolucion resolucion) {
        this.video = video;
        this.categoria = categoria;
        this.resolucion = resolucion;
    }

    public VideoCatResDTO() {
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Resolucion getResolucion() {
        return resolucion;
    }

    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }
    
    
}
