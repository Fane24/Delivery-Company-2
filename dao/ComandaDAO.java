package dao;

import model.Comanda;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComandaDAO {
    public void save(Comanda comanda) throws SQLException {
        String sql = "INSERT INTO Comanda (clientId, livratorId, detaliiProdus, cantitate, pret, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, comanda.getClientId());
            stmt.setInt(2, comanda.getLivratorId());
            stmt.setString(3, comanda.getDetaliiProdus());
            stmt.setInt(4, comanda.getCantitate());
            stmt.setDouble(5, comanda.getPret());
            stmt.setString(6, comanda.getStatus());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comanda.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Comanda getById(int id) throws SQLException {
        String sql = "SELECT * FROM Comanda WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int clientId = rs.getInt("clientId");
                    int livratorId = rs.getInt("livratorId");
                    String detaliiProdus = rs.getString("detaliiProdus");
                    int cantitate = rs.getInt("cantitate");
                    double pret = rs.getDouble("pret");
                    String status = rs.getString("status");
                    Comanda comanda = new Comanda(clientId, livratorId, detaliiProdus, cantitate, pret, status);
                    comanda.setId(id);
                    return comanda;
                }
            }
        }
        return null;
    }
}
