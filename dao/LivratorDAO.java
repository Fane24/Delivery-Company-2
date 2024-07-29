package dao;

import model.Livrator;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivratorDAO {
    public void save(Livrator livrator) throws SQLException {
        String sql = "INSERT INTO Livrator (nume, prenume, telefon, vehicul) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livrator.getNume());
            stmt.setString(2, livrator.getPrenume());
            stmt.setString(3, livrator.getTelefon());
            stmt.setString(4, livrator.getVehicul());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    livrator.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Livrator getById(int id) throws SQLException {
        String sql = "SELECT * FROM Livrator WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nume = rs.getString("nume");
                    String prenume = rs.getString("prenume");
                    String telefon = rs.getString("telefon");
                    String vehicul = rs.getString("vehicul");
                    Livrator livrator = new Livrator(nume, prenume, telefon, vehicul);
                    livrator.setId(id);
                    return livrator;
                }
            }
        }
        return null;
    }
}
