import database.DatabaseSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    private Service service = Service.getInstance();
    Container container = getContentPane();
    JLabel numeLabel = new JLabel("NUME:");
    JLabel parolaLabel = new JLabel("PAROLA");
    JTextField numeText = new JTextField();
    JTextField parolaText = new JTextField();
    JButton butonLogin = new JButton("LOGIN");
    JButton butonRegister = new JButton("REGISTER");


    Login()
    {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager() {
        container.setLayout(null);

    }

    public void setLocationAndSize() {
        numeLabel.setBounds(50,150,100,30);
        parolaLabel.setBounds(50,220,100,30);
        numeText.setBounds(150,150,150,30);
        parolaText.setBounds(150,220,150,30);
        butonLogin.setBounds(50,300,100,30);
        butonRegister.setBounds(200,300,100,30);
    }

    public void addComponentsToContainer() {
        container.add(numeLabel);
        container.add(parolaLabel);
        container.add(numeText);
        container.add(parolaText);
        container.add(butonLogin);
        container.add(butonRegister);
    }

    public void addActionEvent() {
        butonLogin.addActionListener(this);
        butonRegister.addActionListener(this );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == butonRegister) {
            String nume;
            String parola;
            nume = numeText.getText();
            parola = parolaText.getText();
            inregistreazaClientNou(nume, parola);
            JOptionPane.showMessageDialog(this,"User inregistrat! ^_^");
        }

        if(e.getSource() == butonLogin) {
            String nume = numeText.getText();
            String parola = parolaText.getText();
            if (verificareClient(nume, parola)){
                Meniu meniu = new Meniu();
                meniu.setTitle("Meniu");
                meniu.setVisible(true);
                meniu.setBounds(10,10,500,600);
                meniu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                JOptionPane.showMessageDialog(this,"Esuat!");
            }


        }

    }

    public boolean verificareClient(String nume, String parola) {
        if (service.verificareClient(nume, parola)==true) {
            return true;
        }
        else {
            System.out.print("Oops\n");
            return false;
        }
    }



    public void inregistreazaClientNou(String nume, String parola) {
        service.inregistreazaClientNou(nume, parola);
    }


}
