package it.polito.tdp.food.db;
import java.util.*;

import it.polito.tdp.food.model.AdiacenzeCibo;
import it.polito.tdp.food.model.Food;
public class TestDAO {

	public static void main(String[] args) {
		FoodDAO dao = new FoodDAO();
		List<AdiacenzeCibo> listaAdiacenze = new LinkedList<AdiacenzeCibo>();
		/*System.out.println("Printing all the condiments...");
		System.out.println(dao.listAllCondiments());
		
		System.out.println("Printing all the foods...");
		System.out.println(dao.listAllFoods());
		
		System.out.println("Printing all the portions...");
		System.out.println(dao.listAllPortions());*/
		
		Map<Integer, Food> prova = new HashMap<Integer,Food>();
		dao.getVertex(1, prova);
		System.out.println(prova.size());
		listaAdiacenze = dao.getEdges(prova);
		System.out.println(listaAdiacenze.size());
		for(AdiacenzeCibo a : listaAdiacenze) {
			System.out.println(a.getF1().toString()+" "+a.getF2().toString()+" "+a.getPeso()+"\n");
		}
	}

}