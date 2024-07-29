package model;

import java.util.List;

public class Ruta {
    private int id;
    private List<String> trasee;
    private int kilometrii;
    private Livrator livrator;
    private List<String> opriri;

    public Ruta(List<String> trasee, int kilometrii, Livrator livrator, List<String> opriri) {
        this.trasee = trasee;
        this.kilometrii = kilometrii;
        this.livrator = livrator;
        this.opriri = opriri;
    }

    // Getters și setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTrasee() {
        return trasee;
    }

    public void setTrasee(List<String> trasee) {
        this.trasee = trasee;
    }

    public int getKilometrii() {
        return kilometrii;
    }

    public void setKilometrii(int kilometrii) {
        this.kilometrii = kilometrii;
    }

    public Livrator getLivrator() {
        return livrator;
    }

    public void setLivrator(Livrator livrator) {
        this.livrator = livrator;
    }

    public List<String> getOpriri() {
        return opriri;
    }

    public void setOpriri(List<String> opriri) {
        this.opriri = opriri;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "id=" + id +
                ", trasee=" + trasee +
                ", kilometrii=" + kilometrii +
                ", livrator=" + livrator +
                ", opriri=" + opriri +
                '}';
    }
}
