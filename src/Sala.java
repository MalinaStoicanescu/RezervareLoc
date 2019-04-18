import database.DatabaseSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sala {
    private int id;
    private int nrLocuri;
    private List<Loc> locuri;

    public Sala(int id, int nrLocuri) {
        this.nrLocuri = nrLocuri;
        this.id = id;
        initializareSala();
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
            locuri.add(new Loc(0,this,true));
        }

        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "select * from " + DatabaseSQL.getDbName() + ".loc " +
                         "where idSala=" + this.id + ";";
            ResultSet rs = myStmt.executeQuery(sql);

            while (rs.next()) {
                int liber = rs.getInt("liber");
                int nrLoc = rs.getInt("nrLoc");
                int idLoc = rs.getInt("idLoc");
                Loc loc = new Loc(idLoc, this, liber==1);

                locuri.set(nrLoc, loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
