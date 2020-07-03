package estruturas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cavaleiros.CavaleiroBronze;
import cavaleiros.CavaleiroOuro;

/*
 * Métodos para gerar os grafos que serão usados no A* do caminho e das batalhas. Além de métodos para maniular os grafos, como adicionar
 * arestas, vértices, arestas adjacentes, etc. também calculamos aqui as heurísticas para cada busca.
 * */

public class Grafo {
	private ArrayList<Vertice> vertices;
	private ArrayList<Aresta> arestas;
	private ArrayList<CavaleiroOuro> cavaleirosDeOuro;
	private ArrayList<ArestaBatalhas> arestasBatalhas;
	
	public Grafo() {
		this.vertices = new ArrayList<Vertice>();
		this.arestas = new ArrayList<Aresta>();
		this.arestasBatalhas = new ArrayList<ArestaBatalhas>();
		this.cavaleirosDeOuro = new ArrayList<CavaleiroOuro>();
		Collections.sort(this.cavaleirosDeOuro, CavaleiroOuroSort);
		Collections.sort(this.arestasBatalhas, ArestaBatalhasSort);
    }
	
	public static Comparator<CavaleiroOuro> CavaleiroOuroSort = new Comparator<CavaleiroOuro>(){
		public int compare(CavaleiroOuro v1, CavaleiroOuro v2) {
			if(v1.getDificuldade() < v2.getDificuldade()) return +1;
			if(v1.getDificuldade() > v2.getDificuldade()) return -1;
			return 0;
		}
	};
	
	public static Comparator<ArestaBatalhas> ArestaBatalhasSort = new Comparator<ArestaBatalhas>(){
		public int compare(ArestaBatalhas a1, ArestaBatalhas a2) {
			int somaA1 = 0;
			int somaA2 = 0;
			for(CavaleiroBronze cv: a1.getCavaleiros()){
				somaA1 += cv.getCosmo();
			}
			for(CavaleiroBronze cv: a2.getCavaleiros()){
				somaA2 += cv.getCosmo();
			}
			if(somaA1 > somaA2) return -1;
			if(somaA1 < somaA2) return +1;
			return 0;
		}
	};
	
	public ArestaBatalhas addArestaBatalhas(CavaleiroOuro origem, CavaleiroOuro destino, ArrayList<CavaleiroBronze> cavaleiros){
		ArestaBatalhas a = new ArestaBatalhas(origem,destino, cavaleiros);
		this.arestasBatalhas.add(a);
		return a;
	}
	
	public CavaleiroOuro addVerticeBatalhas(int dificuldade, boolean b, int id){
		CavaleiroOuro c = new CavaleiroOuro(dificuldade, b, id);
		this.getCavaleiros().add(c);
		return c;
	}
	
	public ArrayList<ArestaBatalhas> getArestasBatalhasAdjacentes(CavaleiroOuro cavaleiro){
		ArrayList<ArestaBatalhas> a = new ArrayList<ArestaBatalhas>();
		for(ArestaBatalhas ab: this.arestasBatalhas){
			if(cavaleiro.getDificuldade() == ab.getOrigem().getDificuldade() && ab.getOrigem().getId() == cavaleiro.getId()){
				a.add(ab);
			}
		}
		return a;
	}
	
	
	public Vertice addVertice(char nome, int i, int j){
		Vertice v = new Vertice(nome, i , j);
		vertices.add(v);
		return v;
	}
	
	public Aresta addAresta(Vertice origem, Vertice destino, int peso){
		Aresta a = new Aresta(origem, destino, peso);
		arestas.add(a);
		return a;
	}
	
	public ArrayList<Aresta> getArestasAdjacentes(int i, int j){
		ArrayList<Aresta> adjs = new ArrayList<Aresta>();
 		for(Aresta a: this.arestas){
			if(a.getOrigem().getI() == i && a.getOrigem().getJ() == j){
					adjs.add(a);
			}
		}
		return adjs;
	}
	

	/* Calculca heurística para A* do caminho. */
	public double calculaDistancia (Vertice atual, Vertice objetivo){
			int i = Math.abs(objetivo.getI() - atual.getI());
			int j = Math.abs(objetivo.getJ() - atual.getJ());
			double d = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) + atual.getGcost() *10;
			
			atual.setDistancia(d);
			return d;
 	}
	
	public float calculaHeuristicaBatalhas(CavaleiroOuro co, ArrayList<CavaleiroOuro> cavaleiros, ArestaBatalhas aresta){
		float heuristica = 0;
		float cosmoVida = 0;
		boolean chegou = false;
		for(CavaleiroOuro caval : cavaleiros){
			if((!(caval.getDificuldade() == co.getDificuldade())) && (!chegou)){
				continue;
			}
			chegou = true;
			heuristica += caval.getDificuldade();
		}
		
		for(CavaleiroBronze cb: aresta.getCavaleiros()){
			if(cb.getEnergia() >= 0){
				cosmoVida += (cb.getCosmo() * cb.getEnergia());
			}
		}
		if(cosmoVida == 0){
			return heuristica;
		}
		return heuristica / cosmoVida;
	}
	
	int calculaPeso(char mapa[][], int i, int j){
		if(mapa[i][j] == 'p')
			return 1;
		if(mapa[i][j] == 'r')
			return 5;
		if(mapa[i][j] == 'm')
			return 200;
		return 1;
	}
	
	public void geraGrafo(int WIDTH, int HEIGHT, char mapa[][]){
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if(j < WIDTH - 1){
					this.addAresta(this.getVertices().get(WIDTH*i + j), this.getVertices().get(WIDTH*i + j+1), calculaPeso(mapa, i , j+1));
				}
				if(j > 0){
					this.addAresta(this.getVertices().get(WIDTH*i + j), this.getVertices().get(WIDTH*i + j-1), calculaPeso(mapa, i , j-1));
				}
				if(i > 0){
					this.addAresta(this.getVertices().get(WIDTH*i + j), this.getVertices().get(WIDTH*(i-1) + j), calculaPeso(mapa, i-1 , j));
				}
				if(i < HEIGHT -1){
					this.addAresta(this.getVertices().get(WIDTH*i + j), this.getVertices().get(WIDTH*(i+1) + j), calculaPeso(mapa, i+1, j));
				}
					
			} 
				
		}
		
	}
	
	public void printVertice(){
		for(Vertice v: this.vertices){
			System.out.println("[" + v.getI() + "," + v.getJ() + "]");
		}
	}
	
	public void printAresta(){
		for(Aresta a: this.arestas){
			System.out.println("[" + a.getOrigem().getI() + "," + a.getOrigem().getJ() + "]" 
								   + "-" + "[" + a.getDestino().getI() + "," + a.getDestino().getJ() + "]" + ": Peso = " + a.getPeso());
		}
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public ArrayList<CavaleiroOuro> getCavaleiros() {
		return cavaleirosDeOuro;
	}

	public void setCavaleiros(ArrayList<CavaleiroOuro> cavaleiros) {
		this.cavaleirosDeOuro = cavaleiros;
	}
	
	public ArrayList<ArestaBatalhas> getArestasBatalhas() {
		return arestasBatalhas;
	}

	
}
