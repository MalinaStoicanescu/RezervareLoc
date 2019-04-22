import database.DatabaseSQL;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Vizual {
    private Scanner s = new Scanner(System.in);
    private Service service = Service.getInstance();

    public static void main(String[] args) {
        Login frame = new Login();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vizual aplicatie = new Vizual();

        while(true){
            if (aplicatie.service.suntLogat())
                aplicatie.afiseazaMeniuUser();
            else {
                aplicatie.afiseazaMeniu();
            }
            int optiune = aplicatie.citesteOptiune();
            aplicatie.executa(optiune);

        }
    }

    private void afiseazaMeniu(){
        System.out.println("Apasa cifra corespunzatoare actiunii dorite:");
        System.out.println("1. Creeaza user nou");
        System.out.println("2. Autentificare");
        System.out.println("3. Alege un spectacol pentru a rezerva locul");

    }

    private void afiseazaMeniuUser(){
        System.out.println("Apasa cifra corespunzatoare actiunii dorite:");
        System.out.println("1. Vizualizeaza rezervarile");
        System.out.println("2. Alege un spectacol");
        System.out.println("3. Deconecteaza-te");
        if (service.getClientLogat().getIsAdmin()==1) {
            System.out.println("4. Adauga un spectacol");
        }
        if (service.getClientLogat().getIsAdmin()==1) {
            System.out.println("5. Adauga o sala");
        }
    }



    private int citesteOptiune(){
            int optiune = citesteInt();
            if (optiune >= 1 && optiune <=5){
                return optiune;

            }
            else{
                System.out.println("Introdu o cifra valida");
                return citesteOptiune();
            }

    }
    private int citesteInt() {
        String linie = s.nextLine();
        if(linie.matches("^\\d+$"))
            return Integer.parseInt(linie);
        else
            return 0;

    }


    private void executa(int optiune){
        if (service.suntLogat()) {
            switch (optiune) {
                case 1:
                    vizualizareRezervari();
                    break;
                case 2:
                    alegeSpectacol();
                    break;
                case 3:
                    deconecteazaClient();
                    break;
                case 4:
                    if (service.getClientLogat().getIsAdmin()==1) {
                        adaugaSpectacol();
                        break;
                    }
                case 5:
                    if (service.getClientLogat().getIsAdmin()==1) {
                        adaugaSala();
                        break;
                    }
            }
        }
        else {
            switch (optiune) {
                case 1:
                    adaugaUser();
                    break;
                case 2:
                    autentificare();
                    break;

            }
        }
    }

    private void adaugaSala() {
        System.out.print("Nr Locuri: ");
        int nrLocuri = Integer.parseInt(s.nextLine());
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "insert into " + DatabaseSQL.getDbName() + ".sala (nrLocuri) " +
                    "values(" + nrLocuri + ");";
            myStmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adaugaSpectacol() {
        System.out.print("Id Sala: ");
        int idSala = Integer.parseInt(s.nextLine());
        System.out.print("Titlu: ");
        String titlu = s.nextLine();
        System.out.print("Durata: ");
        int durata = Integer.parseInt(s.nextLine());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "insert into " + DatabaseSQL.getDbName() + ".spectacol (idSala, titlu, data, durata) " +
                    "values("+idSala+",'"+titlu + "','" + formatter.format(new Date()) + "'," + durata + ");";
            myStmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void vizualizareRezervari() {
        Client client = service.getClientLogat();

        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "select * from " + DatabaseSQL.getDbName() + ".rezervare r " +
                    "join spectacol s where r.idSpectacol = s.idspectacol " +
                    "and idClient=" + client.getId() + ";";

            ResultSet rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                int idRezervare = rs.getInt("idRezervare");
                int idSpectacol = rs.getInt("idspectacol");
                String titlu = rs.getString("titlu");
                Date data = rs.getDate("data");
                int durata = rs.getInt("durata");

                System.out.println(titlu + " - " + data.toString());
                //System.out.println(rezervare.getSpectacol() + " - " + rezervare.getLoc().getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void adaugaUser(){
        System.out.print("Nume: ");
        String nume = s.nextLine();
        System.out.print("Parola: ");
        String parola = s.nextLine();
        service.inregistreazaClientNou(nume, parola);
        //1autentificare();

    }

    private void autentificare(){
        System.out.print("Autentificare");
        System.out.print("Nume: ");
        String nume = s.nextLine();
        System.out.print("Parola: ");
        String parola = s.nextLine();
        if (service.verificareClient(nume, parola)==true) {
            System.out.print("Autentificare realizata cu succes\n");

        }
        else {
            System.out.print("Oops\n");
            //adaugaUser();

        }

    }

    private void deconecteazaClient(){
        service.delogare();

    }

    private void alegeSpectacol() {
        service.vizualizareSpectacole();
        int optiune = citesteInt();

        Spectacol spectacol = service.cautaSpectacol(optiune);
        if (spectacol != null) {
            Loc loc = spectacol.getSala().gasesteLoc(spectacol.getId());
            if (loc != null) {
                Rezervare rezervare = new Rezervare(spectacol, loc, service.getClientLogat());
                service.getClientLogat().adaugaRezervare(rezervare);
                System.out.println("Rezervare realizata cu succes!");
                return;
            }
        }
        System.out.println("Rezervare esuata");
    }

}
