import java.util.ArrayList;
import java.util.List;

public class Sala {
    private static int nrSali = 0;
    private int id;
    private int nrLocuri;
    private List<Loc> locuri;

    public Sala(int nrLocuri) {
        this.nrLocuri = nrLocuri;
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
    
    public void initializareSala()
    {
        locuri = new ArrayList<>();
        for(int i = 1; i <= nrLocuri; i++)
        {
            locuri.add(new Loc(i,this,true));
        }
    }

    public void actualizeazaLoc(int id)
    {
        Loc loc = locuri.get(id);
        loc.setLiber(!loc.isLiber());
    }

    public Loc gasesteLoc() {
        for (int i = 0; i < nrLocuri; i++)
        {
            if (locuri.get(i).isLiber()) {
                actualizeazaLoc(i);
                return locuri.get(i);
            }
        }
        return null;
    }


}
