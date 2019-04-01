import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Client extends User {

    SortedSet<Rezervare> rezervari;

    public Client(String nume, String parola) {
        super(nume, parola);
        rezervari = new TreeSet<>(Comparator.comparing(Rezervare::getId));

    }

    public SortedSet<Rezervare> getRezervari() {
        return rezervari;
    }

    public void setRezervari(SortedSet<Rezervare> rezervari) {
        this.rezervari = rezervari;
    }

    @Override
    public void adaugaRezervare(Rezervare rezervare){
        if (rezervare.getClient().getId() == this.getId()){
            rezervari.add(rezervare);
        }
    }

    @Override
    public void stergeRezervare(Rezervare rezervare){
        if (rezervare.getClient().getId() == this.getId()) {
            rezervari.remove(rezervare);
        }
    }
}
