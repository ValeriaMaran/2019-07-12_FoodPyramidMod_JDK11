package it.polito.tdp.food.model;

import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;
import it.polito.tdp.food.db.FoodDAO;

public class Model {
	private Graph<Food, DefaultWeightedEdge> grafo;
	private Map<Integer,Food> idMapVertici;
	private List<AdiacenzeCibo> adiacenzeModel;
	private FoodDAO dao;
	
	public Model() {
		this.dao = new FoodDAO();
	}
	public void creaGrafo(int porzione) {
		grafo = new SimpleWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		adiacenzeModel = new LinkedList<AdiacenzeCibo>();
		idMapVertici = new HashMap<Integer,Food>();
		dao.getVertex(porzione, idMapVertici);
		adiacenzeModel = dao.getEdges(idMapVertici);
		for(Food f : idMapVertici.values()) {
			grafo.addVertex(f);
		}
		System.out.println(grafo.vertexSet().size());
		for(AdiacenzeCibo a : adiacenzeModel) {
			Graphs.addEdgeWithVertices(grafo,a.getF1(),a.getF2(),a.getPeso());
		}
		System.out.println(grafo.edgeSet().size());
	}
	
	
	
	public List<Food> getVertexList(){
		List<Food> listaCibi = new LinkedList<Food>(grafo.vertexSet());
		Collections.sort(listaCibi);
		return listaCibi;
	}
	public int getNumberEdges() {
		return this.grafo.edgeSet().size();
		
	}
	
	public List<Ordinamento> getPeggiori(Food nodoPadre){
		List<Food> vicini = Graphs.neighborListOf(grafo,nodoPadre);
		List<Ordinamento> ordinamento = new ArrayList<Ordinamento>();
		for(Food f : vicini) {
			Ordinamento o = new Ordinamento(f,grafo.getEdgeWeight(grafo.getEdge(nodoPadre,f)));
			ordinamento.add(o);
		}
		Collections.sort(ordinamento);
		return ordinamento;
	}
}
