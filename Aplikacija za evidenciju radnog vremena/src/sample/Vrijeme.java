package sample;

public class Vrijeme {
    int id, radnik_id;
    String dolazak, odlazak, datum;

    public Vrijeme(int id, int radnik_id, String dolazak, String odlazak, String datum) {
        this.id = id;
        this.radnik_id = radnik_id;
        this.dolazak = dolazak;
        this.odlazak = odlazak;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRadnik_id() {
        return radnik_id;
    }

    public void setRadnik_id(int radnik_id) {
        this.radnik_id = radnik_id;
    }

    public String getDolazak() {
        return dolazak;
    }

    public void setDolazak(String dolazak) {
        this.dolazak = dolazak;
    }

    public String getOdlazak() {
        return odlazak;
    }

    public void setOdlazak(String odlazak) {
        this.odlazak = odlazak;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
