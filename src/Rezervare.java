public class Rezervare {
    private static int nrRezervari=0;
    private int id;
    private Spectacol spectacol;
    private Client client;
    private Loc loc;


    public Rezervare( Spectacol spectacol,Loc loc, Client client) {
        this.spectacol = spectacol;
        this.loc = loc;
        this.client = client;
        nrRezervari++;
        this.id = nrRezervari;
    }

    public static int getNrRezervari() {
        return nrRezervari;
    }

    public static void setNrRezervari(int nrRezervari) {
        Rezervare.nrRezervari = nrRezervari;
    }

    public Loc getLoc() {
        return loc;
    }

    public void setLoc(Loc loc) {
        this.loc = loc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
