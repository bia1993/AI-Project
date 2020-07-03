package estruturas;

public class Aresta {
	private Vertice origem;
	private Vertice destino;
	private int peso;
	private int gCost;

	public Aresta(Vertice origem, Vertice destino, int peso){
		this.setOrigem(origem);
		this.setDestino(destino);
		this.setPeso(peso);
	}

	public Vertice getOrigem() {
		return origem;
	}

	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getGCost() {
		return gCost;
	}

	public void setGCost(int gCost) {
		this.gCost = gCost;
	}
	
	
}
