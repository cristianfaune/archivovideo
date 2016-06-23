/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Categoria;
import cl.dominio.Resolucion;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author CristianFaune
 */
@WebServlet(name = "RegistroVideoServlet", urlPatterns = {"/RegistroVideoServlet"})
public class RegistroVideoServlet extends HttpServlet {
    
    @Resource(mappedName = "jdbc/archivovideo")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (Connection con = ds.getConnection()){
            
            Servicio servicio = new Servicio(con);
            
            ArrayList<Categoria> lista = servicio.buscarTodasCategorias();
            ArrayList<Resolucion> listaResolucion = servicio.buscarTodasResolucion();
            
            request.setAttribute("lstCategoria", lista);
            request.setAttribute("lstResolucion", listaResolucion);
            request.getRequestDispatcher("RegistroVideo.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión bd",e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
