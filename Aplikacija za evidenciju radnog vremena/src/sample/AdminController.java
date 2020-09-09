package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController {
    public TableView<Radnik> tableView;
    public TableColumn<Radnik, Integer> colId;
    public TableColumn<Radnik, String> colIme;
    public TableColumn<Radnik, String> colPrezime;
    public TableColumn<Radnik, String> colUsername;
    public TableColumn<Radnik, Radnik> colManager;
    private ObservableList<Radnik> radnici = FXCollections.observableArrayList();
    private DAO dao = DAO.getInstance();
    public TextField unosIme, unosPrezime, unosUsername, unosPassword;

    @FXML
    public void initialize()  {
        radnici.removeAll(radnici);
        for(Radnik g: dao.radnici()) {
            radnici.add(g);
        }
        tableView.setItems(radnici);
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colIme.setCellValueFactory(new PropertyValueFactory("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory("prezime"));
        colUsername.setCellValueFactory(new PropertyValueFactory("username"));
        colManager.setCellValueFactory(new PropertyValueFactory<Radnik, Radnik>("manager"));
        // tableViewGradovi.setItems(gradovi);
    }

    public TextField getUnosIme() {
        return unosIme;
    }

    public void setUnosIme(TextField unosIme) {
        this.unosIme = unosIme;
    }

    public TextField getUnosPrezime() {
        return unosPrezime;
    }

    public void setUnosPrezime(TextField unosPrezime) {
        this.unosPrezime = unosPrezime;
    }

    public TextField getUnosUsername() {
        return unosUsername;
    }

    public void setUnosUsername(TextField unosUsername) {
        this.unosUsername = unosUsername;
    }

    public TextField getUnosPassword() {
        return unosPassword;
    }

    public void setUnosPassword(TextField unosPassword) {
        this.unosPassword = unosPassword;
    }

    private boolean jeLiPrazan(String x) {
        if(x.length()!=0)
            return false;
        else
            return true;
    }

    public void unesiBtn(ActionEvent actionEvent) {
        if(!jeLiPrazan(unosIme.getText()) && !jeLiPrazan(unosPrezime.getText()) && !jeLiPrazan(unosUsername.getText())
                && !jeLiPrazan(unosPassword.getText())) {
            LoginController login = new LoginController();
            dao.dodajRadnika(unosIme.getText(), unosPrezime.getText(), unosUsername.getText(), unosPassword.getText(), dao.getLogovani());
            radnici.removeAll(radnici);
            for(Radnik g: dao.radnici()) {
                radnici.add(g);
            }
            tableView.setItems(radnici);
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            colIme.setCellValueFactory(new PropertyValueFactory("ime"));
            colPrezime.setCellValueFactory(new PropertyValueFactory("prezime"));
            colUsername.setCellValueFactory(new PropertyValueFactory("username"));
            colManager.setCellValueFactory(new PropertyValueFactory<Radnik, Radnik>("manager"));
        }
        else {

        }
    }

    public void obrisiBtn(ActionEvent actionEvent) {
        Radnik obrisiGa = tableView.getSelectionModel().getSelectedItem();
        dao.obrisiRadnika(obrisiGa);
        radnici.removeAll(radnici);
        for(Radnik g: dao.radnici()) {
            radnici.add(g);
        }
        tableView.setItems(radnici);
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colIme.setCellValueFactory(new PropertyValueFactory("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory("prezime"));
        colUsername.setCellValueFactory(new PropertyValueFactory("username"));
        colManager.setCellValueFactory(new PropertyValueFactory<Radnik, Radnik>("manager"));
    }
}
