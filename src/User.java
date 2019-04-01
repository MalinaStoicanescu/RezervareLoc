public abstract class User {
    private static int nrUseri = 0;
    int id;
    String nume;
    String parola;

    public User(String nume, String parola) {
        this.nume = nume;
        this.parola = parola;

        nrUseri++;
        id = nrUseri;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getParola() {
        return parola;
    }

    public static int getNrUseri() {
        return nrUseri;
    }

    public static void setNrUseri(int nrUseri) {
        User.nrUseri = nrUseri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void adaugaRezervare(Rezervare rezervare);
    public abstract void stergeRezervare(Rezervare rezervare);
}
