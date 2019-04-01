import java.util.Scanner;

public class Vizual {
    private Scanner s = new Scanner(System.in);
    private Service service = new Service();

    public static void main(String[] args) {
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
    }



    private int citesteOptiune(){
            int optiune = citesteInt();
            if (optiune >= 1 && optiune <=3){
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

    private void vizualizareRezervari() {
        Client client = service.getClientLogat();
        for (Rezervare rezervare: client.getRezervari()) {
            System.out.println(rezervare.getSpectacol() + " - " + rezervare.getLoc().getId());
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
            Loc loc = spectacol.getSala().gasesteLoc();
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
