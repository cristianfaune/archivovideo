/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CristianFaune
 */
public class VideoDAO {
    
    Connection con;

    public VideoDAO(Connection con) {
        this.con = con;
    }
    
    /**
     * Método que registra un nuevo registro de video en la base de datos
     * @param video 
     */
    public void registrarVideo(Video video) {

        String sql = "insert into video values (?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, video.getIdVideo());
            pstmt.setString(2, video.getNombre());
            pstmt.setDate(3, video.getFechaOrigen());
            pstmt.setTimestamp(4, video.getFechaSubida());
            pstmt.setString(5, video.getDuracion());
            pstmt.setString(6, video.getDescripcion());
            pstmt.setString(7, video.getRutaArchivoVideo());
            pstmt.setString(8, video.getIdCategoria());
            pstmt.setString(9, video.getIdResolucion());
            pstmt.setString(10, video.getRut());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en el ingreso de nuevo video", e);
        }
    }
    
    /**
     * Método que retorna el número de fila de la tabla de video y le suma
     * 1, este valor se utiliza para generar parte del idVideo del nuevo archivo.
     * @return 
     */
    public int numeroFilaVideo() {
        int cont = 0;

        String sql = "select count(*)+1 from video";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {

                cont = rs.getRow()+1;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en número de fila");
        }

        return cont;
    }
    
}
