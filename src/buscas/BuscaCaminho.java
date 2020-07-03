package buscas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;

public class BuscaCaminho {

	private Grafo g = new Grafo();
	private Vertice atual;
	private Vertice objetivo;
	public ArrayList<Vertice> caminho;
	
	public BuscaCaminho(Grafo g, Vertice atual, Vertice objetivo){
		this.g = g;
		this.atual = atual;
		this.objetivo = objetivo;
		this.caminho = pathFinding(g, atual, objetivo);
	}	
	/* Compara pelo fCost. */
	private Comparator<Vertice> verticeSort = new Comparator<Vertice>(){
		public int compare(Vertice v1, Vertice v2) {
			if(v1.getDistancia() > v2.getDistancia()) return +1;
			if(v1.getDistancia() < v2.getDistancia()) return -1;
			return 0;
		}
	};
	/*
	 * Executa o A*.
	 * OpenList - vértices que precisam ser avaliados ainda. Estão na fronteira de expansão.
	 * ClosedList - vértices que já foram avaliados. Quando um vértice é avaliado, ele sai
	 * de OpenList e entra em ClosedList. 
	 * Começamos com 3 listas de vértices:
	 * Caminho - o caminho a ser seguido.
	 * OpenList - a lista de vértices a serem avaliados durante a execução.
	 * ClosedList - a lista de vértices que já foram avaliados. 
	 * Depois pegamos o vértice que recebemos como parâmetro, e o usamos como nosso vértice
	 * atual, pois começamos sempre no início do mapa. Então adicionamos o vértice atual a OpenList
	 * pois ele é nosso único vértice por enquanto e ainda não foi avaliado.
	 * Enquanto nossa OpenList for maior do que 0, ou seja, há pelo menos um vértice lá, nos a
	 * ordenamos e pegamos o elemento na sua primeira posição, indíce 0. Depois vemos se o vértice em que 
	 * estamos é o objetivo. Caso seja, adicionamos ele ao caminho e pegamos os pais dele, pois é a
	 * partir dos pais que temos o caminho, já que ele é feito do fim para o início. Enquanto o pai
	 * do atual não for nulo, adicionamos ele ao caminho, o atual vira o seu pai, e fazemos isso 
	 * novamente. Depois liberamos as duas listas e retornamos o caminho. Caso contrário, removemos o 
	 * atual da OpenList e o colocamos na ClosedList, pois ele já foi avaliado. O próximo passo é 
	 * setar o vértice que acabou de ser avaliado como visitado, para que ele não seja visitado novamente.
	 * Depois pegamos as arestas adjacentes e verificamos se algum dos vizinhos é melhor que o atual.
	 * Caso ele seja, o vizinho em questão se torna o objetivo da aresta adjacente.
	 * */
	public ArrayList<Vertice> pathFinding(Grafo g, Vertice comeco, Vertice objetivo){
		 
		ArrayList<Vertice> caminho = new ArrayList<Vertice>();
		ArrayList<Vertice> openList = new ArrayList<Vertice>();
		ArrayList<Vertice> closedList = new ArrayList<Vertice>();
		Vertice atual = comeco;
		
		openList.add(atual);
		
		while(openList.size() > 0){
			
			Collections.sort(openList, verticeSort);
			atual = openList.get(0);
			if(atual.getI() == objetivo.getI() && atual.getJ() == objetivo.getJ()){
				while(atual.getPai() != null){
					caminho.add(atual);
					System.out.println("atual: "+atual.getI() +","+atual.getJ());
					atual = atual.getPai();
				}
				openList.clear();
				closedList.clear();
				return caminho;
			}
			openList.remove(atual);
			closedList.add(atual);
			for(Vertice a : g.getVertices()){
				if (a.getI() == atual.getI() && a.getJ() == atual.getJ())
					a.setVisitado(true);
			}
			ArrayList<Aresta> vizinhos = g.getArestasAdjacentes(atual.getI(), atual.getJ());
			
			System.out.println("Atual : [" + atual.getI() + "," + atual.getJ() +"]" + " Gcost: " + atual.getGcost());
			for(Aresta a: vizinhos){
				boolean vizinhoEhMelhor;
				
				/* 
				 * Se a aresta estiver na closedList ela não precisa ser verificada novamente, então tudo o que vem
				 * a seguir é ignorado e voltamos para o começo do while. Sem isso, funciona mas a execução fica
				 * muito mais lenta.
				 */
				if(vetInList(closedList, a)){
					continue;
				}
				
				double gCost = atual.getGcost() + a.getPeso();
				
				if(!vetInList(openList, a)){
					openList.add(a.getDestino());
					vizinhoEhMelhor = true;
				}
				else if(gCost < atual.getGcost()){
					vizinhoEhMelhor = true;
				}
				else{
					vizinhoEhMelhor = false;
				}
				
				if(vizinhoEhMelhor){
					a.getDestino().setPai(atual);
					a.getDestino().setDistanciaPai(a.getPeso());
					a.getDestino().setGcost(gCost);
					a.getDestino().setDistancia(g.calculaDistancia(a.getDestino(), objetivo));
				}
				
			}
			System.out.println();
		}
		closedList.clear();
	
		return null;
	}
	/*
	 * Verifica se um vértice (recebe uma aresta e pega o destino) está em uma lista. Closed ou Open.
	 * */
	private boolean vetInList(ArrayList<Vertice> closedList, Aresta a){
		for(Vertice v: closedList){
			if(v.getI() == a.getDestino().getI() && v.getJ() == a.getDestino().getJ())
				return true;
		}
		return false;
	}
}
