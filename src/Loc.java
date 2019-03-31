public class Loc {
    int id;
    Sala sala;
    boolean liber;

    public Loc(int id, Sala sala, boolean liber) {
        this.id = id;
        this.sala = sala;
        this.liber = liber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public boolean isLiber() {
        return liber;
    }

    public void setLiber(boolean liber) {
        this.liber = liber;
    }
}


