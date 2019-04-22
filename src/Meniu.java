import database.DatabaseSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Meniu extends JFrame implements ActionListener {
    private Service service = Service.getInstance();

    Container container = getContentPane();
    JButton butonRezervari = new JButton("REZERVARILE MELE");
    JButton butonRezervareNoua = new JButton("REZERVARE NOUA");
    JLabel mesajAlegeRezervare = new JLabel("Alegeti id-ul spectacolului dorit");
    JTextField idSpectacol = new JTextField();
    JButton butonEfectueazaRezervare = new JButton("ALEGE");
    List<JLabel> rezervari = new ArrayList<>();
    List<JLabel> listaSpectacole = new ArrayList<>();
    List<JButton> editStergereButoane = new ArrayList<>();
    List<JTextField> locuriTextFiels = new ArrayList<>();

    Meniu() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }
    public void setLayoutManager() {
        container.setLayout(null);

    }

    public void setLocationAndSize() {
        butonRezervari.setBounds(75,30,200,30);
        butonRezervareNoua.setBounds(75,70,200,30);
    }

    public void addComponentsToContainer() {
        container.add(butonRezervareNoua);
        container.add(butonRezervari);
    }

    public void addActionEvent() {
        butonRezervareNoua.addActionListener(this);
        butonRezervari.addActionListener(this );
        butonEfectueazaRezervare.addActionListener(this);

    }

    public void afisareRezervare(int idRezervare, String titlu, String data, int idLoc, int y) {
        JLabel rezervare = new JLabel(titlu + " : " + data);
        JTextField loc = new JTextField();
        loc.setText(String.valueOf(idLoc));
        rezervari.add(rezervare);
        rezervare.setBounds(20, y,200,30);
        loc.setBounds(202,y, 50,30);
        container.add(rezervare);
        container.add(loc);
        locuriTextFiels.add(loc);
        JButton butonEditare = new JButton("E");
        JButton butonStergere = new JButton("S");
        butonEditare.setBounds(255, y, 50, 30);
        butonStergere.setBounds(310, y, 50, 30);
        Meniu m = this;
        butonEditare.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int idLocNou = Integer.parseInt(loc.getText());
                System.out.println(idRezervare + " " + idLoc);
                boolean editareRealizata = service.editeazaRezervare(idLoc, idLocNou, idRezervare);
                if (editareRealizata)
                    showMessageDialog(m, "Editare realizata");
                else
                    showMessageDialog(m, "Alegeti alt loc :)");

            }
        });
        butonStergere.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                boolean stergereRealizata = service.stergeRezervare(idRezervare, idLoc);
                if (stergereRealizata) {
                    showMessageDialog(m,"Stergere realizata");
                    curataFereastraRezervari();
                    vizualizareRezervari();
                } else {
                    showMessageDialog(m, "A aparut o eroare");
                }

            }
        });
        editStergereButoane.add(butonEditare);
        editStergereButoane.add(butonStergere);
        container.add(butonEditare);
        container.add(butonStergere);
        container.repaint();
    }

    public void vizualizareRezervari() {
        Client client = service.getClientLogat();

        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
        try {
            Statement myStmt = databaseSQL.get_connection().createStatement();
            String sql = "select * from " + DatabaseSQL.getDbName() + ".rezervare r " +
                    "join spectacol s where r.idSpectacol = s.idspectacol " +
                    "and idClient=" + client.getId() +";";

            ResultSet rs = myStmt.executeQuery(sql);
            int y = 200;
            while (rs.next()) {
                int idRezervare = rs.getInt("idRezervare");
                int idSpectacol = rs.getInt("idspectacol");
                int idLoc = rs.getInt("idLoc");
                String titlu = rs.getString("titlu");
                Date data = rs.getDate("data");
                int durata = rs.getInt("durata");
                afisareRezervare(idRezervare, titlu, data.toString(), idLoc, y);
                y += 40;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void curataButoane() {
        for (JButton buton : editStergereButoane)
            container.remove(buton);
        editStergereButoane.clear();
        container.repaint();
    }

    private void curataLocuri() {
        for (JTextField loc : locuriTextFiels)
            container.remove(loc);
        locuriTextFiels.clear();
        container.repaint();
    }

    private void curataRezervari() {
        for (JLabel rez : rezervari) {
            container.remove(rez);
        }
        rezervari.clear();
        container.repaint();
    }

    private void curataRezervareNoua() {
        container.remove(idSpectacol);
        container.remove(butonEfectueazaRezervare);
        container.remove(mesajAlegeRezervare);
        curataSpectacole();
        container.repaint();
    }

    private void curataSpectacole() {
        for (JLabel spectacol : listaSpectacole) {
            container.remove(spectacol);
        }
        listaSpectacole.clear();
        container.repaint();
    }

    public void afisareMesajPentruRezervareNoua(int y) {

        mesajAlegeRezervare.setBounds(100,y,250,30);
        idSpectacol.setBounds(120,y + 40, 100, 30);
        butonEfectueazaRezervare.setBounds(120,y + 80, 100, 30);
        container.add(mesajAlegeRezervare);
        container.add(idSpectacol);
        container.add(butonEfectueazaRezervare);
        container.repaint();
    }


    private int citesteInt() {
        String linie = idSpectacol.getText();
        if(linie.matches("^\\d+$"))
            return Integer.parseInt(linie);
        else
            return 0;

    }


    public void adaugaRezervareNoua() {

        int id = citesteInt();
        Spectacol spectacol = service.cautaSpectacol(id);
        if (spectacol != null) {
            Loc loc = spectacol.getSala().gasesteLoc(id);
            if (loc != null) {
                Rezervare rezervare = new Rezervare(spectacol, loc, service.getClientLogat());
                service.getClientLogat().adaugaRezervare(rezervare);
                showMessageDialog(this, "Rezervare realizata cu succes");
            }
            else {
                showMessageDialog(this, "Introduceti un id valid");
            }
        } else {
            showMessageDialog(this, "Introduceti un id valid");
        }
    }


    public int vizualizareSpectacole() {
        List<Spectacol> spectacole = service.vizualizareSpectacole();
        int y = 100;
        for (Spectacol spectacol : spectacole) {
            JLabel sp = new JLabel(spectacol.getId()+ ". " + spectacol.getTitlu()+ " : " + spectacol.getData().toString());
            sp.setBounds(100,y,200,30);
            listaSpectacole.add(sp);
            container.add(sp);
            y += 40;
        }
        container.repaint();
        return y;
    }

    private void curataFereastraRezervari() {
        curataRezervari();
        curataButoane();
        curataLocuri();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == butonRezervari) {
            curataRezervari();
            curataRezervareNoua();
            vizualizareRezervari();
        }
        if(e.getSource()==butonRezervareNoua) {
            curataFereastraRezervari();
            int coordonataY = vizualizareSpectacole();
            afisareMesajPentruRezervareNoua(coordonataY);
        }
        if(e.getSource()==butonEfectueazaRezervare) {
            adaugaRezervareNoua();

        }

    }
}
