package cl.controlador;

import cl.dominio.Categoria;
import cl.dominio.Resolucion;
import cl.dominio.Usuario;
import cl.dominio.Video;
import cl.dto.VideoCatResDTO;
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
@WebServlet(name = "ModificarServlet", urlPatterns = {"/ModificarServlet"})
public class ModificarServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/archivovideo")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idVideo = request.getParameter("idVideo");
        Video video = new Video();
        String horas;
        String minutos;
        String segundos;

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            video = servicio.buscarVideoPorId(idVideo);

            horas = video.getDuracion().substring(0, 2);
            minutos = video.getDuracion().substring(3, 5);
            segundos = video.getDuracion().substring(6, 8);

            ArrayList<Categoria> lista = servicio.buscarTodasCategorias();
            ArrayList<Resolucion> listaResolucion = servicio.buscarTodasResolucion();

            request.setAttribute("video", video);
            request.setAttribute("horas", horas);
            request.setAttribute("minutos", minutos);
            request.setAttribute("segundos", segundos);
            request.setAttribute("lstCategoria", lista);
            request.setAttribute("lstResolucion", listaResolucion);
            request.getRequestDispatcher("Modificar.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("error en la conexión a la bd", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuarioSesion");

        Map<String, String> mapMensaje = new HashMap<>();
        Map<String, String> mapMensajeExito = new HashMap<>();
        mapMensajeExito.put("mensajeExito", "**El video fue actualizado exitosamente**");

        String categoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        String horas = request.getParameter("horas");
        String minutos = request.getParameter("minutos");
        String segundos = request.getParameter("segundos");
        String fechaOrigen = request.getParameter("fechaOrigen");
        String resolucion = request.getParameter("resolucion");
        String descripcion = request.getParameter("descripcion");
        ArrayList<VideoCatResDTO> listaVideo = new ArrayList<>();
        String idVideo = request.getParameter("idVideo");

        String duracion = "";

        Video video = new Video();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            listaVideo = servicio.todosLosVideos();

            if (categoria.equals("0")) {
                mapMensaje.put("errorCategoria", "**Debe ingresar una categoría**");
            } else {
                video.setIdCategoria(categoria);
            }

            if (resolucion.equals("0")) {
                mapMensaje.put("errorResolucion", "**Debe ingresar una resolución**");
            } else {
                video.setIdResolucion(resolucion);
            }

            if (nombre.isEmpty()) {
                mapMensaje.put("errorNombre", "**Debe ingresar un nombre**");
            } else {
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

            duracion = horas + ":" + minutos + ":" + segundos;

            video.setDuracion(duracion);
            
            video.setIdVideo(idVideo);

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

            video.setDescripcion(descripcion);

            if (mapMensaje.isEmpty()) {
                servicio.modificarVideo(video);
                request.setAttribute("mensajeActualizado", mapMensajeExito);
                request.setAttribute("lstVideos", listaVideo);
                request.getRequestDispatcher("Consulta.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("Modificar.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión bd", e);
        }

    }
}
