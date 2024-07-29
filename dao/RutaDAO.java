package dao;

import model.Ruta;
import model.Livrator;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class RutaDAO {
    public void save(Ruta ruta) throws SQLException {
        String sql = "INSERT INTO Ruta (trasee, kilometrii, livrator_id, opriri) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, String.join(",", ruta.getTrasee()));
            stmt.setInt(2, ruta.getKilometrii());
            stmt.setInt(3, ruta.getLivrator().getId());
            stmt.setString(4, String.join(",", ruta.getOpriri()));
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ruta.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Ruta getById(int id) throws SQLException {
        String sql = "SELECT * FROM Ruta WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    List<String> trasee = Arrays.asList(rs.getString("trasee").split(","));
                    int kilometrii = rs.getInt("kilometrii");
                    Livrator livrator = new LivratorDAO().getById(rs.getInt("livrator_id"));
                    List<String> opriri = Arrays.asList(rs.getString("opriri").split(","));
                    Ruta ruta = new Ruta(trasee, kilometrii, livrator, opriri);
                    ruta.setId(id);
                    return ruta;
                }
            }
        }
        return null;
    }
}
