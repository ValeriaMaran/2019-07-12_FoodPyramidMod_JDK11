package it.polito.tdp.food;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Ordinamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;

//controller turno A --> switchare al branch master_turnoB per turno B

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPorzioni;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnAnalisi;

    @FXML
    private Button btnCalorie;

    @FXML
    private Button btnSimula;

    @FXML
    private ComboBox<Food> boxFood;

    @FXML
    private TextArea txtResult;

    @SuppressWarnings("unlikely-arg-type")
	@FXML
    void doCalorie(ActionEvent event) {
    	Food f = boxFood.getValue();
    	if(f==null) {
    		txtResult.appendText("devi selezionare un cibo dal menù a tendina prima di usare il box calorie");
    		return;
    	}
    	List<Ordinamento> ordinamentoPrimi5 = new ArrayList<Ordinamento>();
    	ordinamentoPrimi5 = model.getPeggiori(f);
    	boolean flag = false;
    	if(ordinamentoPrimi5.size()!=0) {
    		flag = true;
    	}
    	int i = 0;
    	if(ordinamentoPrimi5.size()>5 && flag ==true) {
    		for(i=0;i<5;i++) {
    			
        		txtResult.appendText(ordinamentoPrimi5.get(i).toString());
        	}
    	}
    	if(ordinamentoPrimi5.size()<5 && flag == true) {
    		for(i=0;i<ordinamentoPrimi5.size();i++) {
    			txtResult.appendText(ordinamentoPrimi5.get(i).toString()+"\n");
    		}
    	}
    	if(flag == false) {
    		txtResult.appendText("\nnon è possibile calcolare la lista dei 5 peggiori cibi collegati a quello selezionato");
    	}

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	String s = txtPorzioni.getText();
    	Integer porzioni = -1;
    	if(s==null) {
    		txtResult.appendText("Non hai inserito alcun valore, devi inserire un numero di porzioni prima di fare l'analisi e creare il grafo");
    		return;
    	}
    	try {
    		porzioni = Integer.parseInt(s);
    		model.creaGrafo(porzioni);
    		
    		boxFood.getItems().addAll(this.model.getVertexList());
    		txtResult.appendText("Grafo creato correttamente, contiene "+this.model.getVertexList().size()+" archi e"+this.model.getNumberEdges()+" archi");
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Il numero inserito non è nel formato corretto, devi inserire un numero intero");
    		return;
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
