package it.polito.tdp.food.model;
import java.util.*;
public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.creaGrafo(1);
		List<Ordinamento> o = new LinkedList<Ordinamento>();
		Food f1 = new Food(11514100,"Instant cocoa (with water)");
		o = m.getPeggiori(f1);
		for(Ordinamento ot : o) {
			System.out.println(o.toString());
		}
 	}

}
