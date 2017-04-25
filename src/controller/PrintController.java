/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Proyeccion;
import modelo.Sala;

/**
 * FXML Controller class
 *
 * @author Ian Ward
 */
public class PrintController implements Initializable {

    private Printer printer = Printer.getDefaultPrinter(); //default printer
    @FXML
    private AnchorPane anchorPane;
    private Stage primaryStage;
    private Proyeccion proyeccion;
    private Sala sala;
    private int counter;
    @FXML
    private Label selPrinter;
    @FXML
    private Label printState;
    @FXML
    private Label pelicula;
    @FXML
    private Label dia;
    @FXML
    private Label sesion;
    @FXML
    private Label sitios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 public void initStage(Stage stage, Proyeccion p, Sala s, int c) {
        primaryStage = stage;
        proyeccion = p;
        sala = s;
        counter = c;
        pelicula.setText(proyeccion.getPelicula().getTitulo());
        dia.setText(proyeccion.getDia().toString());
        sesion.setText(proyeccion.getHoraInicio());
        sitios.setText("" + counter);
        selPrinter.setText("" + printer);
    }
    @FXML
    private void selectPrinter(ActionEvent event) {
        // Selects a printer installed in the machine
        ChoiceDialog dialog = new ChoiceDialog(Printer.getDefaultPrinter(),
                Printer.getAllPrinters());
        dialog.setHeaderText("Choose a printer");
        dialog.setContentText("Choose a printer from the list");
        dialog.setTitle("Choose Printer");
        Optional<Printer> opt = dialog.showAndWait();
        if (opt.isPresent()) {
            printer = opt.get();
            selPrinter.setText("" + printer);
        }
    }

    @FXML
    private void print(ActionEvent event) {
        print(anchorPane); // node to be printed
    }

    private void print(Node node) {
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        if (job != null) {
            boolean printed = job.printPage(node);
            if (printed) {
                job.endJob();
                printState.setText("Sent to Printer");
            } else {
                printState.setText("Error while printing");
            }
        } else {
            printState.setText("Failed to create the printer job ");
        }
    }
}
