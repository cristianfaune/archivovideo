/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Categoria;
import cl.dominio.Resolucion;
import cl.dominio.Usuario;
import cl.persistencia.CategoriaDAO;
import cl.persistencia.ResolucionDAO;
import cl.persistencia.UsuarioDAO;
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

    public Servicio(Connection con) {
        usuarioDAO = new UsuarioDAO(con);
        categoriaDAO = new CategoriaDAO(con);
        resolucionDAO = new ResolucionDAO(con);
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
    
}
