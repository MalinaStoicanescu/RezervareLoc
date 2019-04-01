import java.util.Date;
import java.util.List;

public class Spectacol {
    private static int nrSpectacole = 0;
    private int id;

    private String titlu;
    private int nrLocuri;
    private Sala sala;
    Date data;
    int durata;

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
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

    public Spectacol(String titlu, Sala sala, Date data, int durata) {
        this.titlu = titlu;
        this.sala = sala;
        this.data = data;
        this.durata = durata;
        nrSpectacole ++;
        id = nrSpectacole;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getId()+". "+getTitlu() + " : " + getData();
    }






}