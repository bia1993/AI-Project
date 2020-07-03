package buscas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cavaleiros.CavaleiroBronze;
import cavaleiros.CavaleiroOuro;
import estruturas.Aresta;
import estruturas.ArestaBatalhas;
import estruturas.Grafo;
import estruturas.Vertice;
import gui.Arquivos;

public class BuscaBatalhas {

	static int[] dificuldadeCasas = Arquivos.pegaDificuldade();
	static float[] cosmo = Arquivos.pegaCosmo();
	
	private Grafo g = new Grafo();
	private CavaleiroOuro atual;
	private CavaleiroOuro objetivo;
	public ArrayList<ArestaBatalhas> caminho;
	
	public BuscaBatalhas(Grafo g,  CavaleiroOuro objetivo){
		this.g = g;
		this.objetivo = objetivo;
		this.caminho = pathFinding(g, objetivo);
	}	
	
	private Comparator<ArestaBatalhas> ArestaBatalhasSort = new Comparator<ArestaBatalhas>(){
		public int compare(ArestaBatalhas v1, ArestaBatalhas v2) {
			if(v1.getFcost() > v2.getFcost()) return +1;
			if(v1.getFcost() < v2.getFcost()) return -1;
			return 0;
		}
	};
	
	private Comparator<CavaleiroOuro> CavaleirosOuro = new Comparator<CavaleiroOuro>(){
		public int compare(CavaleiroOuro v1, CavaleiroOuro v2) {
			if(v1.getDificuldade() > v2.getDificuldade()) return -1;
			if(v1.getDificuldade() < v2.getDificuldade()) return +1;
			return 0;
		}
	};
	
	public ArrayList<ArestaBatalhas> pathFinding(Grafo g, CavaleiroOuro objetivo){
		 
		CavaleiroBronze Shun = new CavaleiroBronze("Shun", (float) 1.2, 5, true);
		CavaleiroBronze Hyoga = new CavaleiroBronze("Hyoga", (float) 1.3, 5, true);
		CavaleiroBronze Shiryu = new CavaleiroBronze("Shiryu", (float) 1.4, 5, true);
		CavaleiroBronze Seiya = new CavaleiroBronze("Seiya", (float) 1.5, 5, true);
		CavaleiroBronze Ikki = new CavaleiroBronze("Ikki", (float) 1.1, 4, true);	
		ArrayList<CavaleiroBronze> cavaleiros = new ArrayList<CavaleiroBronze>();
		cavaleiros.add(Shun);
		cavaleiros.add(Hyoga);
		cavaleiros.add(Shiryu);
		cavaleiros.add(Seiya);
		cavaleiros.add(Ikki);

		ArrayList<CavaleiroBronze> cb1 = new ArrayList<CavaleiroBronze>(); //todos
		cb1.add(Shun);
		cb1.add(Hyoga);
		cb1.add(Shiryu);
		cb1.add(Seiya);
		cb1.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb2 = new ArrayList<CavaleiroBronze>();//sem Seiya
		cb2.add(Shun);
		cb2.add(Hyoga);
		cb2.add(Shiryu);
		cb2.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb3 = new ArrayList<CavaleiroBronze>();//sem Shiryu
		cb3.add(Shun);
		cb3.add(Hyoga);
		cb3.add(Seiya);
		cb3.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb4 = new ArrayList<CavaleiroBronze>();//sem Hyoga
		cb4.add(Shun);
		cb4.add(Shiryu);
		cb4.add(Seiya);
		cb4.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb5 = new ArrayList<CavaleiroBronze>();//sem Shun
		cb5.add(Hyoga);
		cb5.add(Shiryu);
		cb5.add(Seiya);
		cb5.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb6 = new ArrayList<CavaleiroBronze>();//sem Ikki
		cb6.add(Shun);
		cb6.add(Hyoga);
		cb6.add(Shiryu);
		cb6.add(Seiya);
		
		ArrayList<CavaleiroBronze> cb7 = new ArrayList<CavaleiroBronze>(); //sem Ikki, Shun
		cb7.add(Hyoga);
		cb7.add(Shiryu);
		cb7.add(Seiya);
		
		ArrayList<CavaleiroBronze> cb8 = new ArrayList<CavaleiroBronze>(); //sem Ikki, Shiryu
		cb8.add(Shun);
		cb8.add(Hyoga);
		cb8.add(Seiya);
		
		ArrayList<CavaleiroBronze> cb9 = new ArrayList<CavaleiroBronze>(); //sem Ikki, Hyoga
		cb9.add(Shun);
		cb9.add(Shiryu);
		cb9.add(Seiya);
		
		ArrayList<CavaleiroBronze> cb10 = new ArrayList<CavaleiroBronze>(); //sem Ikki, Seiya
		cb10.add(Shun);
		cb10.add(Hyoga);
		cb10.add(Shiryu);
		
		ArrayList<CavaleiroBronze> cb11 = new ArrayList<CavaleiroBronze>(); //sem Shun, Hyoga
		cb11.add(Shiryu);
		cb11.add(Seiya);
		cb11.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb12 = new ArrayList<CavaleiroBronze>(); //sem Shun, Shiryu
		cb12.add(Hyoga);
		cb12.add(Seiya);
		cb12.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb13 = new ArrayList<CavaleiroBronze>(); //sem Shun, Seiya
		cb13.add(Hyoga);
		cb13.add(Shiryu);
		cb13.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb14 = new ArrayList<CavaleiroBronze>(); //sem Hyoga, Shiryu
		cb14.add(Shun);
		cb14.add(Seiya);
		cb14.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb15 = new ArrayList<CavaleiroBronze>(); //sem Hyoga, Seiya
		cb15.add(Shun);
		cb15.add(Shiryu);
		cb15.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb16 = new ArrayList<CavaleiroBronze>(); //sem Shiryu, Seiya
		cb16.add(Shun);
		cb16.add(Hyoga);
		cb16.add(Ikki);
		
		/*
		 * 
		 * 
		 */
		ArrayList<CavaleiroBronze> cb17 = new ArrayList<CavaleiroBronze>(); //dupla Shun, Ikki
		cb17.add(Shun);
		cb17.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb18 = new ArrayList<CavaleiroBronze>(); //dupla Ikki, Shiryu
		cb18.add(Shiryu);
		cb18.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb19 = new ArrayList<CavaleiroBronze>(); //dupla Ikki, Hyoga
		cb19.add(Hyoga);
		cb19.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb20 = new ArrayList<CavaleiroBronze>(); //dupla Ikki, Seiya
		cb20.add(Seiya);
		cb20.add(Ikki);
		
		ArrayList<CavaleiroBronze> cb21 = new ArrayList<CavaleiroBronze>(); //dupla Shun, Hyoga
		cb21.add(Shun);
		cb21.add(Hyoga);
		
		ArrayList<CavaleiroBronze> cb22 = new ArrayList<CavaleiroBronze>(); //dupla Shun, Shiryu
		cb22.add(Shun);
		cb22.add(Shiryu);
		
		ArrayList<CavaleiroBronze> cb23 = new ArrayList<CavaleiroBronze>(); //dupla Shun, Seiya
		cb23.add(Shun);
		cb23.add(Shiryu);
		
		ArrayList<CavaleiroBronze> cb24 = new ArrayList<CavaleiroBronze>(); //dupla Hyoga, Shiryu
		cb24.add(Hyoga);
		cb24.add(Shiryu);
		
		ArrayList<CavaleiroBronze> cb25 = new ArrayList<CavaleiroBronze>(); //dupla Hyoga, Seiya
		cb25.add(Hyoga);
		cb25.add(Seiya);
		
		ArrayList<CavaleiroBronze> cb26 = new ArrayList<CavaleiroBronze>(); //dupla Seiya, Shiryu
		cb26.add(Shiryu);
		cb26.add(Seiya);
		
		/*
		 * 
		 * 
		 */
		ArrayList<CavaleiroBronze> cb27 = new ArrayList<CavaleiroBronze>();
		cb27.add(Shun);
		ArrayList<CavaleiroBronze> cb28 = new ArrayList<CavaleiroBronze>();
		cb28.add(Hyoga);
		ArrayList<CavaleiroBronze> cb29 = new ArrayList<CavaleiroBronze>();
		cb29.add(Shiryu);
		ArrayList<CavaleiroBronze> cb30 = new ArrayList<CavaleiroBronze>();
		cb30.add(Seiya);
		ArrayList<CavaleiroBronze> cb31 = new ArrayList<CavaleiroBronze>();
		cb31.add(Ikki);
		
		ArrayList<ArrayList<CavaleiroBronze>> combinacoes = new ArrayList<ArrayList<CavaleiroBronze>>();
		combinacoes.add(cb1);
		combinacoes.add(cb2);
		combinacoes.add(cb3);
		combinacoes.add(cb4);
		combinacoes.add(cb5);
		combinacoes.add(cb6);
		combinacoes.add(cb7);
		combinacoes.add(cb8);
		combinacoes.add(cb9);
		combinacoes.add(cb10);
		combinacoes.add(cb11);
		combinacoes.add(cb12);
		combinacoes.add(cb13);
		combinacoes.add(cb14);
		combinacoes.add(cb15);
		combinacoes.add(cb16);
		combinacoes.add(cb17);
		combinacoes.add(cb18);
		combinacoes.add(cb19);
		combinacoes.add(cb20);
		combinacoes.add(cb21);
		combinacoes.add(cb22);
		combinacoes.add(cb23);
		combinacoes.add(cb24);
		combinacoes.add(cb25);
		combinacoes.add(cb26);
		combinacoes.add(cb27);
		combinacoes.add(cb28);
		combinacoes.add(cb29);
		combinacoes.add(cb30);
		combinacoes.add(cb31);
		
		CavaleiroOuro Mu = new CavaleiroOuro(dificuldadeCasas[0], true ,0);
		CavaleiroOuro Aldebaran = new CavaleiroOuro(dificuldadeCasas[1], true, 0);
		CavaleiroOuro Saga = new CavaleiroOuro(dificuldadeCasas[2], true,0);
		CavaleiroOuro MDM = new CavaleiroOuro(dificuldadeCasas[3], true,0);
		CavaleiroOuro Aioria = new CavaleiroOuro(dificuldadeCasas[4], true,0);
		CavaleiroOuro Shaka = new CavaleiroOuro(dificuldadeCasas[5], true,0);
		CavaleiroOuro Dohko = new CavaleiroOuro(dificuldadeCasas[6], true,0);
		CavaleiroOuro Miro = new CavaleiroOuro(dificuldadeCasas[7], true,0);
		CavaleiroOuro Aioros = new CavaleiroOuro(dificuldadeCasas[8], true,0);
		CavaleiroOuro Shura = new CavaleiroOuro(dificuldadeCasas[9], true,0);
		CavaleiroOuro Camus = new CavaleiroOuro(dificuldadeCasas[10], true,0);
		CavaleiroOuro Afrodite = new CavaleiroOuro(dificuldadeCasas[11], true,0);
		CavaleiroOuro Nulo = new CavaleiroOuro(0, true,0);
		
		ArrayList<CavaleiroOuro> cavaleirosOuro = new ArrayList<CavaleiroOuro>();
		cavaleirosOuro.add(Mu);
		cavaleirosOuro.add(Aldebaran);
		cavaleirosOuro.add(Saga);
		cavaleirosOuro.add(MDM);
		cavaleirosOuro.add(Aioria);
		cavaleirosOuro.add(Shaka);
		cavaleirosOuro.add(Dohko);
		cavaleirosOuro.add(Miro);
		cavaleirosOuro.add(Aioros);
		cavaleirosOuro.add(Shura);
		cavaleirosOuro.add(Camus);
		cavaleirosOuro.add(Afrodite);
		cavaleirosOuro.add(Nulo);
		
		Collections.sort(cavaleirosOuro, CavaleirosOuro);
		
		
		ArrayList<ArestaBatalhas> caminho = new ArrayList<ArestaBatalhas>();
		ArrayList<ArestaBatalhas> openList = new ArrayList<ArestaBatalhas>();
		ArrayList<ArestaBatalhas> closedList = new ArrayList<ArestaBatalhas>();
		
		CavaleiroOuro cavaleiroAtual = cavaleirosOuro.get(0);
		
		Grafo caminhos = new Grafo();
		
		caminhos.addVerticeBatalhas(cavaleiroAtual.getDificuldade(), true, 0);
		caminhos.addVerticeBatalhas(cavaleirosOuro.get(1).getDificuldade(), true, 0);
		ArestaBatalhas atual = caminhos.addArestaBatalhas(cavaleirosOuro.get(0), cavaleirosOuro.get(1), null);
		
		openList.add(atual);
		
		boolean primeiraVez = true;
		
		for(CavaleiroOuro f: cavaleirosOuro){
			System.out.println(f.getDificuldade());
		}
		
		int i = 0;
		int id = 0;
		while(openList.size() > 0){
			
			
			System.out.println("OPENLIST: " + openList.size());
			System.out.println("CLOSEDLIST: " + closedList.size());
			Collections.sort(openList, ArestaBatalhasSort);
			/*
			ArestaBatalhas melhor = null;
			float melhorFcost = 999999999;
			for(ArestaBatalhas a: openList){
				if(openList.size() == 347){
					System.out.println("ORIGEM: " + a.getOrigem().getDificuldade() + "HEURISTICA: " + a.getHcost() + " OPENLIST");
				}
				
				if(a.getFcost() < melhorFcost){
					melhor = a;
					melhorFcost = a.getFcost();
					System.out.println("MEEEELLHOOORRRRR: " + melhor.getOrigem().getDificuldade()  + " HEURISTICA: " + melhor.getFcost() + " MElhorFCost " + melhorFcost);
				}
			}
			
			atual = melhor;
			*/
			atual = openList.get(0);
			//System.out.println("Origem: " + atual.getOrigem().getDificuldade());
			if(!primeiraVez){
				cavaleiroAtual = atual.getDestino();
				/*
				CavaleiroOuro cavaloP = cavaleiroAtual;
				while(cavaloP.getPai() != null){
					for(CavaleiroBronze cb: cavaloP.getPai().getCavaleiros()){
						if(cb.getNome() == cavaleiros.get(0).getNome()){
							cavaleiros.get(0).setEnergia(cavaleiros.get(0).getEnergia()-1);
						}
						else if(cb.getNome() == cavaleiros.get(1).getNome()){
							cavaleiros.get(1).setEnergia(cavaleiros.get(1).getEnergia()-1);
						}
						else if(cb.getNome() == cavaleiros.get(2).getNome()){
							cavaleiros.get(2).setEnergia(cavaleiros.get(2).getEnergia()-1);
						}
						else if(cb.getNome() == cavaleiros.get(3).getNome()){
							cavaleiros.get(3).setEnergia(cavaleiros.get(3).getEnergia()-1);
						}
						else if(cb.getNome() == cavaleiros.get(4).getNome()){
							cavaleiros.get(4).setEnergia(cavaleiros.get(4).getEnergia()-1);
						}
					}
					cavaloP = cavaloP.getPai().getOrigem();
					
				}
				*/
			}else{
				caminhos.getArestasBatalhas().remove(0);
			}
			primeiraVez = false;
			
			
			if(cavaleiroAtual.getDificuldade() == 0){
				System.out.println();
				while(cavaleiroAtual.getPai() != null){
					caminho.add(cavaleiroAtual.getPai());
					cavaleiroAtual = cavaleiroAtual.getPai().getOrigem();
				}
				openList.clear();
				closedList.clear();
				return caminho;
			}
			
			if(closedList.size() == 1048){
				System.out.println();
			}
			
			if(caminhos.getArestasBatalhasAdjacentes(cavaleiroAtual).size() == 0){
				id = 0;
				boolean cavaleiroSemVida = false;
				
				for(ArrayList<CavaleiroBronze> cavalos : combinacoes){
					CavaleiroOuro c = caminhos.addVerticeBatalhas(cavaleirosOuro.get(buscaCavaleiro(cavaleirosOuro, cavaleiroAtual) + 1).getDificuldade(), true , id);
					
					CavaleiroBronze Shun2 = new CavaleiroBronze("Shun", cosmo[0], 5, true);
					CavaleiroBronze Hyoga2 = new CavaleiroBronze("Hyoga", cosmo[1], 5, true);
					CavaleiroBronze Shiryu2 = new CavaleiroBronze("Shiryu", cosmo[2], 5, true);
					CavaleiroBronze Seiya2 = new CavaleiroBronze("Seiya", cosmo[3], 5, true);
					CavaleiroBronze Ikki2 = new CavaleiroBronze("Ikki", cosmo[4], 5, true);	
					ArrayList<CavaleiroBronze> cavaloB = new ArrayList<CavaleiroBronze>();
					for(CavaleiroBronze cb: cavalos){
						if(cb.getNome() == "Shun"){
							Shun2.setEnergia(cb.getEnergia());
							cavaloB.add(Shun2);
						}
						else if(cb.getNome() == "Hyoga"){
							Hyoga2.setEnergia(cb.getEnergia());
							cavaloB.add(Hyoga2);
						}
						else if(cb.getNome() == "Shiryu"){
							Shiryu2.setEnergia(cb.getEnergia());
							cavaloB.add(Shiryu2);
						}
						else if(cb.getNome() == "Seiya"){
							Seiya2.setEnergia(cb.getEnergia());
							cavaloB.add(Seiya2);
						}
						else if(cb.getNome() == "Ikki"){
							Ikki2.setEnergia(cb.getEnergia());
							cavaloB.add(Ikki2);
						}
						
					}
					
					
					
					
					/* Se vida for <= 0 adicionamos os cavaleiros a um ArrayList de mortos. Eles não podem mais lutar. */
					
					ArrayList<CavaleiroBronze> vidaAtualizada = new ArrayList<CavaleiroBronze>();
					ArrayList<CavaleiroBronze> mortos = new ArrayList<CavaleiroBronze>();
					vidaAtualizada = pegaVidaAtual(cavaleiroAtual);
					for(CavaleiroBronze cb: vidaAtualizada){
						if(cb.getEnergia() <= 0){
							mortos.add(cb);
						}
					}
					boolean arestaInvalida = false;
					ArrayList<CavaleiroBronze> cavalosAtualizados = new ArrayList<CavaleiroBronze>();
					for(CavaleiroBronze cb: cavaloB){
						for(CavaleiroBronze morto: mortos){
							if(cb.getCosmo() == morto.getCosmo()){
								arestaInvalida = true;
							}
						}
						int b = buscaCavaleiroBronze(vidaAtualizada,cb);
					
						cb.setEnergia(vidaAtualizada.get(b).getEnergia()-1);
						cavalosAtualizados.add(cb);
						
					}
					int totalVida = 0;
					for(CavaleiroBronze cb: cavalosAtualizados){
						totalVida++;
					}
					for(CavaleiroBronze cb: vidaAtualizada){
						totalVida += cb.getEnergia();
					}
					int indice = buscaCavaleiro(cavaleirosOuro, cavaleiroAtual);
					if(totalVida < 11 - indice){
						System.out.println("Ha menos vidas do que casas");
						continue;
					}
					if(arestaInvalida){
						System.out.println("Aresta Invalida");
						continue;
					}
					
					
					caminhos.addArestaBatalhas(cavaleiroAtual, c, cavaloB);
					System.out.println("Expandiu---------------------------");
					System.out.println("Origem: " + cavaleiroAtual.getDificuldade() + "ID:" + cavaleiroAtual.getId() + " Destino: " + c.getDificuldade());
					for(CavaleiroBronze cb: cavaloB){
						System.out.println("Cavaleiro: " + cb.getCosmo() + " Vida - " + cb.getEnergia());
					}
					
					System.out.println();
					id++;
					//cavalosAtualizados.clear();
				}
				
			}
			
			if(id == 434){
				System.out.println("");
			}
			
			
			
			
			openList.remove(atual);
			closedList.add(atual);
			
			ArrayList<ArestaBatalhas> vizinhos = caminhos.getArestasBatalhasAdjacentes(cavaleiroAtual);
			System.out.println("Numero de vizinhos: " + vizinhos.size());
			int av = 0;
			for(ArestaBatalhas a: vizinhos){
				/*
				CavaleiroBronze Shun2 = new CavaleiroBronze("Shun", (float) 1.2, 5, true);
				CavaleiroBronze Hyoga2 = new CavaleiroBronze("Hyoga", (float) 1.3, 5, true);
				CavaleiroBronze Shiryu2 = new CavaleiroBronze("Shiryu", (float) 1.4, 5, true);
				CavaleiroBronze Seiya2 = new CavaleiroBronze("Seiya", (float) 1.5, 5, true);
				CavaleiroBronze Ikki2 = new CavaleiroBronze("Ikki", (float) 1.1, 5, true);	
				ArrayList<CavaleiroBronze> cavaloB = new ArrayList<CavaleiroBronze>();
				Shun2.setEnergia(cavaleiros.get(0).getEnergia());
				Hyoga2.setEnergia(cavaleiros.get(1).getEnergia());
				Shiryu2.setEnergia(cavaleiros.get(2).getEnergia());
				Seiya2.setEnergia(cavaleiros.get(3).getEnergia());
				Ikki2.setEnergia(cavaleiros.get(4).getEnergia());
				cavaloB.add(Shun2);
				cavaloB.add(Hyoga2);
				cavaloB.add(Shiryu2);
				cavaloB.add(Seiya2);
				cavaloB.add(Ikki2);
				*/
				
				boolean vizinhoEhMelhor = false;
				
				boolean cavaleiroSemVida = false;
				
				double gcost = cavaleiroAtual.getGcost() + a.getPeso();
				
				a.setGcost((float) gcost);
				a.setHcost(g.calculaHeuristicaBatalhas(a.getDestino(), cavaleirosOuro, a));
				a.setFcost(a.getGcost() + a.getHcost());
				/*
				for(CavaleiroBronze cb: a.getCavaleiros()){
					if(cb.getNome() == cavaloB.get(0).getNome()){
						cavaloB.get(0).setEnergia(cavaloB.get(0).getEnergia()-1);
					}
					else if(cb.getNome() == cavaloB.get(1).getNome()){
						cavaloB.get(1).setEnergia(cavaloB.get(1).getEnergia()-1);
					}
					else if(cb.getNome() == cavaloB.get(2).getNome()){
						cavaloB.get(2).setEnergia(cavaloB.get(2).getEnergia()-1);
					}
					else if(cb.getNome() == cavaloB.get(3).getNome()){
						cavaloB.get(3).setEnergia(cavaloB.get(3).getEnergia()-1);
					}
					else if(cb.getNome() == cavaloB.get(4).getNome()){
						cavaloB.get(4).setEnergia(cavaloB.get(4).getEnergia()-1);
					}
				}
				int vidaTot = 0;
				for(CavaleiroBronze cb: cavaloB){
					vidaTot += cb.getEnergia();
					if(cb.getEnergia() <= 0){
						cavaleiroSemVida = true;
					}
				}
				
				if(cavaleiroSemVida){
					closedList.add(a);
					continue;
				}
				
				int indice = buscaCavaleiro(cavaleirosOuro, cavaleiroAtual);
				if(vidaTot < (11 - indice)){
					closedList.add(a);
					continue;
				}
				*/
				/*
				 * Verifica se algum cavaleiro da lista de cavaleiros da aresta que esta sendo verificada
				 * nao tem mais energia sobrando
				 * 
				 */
				if(vetInList(closedList,a)){
					continue;
				}
				
				
				if(!vetInList(openList, a)){
					openList.add(a);
					vizinhoEhMelhor = true;
				}
				else if(gcost < atual.getGcost()){
					vizinhoEhMelhor = true;
				}
				else{
					vizinhoEhMelhor = false;
				}
				
				if(vizinhoEhMelhor){
					a.getDestino().setPai(a);
					a.getDestino().setDistanciaPai(a.getPeso());
					g.getCavaleiros().get(i+1).setPai(a);
					g.getCavaleiros().get(i+1).setDistanciaPai(a.getPeso());
					caminhos.getCavaleiros().get(i+1).setPai(a);
					caminhos.getCavaleiros().get(i+1).setDistanciaPai(a.getPeso());
				}
				av++;
			}
			System.out.println("Numero de arestas vizitadas : " + av);
			System.out.println();
		}
		closedList.clear();
	
		return null;
	}
	/*
	 * Verifica se um vértice (recebe uma aresta e pega o destino) está em uma lista. Closed ou Open.
	 * */
	private boolean vetInList(ArrayList<ArestaBatalhas> closedList, ArestaBatalhas a){
		for(ArestaBatalhas v: closedList){
			if(v.getOrigem().equals(a.getOrigem()) && v.getDestino().equals(a.getDestino()))
				return true;
		}
		return false;
	}
	
	private int buscaCavaleiro(ArrayList<CavaleiroOuro> cavaleiros, CavaleiroOuro a){
		int i = 0;
		for(CavaleiroOuro co: cavaleiros){
			if(co.getDificuldade() == a.getDificuldade()){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private int buscaCavaleiroBronze(ArrayList<CavaleiroBronze> cavaleiros, CavaleiroBronze a){
		int i = 0;
		for(CavaleiroBronze co: cavaleiros){
			if(co.getNome() == a.getNome()){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private void resetVida(ArrayList<CavaleiroBronze> cavaleiros){
		for(CavaleiroBronze cb: cavaleiros){
			cb.setEnergia(5);
		}
	}
	
	private ArrayList<CavaleiroBronze> pegaVidaAtual(CavaleiroOuro caval){
		CavaleiroOuro cavaleiro = caval;
		
		CavaleiroBronze Shun = new CavaleiroBronze("Shun", (float) 1.2, 5, true);
		CavaleiroBronze Hyoga = new CavaleiroBronze("Hyoga", (float) 1.3, 5, true);
		CavaleiroBronze Shiryu = new CavaleiroBronze("Shiryu", (float) 1.4, 5, true);
		CavaleiroBronze Seiya = new CavaleiroBronze("Seiya", (float) 1.5, 5, true);
		CavaleiroBronze Ikki = new CavaleiroBronze("Ikki", (float) 1.1, 5, true);	
		ArrayList<CavaleiroBronze> vidas = new ArrayList<CavaleiroBronze>();
		vidas.add(Shun);
		vidas.add(Hyoga);
		vidas.add(Shiryu);
		vidas.add(Seiya);
		vidas.add(Ikki);
		
		while(cavaleiro.getPai() != null){
			for(CavaleiroBronze cb: cavaleiro.getPai().getCavaleiros()){
				if(cb.getNome() == vidas.get(0).getNome()){
					vidas.get(0).setEnergia(vidas.get(0).getEnergia()-1);
				}
				else if(cb.getNome() == vidas.get(1).getNome()){
					vidas.get(1).setEnergia(vidas.get(1).getEnergia()-1);
				}
				else if(cb.getNome() == vidas.get(2).getNome()){
					vidas.get(2).setEnergia(vidas.get(2).getEnergia()-1);
				}
				else if(cb.getNome() == vidas.get(3).getNome()){
					vidas.get(3).setEnergia(vidas.get(3).getEnergia()-1);
				}
				else if(cb.getNome() == vidas.get(4).getNome()){
					vidas.get(4).setEnergia(vidas.get(4).getEnergia()-1);
				}
			}
			cavaleiro = cavaleiro.getPai().getOrigem();
		}
		return vidas;
	}
}
