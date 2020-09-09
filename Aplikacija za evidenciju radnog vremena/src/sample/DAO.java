package sample;

import javafx.fxml.FXML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {
    private static DAO instance;
    private Connection conn;
    private int logovani;

    public int getLogovani() {
        return logovani;
    }

    public void setLogovani(int logovani) {
        this.logovani = logovani;
    }

    private static void initialize() {
        instance = new DAO();
    }

    public Connection getConn() {
        return conn;
    }

    public static DAO getInstance(){
        if(instance==null){
            instance=new DAO();
        }
        return instance;
    }

    private PreparedStatement odrediIdRadnikaUpit, dajDirektoreUpit, dajRadnikeUpit, jeLiRegistrovanUpit,
            dodajRadnikaUpit, obrisiRadnikaUpit, dajSifruUpit, dajManagerIdUpit, dajSveUpit, dajImeZaIdUpit,
            dajIdZaUsernameUpit;

    private DAO(){
        try {
            conn= DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dajDirektoreUpit = conn.prepareStatement("select id, ime, prezime, username, manager_id from radnik where manager_id is null");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                dajDirektoreUpit = conn.prepareStatement("select * from radnik where manager_id is null");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            dajRadnikeUpit = conn.prepareStatement("select * from radnik where manager_id is not null");
            jeLiRegistrovanUpit = conn.prepareStatement("select * from radnik where username = ?");
            dodajRadnikaUpit = conn.prepareStatement("insert into radnik values(?,?,?,?,?,?)");
            obrisiRadnikaUpit = conn.prepareStatement("delete from radnik where id = ?");
            odrediIdRadnikaUpit=conn.prepareStatement("SELECT Max(id)+1 FROM radnik");
            dajSifruUpit = conn.prepareStatement("select password from radnik where username = ?");
            dajManagerIdUpit = conn.prepareStatement("Select manager_id from radnik where username = ? and manager_id is not null");
            dajSveUpit = conn.prepareStatement("select id, ime, prezime, username, manager_id from radnik");
            dajImeZaIdUpit = conn.prepareStatement("select ime from radnik where id = ?");
            dajIdZaUsernameUpit = conn.prepareStatement("select id from radnik where username = ?");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance=null;
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("/home/amelpandur/IdeaProjects/Aplikacija za evidenciju radnog vremena/src/sample/baza.sql"));
            String sqlUpit="";
            while(ulaz.hasNext()){
                sqlUpit+=ulaz.nextLine();
                if( sqlUpit.charAt(sqlUpit.length()-1)==';'){
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit="";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int dajSljedeciId() {
        ResultSet rs;
        try {
            rs = odrediIdRadnikaUpit.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void dodajRadnika(String ime, String prezime, String username, String password, int manager_id) {
        try {
            dodajRadnikaUpit.setInt(1, dajSljedeciId());
            dodajRadnikaUpit.setString(2, ime);
            dodajRadnikaUpit.setString(3, prezime);
            dodajRadnikaUpit.setString(4, username);
            dodajRadnikaUpit.setString(5, password);
            dodajRadnikaUpit.setInt(6, manager_id);
            dodajRadnikaUpit.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean daLiJeRegistrovan(String username) {
        ResultSet rs;
        try {
            jeLiRegistrovanUpit.setString(1,username);
            rs = jeLiRegistrovanUpit.executeQuery();
            if(rs.next())
                return true;
            else
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String dajSifru(String username) {
        ResultSet rs;
        try {
            dajSifruUpit.setString(1,username);
            rs = dajSifruUpit.executeQuery();
            if(!rs.next())
                return null;
            return rs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean jeLiRadnik(String username) {
        ResultSet rs;
        try {
            dajManagerIdUpit.setString(1,username);
            rs = dajManagerIdUpit.executeQuery();
            if(rs.next())
                return true;
            else return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private String dajImeZaId(int id) {
        ResultSet rs;
        try {
            dajImeZaIdUpit.setInt(1, id);
            rs = dajImeZaIdUpit.executeQuery();
            if(!rs.next())
                return null;
            return rs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<Radnik> radnici() {
        ArrayList<Radnik> radni = new ArrayList();
        ResultSet rs;
        try {
            rs = dajSveUpit.executeQuery();
            while(rs.next()) {
                Radnik r = new Radnik(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), dajImeZaId(rs.getInt(5)));
//                r.setId(rs.getInt(1));
//                r.setIme(rs.getString(2));
//                r.setPrezime(rs.getString(3));
//                r.setUsername(rs.getString(4));
//                r.setManager(dajImeZaId(rs.getInt(5)));
                radni.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return radni;
    }

    public int dajIdZaUsername(String username) {
        ResultSet rs;
        try {
            dajIdZaUsernameUpit.setString(1, username);
            rs = dajIdZaUsernameUpit.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void obrisiRadnika(Radnik r) {
        try {
            obrisiRadnikaUpit.setInt(1, r.getId());
            obrisiRadnikaUpit.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
