import database.DatabaseSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "select * from " + DatabaseSQL.getDbName() + ".user " +
                          "where nume='" + client.getNume() + "' and parola='" + client.getParola() + "';";
            ResultSet rs = myStmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("nume"));
                String nume = rs.getString("nume");
                String parola = rs.getString("parola");
                int isAdmin = rs.getInt("isAdmin");
                int id = rs.getInt("idUser");

                return new Client(id, nume, parola, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
