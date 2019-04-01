import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service {
    private ClientRepo clienti = new ClientRepo();
    private Client clientLogat = null;
    private List<Spectacol> spectacole = new ArrayList<>();

    public Service() {
        spectacole.add(new Spectacol("Romeo si Julieta", new Sala(50), new Date(), 120));
        spectacole.add(new Spectacol("Frumoasa si Bestia", new Sala(60), new Date(),100));
    }

    public Client getClientLogat() {
        return clientLogat;
    }

    public Spectacol cautaSpectacol(int id){
        for (Spectacol spectacol: spectacole) {
            if (spectacol.getId() == id)
                return spectacol;
        }
        return null;

    }

    public void vizualizareSpectacole(){
        for(Spectacol spectacol: spectacole) {
            System.out.println(spectacol);
        }
    }

    public void inregistreazaClientNou(String nume, String parola){
        Client client = new Client(nume, parola);
        clienti.adauga(client);
    }

    public boolean suntLogat() {
        return clientLogat != null;
    }

    public void delogare(){
        clientLogat = null;
    }

    public boolean verificareClient(String nume, String parola) {
        Client client = new Client(nume, parola);
        clientLogat = clienti.verifica(client);
        if (clientLogat != null)
        {

            return true;
        }
        return false;
    }


}
