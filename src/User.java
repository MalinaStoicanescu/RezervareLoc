public abstract class User {
    private static int nrUseri = 0;
    int id;
    private String nume;
    private String parola;
    int isAdmin;

    public User(String nume, String parola) {
        this.nume = nume;
        this.parola = parola;

        nrUseri++;
        id = nrUseri;
    }

    public User(int id, String nume, String parola, int isAdmin) {
        this.id = id;
        this.nume = nume;
        this.parola = parola;
        this.isAdmin = isAdmin;
    }

    public int getIsAdmin() {
        return isAdmin;
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
