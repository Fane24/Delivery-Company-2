package dao;

import model.Client;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {
    public void save(Client client) throws SQLException {
        String sql = "INSERT INTO Client (nume, adresa, nrTelefon, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getNume());
            stmt.setString(2, client.getAdresa());
            stmt.setString(3, client.getNrTelefon());
            stmt.setString(4, client.getEmail());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Client getById(int id) throws SQLException {
        String sql = "SELECT * FROM Client WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nume = rs.getString("nume");
                    String adresa = rs.getString("adresa");
                    String nrTelefon = rs.getString("nrTelefon");
                    String email = rs.getString("email");
                    Client client = new Client(nume, adresa, nrTelefon, email);
                    client.setId(id);
                    return client;
                }
            }
        }
        return null;
    }
}
