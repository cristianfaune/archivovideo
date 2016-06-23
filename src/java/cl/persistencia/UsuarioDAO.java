/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CristianFaune
 */
public class UsuarioDAO {
    
    Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }
    
    /**
     * Método que retorna un objeto de tipo usuario con todos sus datos
     * si es que el email utilizado como parámetro coincide con el registro de
     * la base de datos
     * @param usuarioEmail
     * @return 
     */
    public Usuario buscarUsuarioEmail(String usuarioEmail) {
        Usuario usuario = null;

        String sql = "select * from usuario where email = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, usuarioEmail);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    usuario = new Usuario();

                    usuario.setRut(rs.getString("rut"));
                    usuario.setNombres(rs.getString("nombres"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setIdCargo(rs.getString("idCargo"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de usuario por email");
        }

        return usuario;
    }
}
