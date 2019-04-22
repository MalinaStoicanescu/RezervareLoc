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

    public Sala(int id, int nrLocuri, int idSpectacol) {
        this.nrLocuri = nrLocuri;
        this.id = id;
        initializareSala(idSpectacol);
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
    
    public void initializareSala(int idSpectacol)
    {
        locuri = new ArrayList<>();
        for(int i = 0; i <= nrLocuri; i++)
        {
            locuri.add(new Loc(i,this,true));
        }

        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "select * from " + DatabaseSQL.getDbName() + ".loc " +
                         "where idSpectacol=" + idSpectacol + ";";
            ResultSet rs = myStmt.executeQuery(sql);

            while (rs.next()) {
                int liber = rs.getInt("liber");
                int idLoc = rs.getInt("idLoc");
                Loc loc = new Loc(idLoc, this, liber==1);

                locuri.set(idLoc, loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void actualizeazaLoc(int id, int idSpectacol)
    {
        Loc loc = locuri.get(id);
        loc.setLiber(!loc.isLiber());

        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "insert into " + DatabaseSQL.getDbName() + ".loc (idLoc, idSpectacol, liber) values("+
                    id + "," + idSpectacol + ", " + loc.isLiber() + ");";

            myStmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Loc gasesteLoc(int idSpectacol) {
        for (int i = 1; i <= nrLocuri; i++)
        {
            if (locuri.get(i).isLiber()) {
                actualizeazaLoc(i, idSpectacol);
                return locuri.get(i);
            }
        }
        return null;
    }


}
