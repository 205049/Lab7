package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.dizionario.model.DizionarioModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGrafo;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnConnessi;

    @FXML
    private TextArea txtResult;
    
    private DizionarioModel model;

    public void setModel(DizionarioModel model) {
		this.model = model;
	}

	@FXML
    void doConnessi(ActionEvent event) {
		int lenght = Integer.parseInt(txtLettere.getText());
    	String p = txtParola.getText();
    	
    	if(p.compareTo("") == 0)
    		txtResult.setText("Errore. Parola non inserita. Inserisci parola...");
    	else if(p.length() != lenght)
    		txtResult.setText("Errore. Lunghezza parola errata.");
    	else if(!model.getDizionario().contains(p))
    		txtResult.setText("Errore. Parola inesistente.");
    	else{
    		
    		List<String> connessi = new ArrayList<String>();
    		//vicini.addAll(model.paroleSimili(p));
    		
    		model.connessi(p);
    		model.getConnessi().remove(p);
    		connessi.addAll(model.getConnessi());
    		
    		txtResult.setText("Connessi:\n");
    		for(String s: connessi){
    			txtResult.appendText(s + "\n");
    		}
    		
    	}
    }

    @FXML
    void doGrafo(ActionEvent event) {
    	
    	model.getDizionario().clear();
    	
    	try{
    		int lenght = Integer.parseInt(txtLettere.getText());

        	model.generaGrafo(lenght);
        	
        	btnVicini.setDisable(false);
        	btnConnessi.setDisable(false);
        	
        	txtResult.setText("Grafo generato correttamente!");
    	} catch (NumberFormatException e) {
    		txtResult.setText("Inserisci il numero di lettere...");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtLettere.clear();
    	txtParola.clear();
    	txtResult.clear();
    	
    	model.getDizionario().clear();
    	
    	btnVicini.setDisable(true);
    	btnConnessi.setDisable(true);
    	
    }

    @FXML
    void doVicini(ActionEvent event) {
    	int lenght = Integer.parseInt(txtLettere.getText());
    	String p = txtParola.getText();
    	
    	if(p.compareTo("") == 0)
    		txtResult.setText("Errore. Parola non inserita. Inserisci parola...");
    	else if(p.length() != lenght)
    		txtResult.setText("Errore. Lunghezza parola errata.");
    	else if(!model.getDizionario().contains(p))
    		txtResult.setText("Errore. Parola inesistente.");
    	else{
    		
    		List<String> vicini = new LinkedList<String>();
    		//vicini.addAll(model.paroleSimili(p));
    		
    		vicini.addAll(Graphs.neighborListOf(model.getGrafo(), p));
    		
    		txtResult.setText("Vicini:\n");
    		for(String s: vicini){
    			txtResult.appendText(s + "\n");
    		}
    		
    	}
    }

    @FXML
    void initialize() {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnGrafo != null : "fx:id=\"btnGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnConnessi != null : "fx:id=\"btnConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";

        model = new DizionarioModel();
    }
}

