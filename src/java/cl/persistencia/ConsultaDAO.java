/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Categoria;
import cl.dominio.Resolucion;
import cl.dominio.Video;
import cl.dto.VideoCatResDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class ConsultaDAO {
    
    Connection con;

    public ConsultaDAO(Connection con) {
        this.con = con;
    }
    
    /**
     * Método que retorna una lista con todos los registros de archivos de video
     * en la base de datos, usa un join para traer otros datos de tablas relacionadas
     * @return 
     */
    public ArrayList<VideoCatResDTO> todosLosVideos() {
        ArrayList<VideoCatResDTO> lista = new ArrayList<>();
        Video video;
        Categoria categoria;
        Resolucion resolucion;

        String sql = "SELECT * from video v "
                + "join categoria c on v.idCategoria = c.idCategoria "
                + "join resolucion r on v.idResolucion = r.idResolucion";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {


            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
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

                    categoria = new Categoria();

                    categoria.setIdCategoria(rs.getString(11));
                    categoria.setDescripcion(rs.getString(12));

                    resolucion = new Resolucion();

                    resolucion.setIdResolucion(rs.getString(13));
                    resolucion.setDescripcion(rs.getString(14));

                    lista.add(new VideoCatResDTO(video,categoria,resolucion));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la consulta de todos los videos", e);
        }

        return lista;
    }
}
