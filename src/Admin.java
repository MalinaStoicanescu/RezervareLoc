public class Admin extends User {


    enum Permisiuni{
        EDITARE,
        STERGERE,
        ADAUGARE,
        FULLOPTION
    }

    public Admin(String nume, String parola) {
        super(nume, parola);
    }

    @Override
    public void adaugaRezervare(Rezervare rezervare){
        rezervare.getUser().adaugaRezervare(rezervare);

    }

    @Override
    public void stergeRezervare(Rezervare rezervare){
        rezervare.getUser().stergeRezervare(rezervare);
    }
}
