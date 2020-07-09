package it.polito.tdp.food.model;

public class Ordinamento implements Comparable<Ordinamento> {
	private Food f1;
	private Double peso;
	public Ordinamento(Food f1, Double peso) {
		super();
		this.f1 = f1;
		this.peso = peso;
	}
	public Food getF1() {
		return f1;
	}
	public void setF1(Food f1) {
		this.f1 = f1;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return String.format("Ordinamento [f1=%s, peso=%s]", f1, peso);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f1 == null) ? 0 : f1.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordinamento other = (Ordinamento) obj;
		if (f1 == null) {
			if (other.f1 != null)
				return false;
		} else if (!f1.equals(other.f1))
			return false;
		return true;
	}
	@Override
	public int compareTo(Ordinamento o) {
		// TODO Auto-generated method stub
		return this.peso.compareTo(o.getPeso());
	}
	
	
}
