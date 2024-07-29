package util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.sql.*;
import java.util.*;

public class GenerateReport {
    public static void generateLivratoriPdfReport() {
        try {
            // Configurare FreeMarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDirectoryForTemplateLoading(new File("src/templates"));
            cfg.setDefaultEncoding("UTF-8");

            // Încarcă template-ul
            Template template = cfg.getTemplate("livratori_report_template.ftl");

            // Creează datele pentru raport din baza de date
            List<Map<String, Object>> livratori = getLivratoriFromDatabase();
            Map<String, Object> data = new HashMap<>();
            data.put("livratori", livratori);

            // Generează conținutul HTML folosind template-ul și datele
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            String htmlContent = writer.toString();

            // Creează PDF-ul din conținutul HTML
            try (OutputStream os = new FileOutputStream("Raport_Livratori.pdf")) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(os);
            }

            System.out.println("Raport generat cu succes!");

        } catch (IOException | TemplateException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, Object>> getLivratoriFromDatabase() throws SQLException {
        List<Map<String, Object>> livratori = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Firma_de_Curierat";
        String user = "root";
        String password = "Faneparola1.";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT l.id, l.nume, COUNT(c.id) AS numar_curse, GROUP_CONCAT(r.traseu SEPARATOR ', ') AS rute " +
                             "FROM Livrator l " +
                             "LEFT JOIN Comanda c ON l.id = c.livrator_id " +
                             "LEFT JOIN Ruta r ON c.ruta_id = r.id " +
                             "GROUP BY l.id, l.nume")) {

            while (rs.next()) {
                Map<String, Object> livrator = new HashMap<>();
                livrator.put("id", rs.getInt("id"));
                livrator.put("nume", rs.getString("nume"));
                livrator.put("numar_curse", rs.getInt("numar_curse"));
                livrator.put("rute", rs.getString("rute"));
                livratori.add(livrator);
            }
        }
        return livratori;
    }

    public static void generateClientPdfReport() {
        try {
            // Configurare FreeMarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDirectoryForTemplateLoading(new File("src/templates"));
            cfg.setDefaultEncoding("UTF-8");

            // Încarcă template-ul
            Template template = cfg.getTemplate("client_report_template.ftl");

            // Creează datele pentru raport din baza de date
            List<Map<String, Object>> clienti = getClientiFromDatabase();
            Map<String, Object> data = new HashMap<>();
            data.put("clienti", clienti);

            // Generează conținutul HTML folosind template-ul și datele
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            String htmlContent = writer.toString();

            // Creează PDF-ul din conținutul HTML
            try (OutputStream os = new FileOutputStream("Raport_Clienti.pdf")) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(os);
            }

            System.out.println("Raport generat cu succes!");

        } catch (IOException | TemplateException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, Object>> getClientiFromDatabase() throws SQLException {
        List<Map<String, Object>> clienti = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Firma_de_Curierat";
        String user = "root";
        String password = "Faneparola1.";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Client")) {

            while (rs.next()) {
                Map<String, Object> client = new HashMap<>();
                client.put("id", rs.getInt("id"));
                client.put("nume", rs.getString("nume"));
                client.put("adresa", rs.getString("adresa"));
                client.put("telefon", rs.getString("telefon"));
                client.put("email", rs.getString("email"));
                clienti.add(client);
            }
        }
        return clienti;
    }

    public static void generatePdfReport() {
        try {
            // Configurare FreeMarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDirectoryForTemplateLoading(new File("src/templates"));
            cfg.setDefaultEncoding("UTF-8");

            // Încarcă template-ul
            Template template = cfg.getTemplate("report_template.ftl");

            // Creează datele pentru raport din baza de date
            List<Map<String, Object>> comenzi = getComenziFromDatabase();
            Map<String, Object> data = new HashMap<>();
            data.put("comenzi", comenzi);

            // Generează conținutul HTML folosind template-ul și datele
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            String htmlContent = writer.toString();

            // Creează PDF-ul din conținutul HTML
            try (OutputStream os = new FileOutputStream("Raport_Comanda.pdf")) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(os);
            }

            System.out.println("Raport generat cu succes!");

        } catch (IOException | TemplateException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, Object>> getComenziFromDatabase() throws SQLException {
        List<Map<String, Object>> comenzi = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Firma_de_Curierat";
        String user = "root";
        String password = "Faneparola1.";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Comanda")) {

            while (rs.next()) {
                Map<String, Object> comanda = new HashMap<>();
                comanda.put("id", rs.getInt("id"));
                comanda.put("nume_client", rs.getString("nume_client"));
                comanda.put("nume_livrator", rs.getString("nume_livrator"));
                comanda.put("detalii_produs", rs.getString("detalii_produs"));
                comanda.put("cantitate", rs.getInt("cantitate"));
                comanda.put("pret", rs.getDouble("pret"));
                comanda.put("status_comanda", rs.getString("status_comanda"));
                comenzi.add(comanda);
            }
        }
        return comenzi;
    }
}
