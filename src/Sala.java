import java.util.List;

public class Sala {
    private static int nrSali = 0;
    int id;
    int nrLocuri;
    List<Loc> locuri;
    Spectacol spectacol;

    public Sala(int nrLocuri, Spectacol spectacol) {
        this.nrLocuri = nrLocuri;
        this.spectacol = spectacol;
        initializareSala();

        nrSali++;
        id = nrSali;
    }

    public int getId() {
        return id;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public List<Loc> getLocuri() {
        return locuri;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }
    
    public void initializareSala()
    {
        for(int i = 1; i <= nrLocuri; i++)
        {
            locuri.add(new Loc(i,this,true));
        }
    }

    public void actualizeazaLoc(int id)
    {
        Loc loc = locuri.get(id);
        loc.liber = !loc.liber;
    }


}
