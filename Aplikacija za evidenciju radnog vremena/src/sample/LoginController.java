package sample;

import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML
    public TextField fieldUsername, fieldPassword, pomocni;
    @FXML
    public Button btnOk;
    private int loginID;
    private DAO dao = DAO.getInstance();

//    public LoginController(TextField fieldUsername, TextField fieldPassword) {
//        this.fieldUsername = fieldUsername;
//        this.fieldPassword = fieldPassword;
//    }

    public TextField getFieldUsername() {
        return fieldUsername;
    }

    public void setFieldUsername(TextField fieldUsername) {
        this.fieldUsername = fieldUsername;
    }

    public TextField getFieldPassword() {
        return fieldPassword;
    }

    public void setFieldPassword(TextField fieldPassword) {
        this.fieldPassword = fieldPassword;
    }

    public void okAction(ActionEvent actionEvent) {
//        boolean validan = false;
//
//        if(validan == true){
//
//        }
        if(dao.daLiJeRegistrovan(fieldUsername.getText()) && this.fieldPassword.getText().equals(dao.dajSifru(fieldUsername.getText()))) {
            dao.setLogovani(dao.dajIdZaUsername(fieldUsername.getText()));
            if(dao.jeLiRadnik(fieldUsername.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("radnik.fxml"));
                RadnikController ctrl = new RadnikController();
                loader.setController(ctrl);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage primaryStage = new Stage();
                primaryStage.setScene(new Scene(root, 300, 275));
                primaryStage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                AdminController ctrl = new AdminController();
                loader.setController(ctrl);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage primaryStage = new Stage();
                primaryStage.setScene(new Scene(root, 1000, 975));
                primaryStage.show();
            }
        }

    }

    public int getLoginID() {
        return loginID;
    }
}
