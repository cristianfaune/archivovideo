/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class CategoriaDAO {
    
    Connection con;

    public CategoriaDAO(Connection con) {
        this.con = con;
    }
    
    /**
     * Método que retorna todos los registros de categorías desde 
     * la base de datos
     * @return 
     */
    public ArrayList<Categoria> buscarTodasCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        Categoria categoria;

        String sql = "select * from categoria";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                categoria = new Categoria();

                categoria.setIdCategoria(rs.getString(1));
                categoria.setDescripcion(rs.getString(2));

                lista.add(categoria);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todas las categorias", e);
        }

        return lista;
    }

}
