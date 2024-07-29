package model;

public class Produs {
    private int id;
    private String nume;
    private String descriere;
    private double pret;
    private int cantitateInStoc;

    public Produs(String nume, String descriere, double pret, int cantitateInStoc) {
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
        this.cantitateInStoc = cantitateInStoc;
    }

    // Getters și setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitateInStoc() {
        return cantitateInStoc;
    }

    public void setCantitateInStoc(int cantitateInStoc) {
        this.cantitateInStoc = cantitateInStoc;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", descriere='" + descriere + '\'' +
                ", pret=" + pret +
                ", cantitateInStoc=" + cantitateInStoc +
                '}';
    }
}
