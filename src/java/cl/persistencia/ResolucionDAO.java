/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Resolucion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class ResolucionDAO {
    Connection con;

    public ResolucionDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList<Resolucion> buscarTodasResolucion() {
        ArrayList<Resolucion> lista = new ArrayList<>();
        Resolucion resolucion;

        String sql = "select * from resolucion";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                resolucion = new Resolucion();

                resolucion.setIdResolucion(rs.getString(1));
                resolucion.setDescripcion(rs.getString(2));

                lista.add(resolucion);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todas las resoluciones", e);
        }

        return lista;
    }

    
}
