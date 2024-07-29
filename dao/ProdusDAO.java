package dao;

import model.Produs;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdusDAO {
    public void save(Produs produs) throws SQLException {
        String sql = "INSERT INTO Produs (nume, descriere, pret, cantitateInStoc) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produs.getNume());
            stmt.setString(2, produs.getDescriere());
            stmt.setDouble(3, produs.getPret());
            stmt.setInt(4, produs.getCantitateInStoc());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produs.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Produs> getAll() throws SQLException {
        List<Produs> produse = new ArrayList<>();
        String sql = "SELECT * FROM Produs";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String descriere = rs.getString("descriere");
                double pret = rs.getDouble("pret");
                int cantitateInStoc = rs.getInt("cantitateInStoc");
                Produs produs = new Produs(nume, descriere, pret, cantitateInStoc);
                produs.setId(id);
                produse.add(produs);
            }
        }
        return produse;
    }
}
