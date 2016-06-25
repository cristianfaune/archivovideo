/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Video;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

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
    public int numFilasVideo() {
        int cont = 0;

        String sql = "select * from video";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery();) {
                
                rs.last();
                cont = rs.getRow()+1;
                
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar mascota por ID", e);
        }

        return cont;
    }
    
    /**
     * Método que elimina un registro de video según su idVideo
     * @param idVideo 
     */
    public void eliminarVideo(String idVideo) {

        String sql = "delete from video where idVideo = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, idVideo);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar video", e);
        }
    }
    
    /**
     * Método que retorna un objeto de tipo Video consultando por su idVideo, 
     * si no se encuentra el registro devuelve un valor null
     * @param idVideo
     * @return 
     */
    public Video buscarVideoPorId(String idVideo) {
        Video video = null;

        String sql = "select * from video where idVideo = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, idVideo);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    video = new Video();

                    video.setIdVideo(rs.getString(1));
                    video.setNombre(rs.getString(2));
                    video.setFechaOrigen(rs.getDate(3));
                    video.setFechaSubida(rs.getTimestamp(4));
                    video.setDuracion(rs.getString(5));
                    video.setDescripcion(rs.getString(6));
                    video.setRutaArchivoVideo(rs.getString(7));
                    video.setIdCategoria(rs.getString(8));
                    video.setIdResolucion(rs.getString(9));
                    video.setRut(rs.getString(10));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar video por ID", e);
        }

        return video;
    }
    
    /**
     * Método que actualiza el registro de un video con un idVideo específico
     * @param video 
     */
    public void modificarVideo(Video video) {

        String sql = "update video set nombre = ?, "
                + "fechaOrigen = ?, duracion = ?, descripcion = ?, "
                + "idCategoria = ?, idResolucion = ? where idVideo = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, video.getNombre());
            pstmt.setDate(2, video.getFechaOrigen());
            pstmt.setString(3, video.getDuracion());
            pstmt.setString(4, video.getDescripcion());
            pstmt.setString(5, video.getIdCategoria());
            pstmt.setString(6, video.getIdResolucion());
            pstmt.setString(7, video.getIdVideo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar datos video", e);
        }
    }
}
