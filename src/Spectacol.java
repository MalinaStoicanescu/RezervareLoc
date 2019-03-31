import java.util.Date;
import java.util.List;

public class Spectacol {
    private String titlu;
    private int nrLocuri;
    private Sala sala;
    Date data;

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Spectacol(String titlu, int nrLocuri, Sala sala, Date data, int durata) {
        this.titlu = titlu;
        this.nrLocuri = nrLocuri;
        this.sala = sala;
        this.data = data;
        this.durata = durata;
    }

    int durata;

}