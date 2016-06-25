/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Categoria;
import cl.dominio.Resolucion;
import cl.dominio.Usuario;
import cl.dominio.Video;
import cl.dto.VideoCatResDTO;
import cl.persistencia.CategoriaDAO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.ResolucionDAO;
import cl.persistencia.UsuarioDAO;
import cl.persistencia.VideoDAO;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class Servicio {
    UsuarioDAO usuarioDAO;
    CategoriaDAO categoriaDAO;
    ResolucionDAO resolucionDAO;
    VideoDAO videoDAO;
    ConsultaDAO consultaDAO;

    public Servicio(Connection con) {
        usuarioDAO = new UsuarioDAO(con);
        categoriaDAO = new CategoriaDAO(con);
        resolucionDAO = new ResolucionDAO(con);
        videoDAO = new VideoDAO(con);
        consultaDAO = new ConsultaDAO(con);
    }
    
    public Usuario buscarUsuarioEmail(String usuarioEmail) {
        return usuarioDAO.buscarUsuarioEmail(usuarioEmail);
    }
    
    public ArrayList<Categoria> buscarTodasCategorias() {
        return categoriaDAO.buscarTodasCategorias();
    }
    
    public ArrayList<Resolucion> buscarTodasResolucion() {
        return resolucionDAO.buscarTodasResolucion();
    }
    
    public void registrarVideo(Video video) {
        videoDAO.registrarVideo(video);
    }
    
    public int numFilasVideo() {
        return videoDAO.numFilasVideo();
    }
    
    public ArrayList<VideoCatResDTO> todosLosVideos() {
        return consultaDAO.todosLosVideos();
    }
    
    public void eliminarVideo(String idVideo) {
        videoDAO.eliminarVideo(idVideo);
    }
    
    public Video buscarVideoPorId(String idVideo) {
        return videoDAO.buscarVideoPorId(idVideo);
    }
    
    public void modificarVideo(Video video) {
        videoDAO.modificarVideo(video);
    }
}
