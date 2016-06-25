package cl.controlador;

import cl.dto.VideoCatResDTO;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "EliminarServlet", urlPatterns = {"/EliminarServlet"})
public class EliminarServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/archivovideo")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idVideo = request.getParameter("idVideo");
        ArrayList<VideoCatResDTO> lista = new ArrayList<>();
        Map<String, String> mapMensaje = new HashMap<>();
        mapMensaje.put("mensajeEliminado", "**El archivo de video fue exitosamente eliminado**");
        
        try (Connection con = ds.getConnection()){
            
            Servicio servicio = new Servicio(con);
            
            servicio.eliminarVideo(idVideo);
            
            lista = servicio.todosLosVideos();
            
            request.setAttribute("lstVideos", lista);
            request.setAttribute("mensaje", mapMensaje);
            request.getRequestDispatcher("Consulta.jsp").forward(request, response);
            
        } catch (SQLException e) {
            
            throw new RuntimeException("error en la conexión bd",e);
        }

    }

}
