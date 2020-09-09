package sample;

public class Radnik extends Osoba {
    private String manager;

    public Radnik(int id, String ime, String prezime, String username, String manager1) {
        super(id, prezime, username, ime);
        this.manager = manager1;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
