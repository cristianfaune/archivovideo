
package cl.controlador;

import cl.dto.VideoCatResDTO;
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
@WebServlet(name = "ConsultaServlet", urlPatterns = {"/ConsultaServlet"})
public class ConsultaServlet extends HttpServlet {
    
    @Resource(mappedName = "jdbc/archivovideo")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<VideoCatResDTO> lista = new ArrayList<>();
        
        try (Connection con = ds.getConnection()){
            
            Servicio servicio = new Servicio(con);
            
            lista = servicio.todosLosVideos();
            
            request.setAttribute("lstVideos", lista);
            request.getRequestDispatcher("Consulta.jsp").forward(request, response);
            
            
        } catch (SQLException e) {
            throw new RuntimeException("error en la conexión bd",e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
