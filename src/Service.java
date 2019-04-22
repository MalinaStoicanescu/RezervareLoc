import com.mysql.cj.exceptions.DataReadException;
import database.DatabaseSQL;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service {
    private static Service instance = new Service();
    private ClientRepo clienti = new ClientRepo();
    private Client clientLogat = null;
    private List<Spectacol> spectacole = new ArrayList<>();

    private Service() {
    }

    public static Service getInstance() {
        return instance;
    }

    public Client getClientLogat() {
        return clientLogat;
    }

    public Spectacol cautaSpectacol(int id) {
        for (Spectacol spectacol : spectacole) {
            if (spectacol.getId() == id)
                return spectacol;
        }
        return null;
    }

    public List<Spectacol> vizualizareSpectacole() {
        spectacole.clear();
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "select * from " + DatabaseSQL.getDbName() + ".spectacol spec join " +
                    DatabaseSQL.getDbName() + ".sala sa where sa.idSala = spec.idSala;";
            ResultSet rs = myStmt.executeQuery(sql);

            while (rs.next()) {
                int idSpectacol = rs.getInt("idspectacol");
                String titlu = rs.getString("titlu");
                Date data = rs.getDate("data");
                int durata = rs.getInt("durata");

                int nrLocuriSala = rs.getInt("nrLocuri");
                int idSala = rs.getInt("idSala");
                Sala sala = new Sala(idSala, nrLocuriSala, idSpectacol);

                Spectacol spectacol = new Spectacol(idSpectacol, titlu, sala, data, durata);
                spectacole.add(spectacol);

                //System.out.println(spectacol);
            }
            return spectacole;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public boolean editeazaRezervare(int idLocVechi, int idLocNou, int idRezervare) {
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();

        try{
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "update " + DatabaseSQL.getDbName() + ".rezervare set idLoc = " + idLocNou + " where idRezervare=" +
                    idRezervare + ";";
            String sql2 = "update " + DatabaseSQL.getDbName() + ".loc set idLoc = " + idLocNou + " where idLoc= " +
                    idLocVechi + ";";
            myStmt.executeUpdate(sql);
            myStmt.executeUpdate(sql2);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean stergeRezervare(int idRezervare, int idLoc) {
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();

        try{
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "delete from " + DatabaseSQL.getDbName() + ".rezervare where idRezervare = " + idRezervare + ";";
            String sql2 = "delete from " + DatabaseSQL.getDbName() + ".loc where idLoc = " + idLoc + ";";
            myStmt.executeUpdate(sql);
            myStmt.executeUpdate(sql2);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void inregistreazaClientNou(String nume, String parola) {
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();

        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "insert into " + DatabaseSQL.getDbName() + ".user (nume, parola, isAdmin) " +
                    "values('" + nume + "','" + parola + "',0);";
            myStmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
