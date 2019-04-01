import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientRepo implements Repo<Client> {
    private List<Client> clienti = new ArrayList<>();

    @Override
    public void adauga(Client client){
        clienti.add(client);
    }

    @Override
    public Client get(int index){
        return clienti.get(index);
    }

    public Client verifica(Client client) {
        for (Client client1 : clienti) {
            if (client1.nume.equals(client.nume) && client1.parola.equals(client.parola))
                return client1;
        }
        return null;
    }

    @Override
    public void actualizeaza(Client client){
        //
    }

    @Override
    public void sterge(Client client){
        //
    }

    @Override
    public int obtineDimensiune(){
        return clienti.size();
    }
}
