public interface Repo<T> {
    public void adauga (T obiect);
    public T get(int id);
    public void actualizeaza(T obiect);
    public void sterge(T obiect);
    public int obtineDimensiune();
    public T verifica(T obiect);

}
