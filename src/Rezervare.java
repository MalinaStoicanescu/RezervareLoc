import java.util.List;

public class Rezervare {
    private static int nrRezervari=0;
    int id;
    Spectacol spectacol;
    Loc loc;
    User user;

    public Rezervare(int id, Spectacol spectacol, Loc loc, User user) {
        this.spectacol = spectacol;
        this.loc = loc;
        this.user = user;
        nrRezervari++;
        this.id = nrRezervari;
    }

    public static int getNrRezervari() {
        return nrRezervari;
    }

    public static void setNrRezervari(int nrRezervari) {
        Rezervare.nrRezervari = nrRezervari;
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

    public Loc getLoc() {
        return loc;
    }

    public void setLoc(Loc loc) {
        this.loc = loc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
