package model;

public class Comanda {
    private int id;
    private int clientId;
    private int livratorId;
    private String detaliiProdus;
    private int cantitate;
    private double pret;
    private String status;

    public Comanda(int clientId, int livratorId, String detaliiProdus, int cantitate, double pret, String status) {
        this.clientId = clientId;
        this.livratorId = livratorId;
        this.detaliiProdus = detaliiProdus;
        this.cantitate = cantitate;
        this.pret = pret;
        this.status = status;
    }

    // Getters și setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLivratorId() {
        return livratorId;
    }

    public void setLivratorId(int livratorId) {
        this.livratorId = livratorId;
    }

    public String getDetaliiProdus() {
        return detaliiProdus;
    }

    public void setDetaliiProdus(String detaliiProdus) {
        this.detaliiProdus = detaliiProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", livratorId=" + livratorId +
                ", detaliiProdus='" + detaliiProdus + '\'' +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                ", status='" + status + '\'' +
                '}';
    }
}
