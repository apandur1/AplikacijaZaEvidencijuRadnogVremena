package sample;

public class Osoba {
    private String ime, prezime, username;
    private int id;
//    private double datum;


    public Osoba(int id, String ime, String prezime, String username) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
