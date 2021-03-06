/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import accesoaBD.AccesoaBD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ian Ward
 */
public class MainScreenController implements Initializable {

    private Stage primaryStage;
    @FXML
    private Button btnMakeReservation;
    @FXML
    private Button btnMakeTicketSale;
    @FXML
    private Button btnMakeReservationSale;

    public void initStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Ticket Office");
        AccesoaBD base = new AccesoaBD();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnMakeReservation_Click(ActionEvent event) {
        swapWindow(false);
    }

    @FXML
    private void btnMakeTicketSale_Click(ActionEvent event) {
        swapWindow(true);
    }

    @FXML
    private void btnMakeReservationSale_Click(ActionEvent event) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/view/TicketFromR.fxml"));
            Parent root = (Parent) myLoader.load();
            TicketFromRController r = myLoader.<TicketFromRController>getController();
            r.initStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void swapWindow(boolean one){
    try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/view/DayMovieScreen.fxml"));
            Parent root = (Parent) myLoader.load();
            DayMovieScreenController w = myLoader.<DayMovieScreenController>getController();
            w.initStage(primaryStage, one);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    @FXML
    private void quit_Click(ActionEvent event) {
        primaryStage.hide();
    }

}
