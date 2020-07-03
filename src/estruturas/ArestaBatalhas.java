package estruturas;

import java.util.ArrayList;

import cavaleiros.CavaleiroBronze;
import cavaleiros.CavaleiroOuro;

public class ArestaBatalhas {
	private float peso;
	private CavaleiroOuro origem;
	private CavaleiroOuro destino;
	private float fcost;
	private boolean visitado = false;
	private float gcost;
	private float hcost;
	ArrayList<CavaleiroBronze> cavaleiros;
	
	public ArrayList<CavaleiroBronze> getCavaleiros() {
		return cavaleiros;
	}

	public ArestaBatalhas(CavaleiroOuro origem, CavaleiroOuro destino, ArrayList<CavaleiroBronze> cavaleiros){
		this.setOrigem(origem);
		this.setDestino(destino);
		this.cavaleiros = cavaleiros;
	}

	public CavaleiroOuro getOrigem() {
		return origem;
	}

	public void setOrigem(CavaleiroOuro origem) {
		this.origem = origem;
	}

	public CavaleiroOuro getDestino() {
		return destino;
	}

	public void setDestino(CavaleiroOuro destino) {
		this.destino = destino;
	}

	public float getPeso() {
		float peso = 0;
		for(CavaleiroBronze cb: this.cavaleiros){
			peso += cb.getCosmo();
		}
		return (this.getOrigem().getDificuldade()/peso);
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public float getFcost() {
		return fcost + this.getGcost();
	}

	public void setFcost(float heuristica) {
		this.fcost = heuristica;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public float getGcost() {
		return gcost;
	}

	public void setGcost(float gcost) {
		this.gcost = gcost;
	}

	public float getHcost() {
		return hcost;
	}

	public void setHcost(float hcost) {
		this.hcost = hcost;
	}
}
