/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Usuario;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "ValidarIngresoServlet", urlPatterns = {"/ValidarIngresoServlet"})
public class ValidarIngresoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/archivovideo")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String, String> mapMensajeEmail = new HashMap<>();
        Map<String, String> mapMensajePass = new HashMap<>();
        HttpSession session = request.getSession();
        Usuario usuario = null;

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);
            
            usuario = servicio.buscarUsuarioEmail(email);

            if (email.isEmpty() || email == null) {
                mapMensajeEmail.put("errorEmail", "Debe ingresar su email");
            } else if (usuario == null) {
                mapMensajeEmail.put("errorEmail", "El usuario con email " + email + " no existe");
            }

            if (password.isEmpty() || password == null) {
                mapMensajePass.put("errorPass", "Debe ingresar su PASSWORD");
            } else if (usuario != null) {
                if (!usuario.getPassword().equals(password)) {
                    mapMensajePass.put("errorPass", "Su password no coincide con el registro");
                }
            }

            if (mapMensajePass.isEmpty() && mapMensajeEmail.isEmpty()) {

                session.setAttribute("usuarioSesion", usuario);
                request.getRequestDispatcher("/Menu.jsp").forward(request, response);

            } else {
                request.setAttribute("mapMensajePass", mapMensajePass);
                request.setAttribute("mapMensajeEmail", mapMensajeEmail);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("error en la conexion", e);
        }

    }

}
