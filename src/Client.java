import database.DatabaseSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class Client extends User {

    SortedSet<Rezervare> rezervari;

    public Client(int id, String nume, String parola, int isAdmin) {
        super(id, nume, parola, isAdmin);
        rezervari = new TreeSet<>(Comparator.comparing(Rezervare::getId));
    }

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
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "insert into " + DatabaseSQL.getDbName() + ".rezervare (idLoc, idClient, idSpectacol) values('"+
                  rezervare.getLoc().getId() + "','" + rezervare.getClient().getId() + "', '" + rezervare.getSpectacol().getId() + "');";

            myStmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stergeRezervare(Rezervare rezervare){
        if (rezervare.getClient().getId() == this.getId()) {
            rezervari.remove(rezervare);
        }
    }
}
