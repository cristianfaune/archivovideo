/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Categoria;
import cl.dominio.Resolucion;
import cl.dominio.Usuario;
import cl.dominio.Video;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario us = (Usuario)session.getAttribute("usuarioSesion");
        
        Map<String, String> mapMensaje = new HashMap<>();
        
        String categoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        String horas = request.getParameter("horas");
        String minutos = request.getParameter("minutos");
        String segundos = request.getParameter("segundos");
        String fechaOrigen = request.getParameter("fechaOrigen");
        String resolucion = request.getParameter("resolucion");
        String descripcion = request.getParameter("descripcion");
        String nombreArchivo = request.getParameter("nombreVideo");
        
        String duracion = "";
        
        Video video = new Video();
        
        try (Connection con = ds.getConnection()){
            
            Servicio servicio = new Servicio(con);
            
            int cont = servicio.numeroFilaVideo();
            
            if (categoria.equals("0")) {
                mapMensaje.put("errorCategoria", "**Debe ingresar una categoría**");
            }else{
                video.setIdCategoria(categoria);
            }
            
            if (resolucion.equals("0")) {
                mapMensaje.put("errorResolucion", "**Debe ingresar una resolución**");
            }else{
                video.setIdResolucion(resolucion);
            }
            
            if (nombre.isEmpty()) {
                mapMensaje.put("errorNombre", "**Debe ingresar un nombre**");
            }else{
                video.setNombre(nombre);
            }
            
            if (horas.isEmpty()) {
                
                horas = "00";  
            }
            
            if (minutos.isEmpty()) {
                
                minutos = "00";
            }
            
            if (segundos.isEmpty()) {
                
                segundos = "00";
            }
            
            duracion = horas+":"+minutos+":"+segundos;
            
            video.setDuracion(duracion);
            
            
            if (fechaOrigen.isEmpty()) {
                mapMensaje.put("errorFecha", "**Debe seleccionar una fecha**");
            } else {
                try {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = sdf1.parse(fechaOrigen);
                    java.sql.Date fechaDate = new java.sql.Date(date.getTime());

                    video.setFechaOrigen(fechaDate);

                } catch (ParseException e) {
                    throw new RuntimeException("error", e);
                }

            }
            
            video.setFechaSubida(new Timestamp(System.currentTimeMillis()));
            
            video.setRut(us.getRut());
            
            video.setDescripcion(descripcion);
            
            video.setIdVideo(categoria+resolucion+String.format("%04d", cont));
            
            video.setRutaArchivoVideo("video/"+nombreArchivo);
            
            servicio.registrarVideo(video);
            
            request.getRequestDispatcher("RegistroVideo.jsp").forward(request, response);
            
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión bd",e);
        }

    }


}
