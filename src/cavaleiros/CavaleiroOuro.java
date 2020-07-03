package cavaleiros;

import estruturas.ArestaBatalhas;

public class CavaleiroOuro {
	private int id;
	private int dificuldade;
	private boolean vivo;
	private boolean visitado = false;
	private float gcost;
	private ArestaBatalhas pai;
	private float distanciaPai;
	private float distanciaDestino;
	
	public float getGcost() {
		return gcost;
	}

	public void setGcost(float gcost) {
		this.gcost = gcost;
	}

	public CavaleiroOuro(int dificuldade, boolean vivo, int id) {
		this.dificuldade = dificuldade;
		this.vivo = vivo;
		this.id = id;
	}
	
	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public ArestaBatalhas getPai() {
		return pai;
	}

	public void setPai(ArestaBatalhas pai) {
		this.pai = pai;
	}

	public float getDistanciaPai() {
		return distanciaPai;
	}

	public void setDistanciaPai(float f) {
		this.distanciaPai = f;
	}

	public float getDistanciaDestino() {
		return distanciaDestino + this.getGcost();
	}

	public void setDistanciaDestino(float distanciaDestino) {
		this.distanciaDestino = distanciaDestino;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
