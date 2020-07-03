package estruturas;

import java.util.ArrayList;

public class Vertice {
	private char nome;
	private int i;
	private int j;
	private Vertice pai = null;
	private int distanciaPai;
	private ArrayList<Aresta> adjacentes;
	private boolean visitado = false;
	private double fCost, gCost, distanciaDestino;
	
	public double getDistancia() {
		return distanciaDestino + this.getGcost();
	}

	public void setDistancia(double d) {
		this.distanciaDestino = d;
	}
	
	public Vertice(char nome, int i, int j){
		this.setNome(nome);
		this.i = i;
		this.j = j;
		this.adjacentes = new ArrayList<Aresta>();
	}
	
	void addAdj(Aresta adj){
		adjacentes.add(adj);
	}

	public char getNome() {
		return nome;
	}

	public void setNome(char nome) {
		this.nome = nome;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public Vertice getPai() {
		return pai;
	}

	public void setPai(Vertice pai) {
		this.pai = pai;
	}

	public int getDistanciaPai() {
		return distanciaPai;
	}

	public void setDistanciaPai(int distanciaPai) {
		this.distanciaPai = distanciaPai;
	}

	public double getGcost() {
		return gCost;
	}

	public void setGcost(double gcost) {
		this.gCost = gcost;
	}

	public double getFcost() {
		return fCost;
	}

	public void setFcost(double fcost) {
		this.fCost = fcost;
	}
	
	
	

}
