package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import cavaleiros.CavaleiroBronze;
import cavaleiros.CavaleiroOuro;
import buscas.BuscaBatalhas;
import buscas.BuscaCaminho;
import estruturas.ArestaBatalhas;
import estruturas.Grafo;
import estruturas.Vertice;

public class Jogo {
	/* Passos - o tamanho do caminho, ou seja, a quantidade de casas a serem percorridas no total. */
	static int tamanhoCaminho;
	static Grafo gb = new Grafo();
	static int[] dificuldadeCasas = Arquivos.pegaDificuldade();
	static float[] cosmo = Arquivos.pegaCosmo();
	static ArrayList<ArestaBatalhas> res = new ArrayList<ArestaBatalhas>();
	static float custoB;
	static int custoCaminho;
	
	@SuppressWarnings("resource")
	public static void main (String[] args) throws IOException{
		final int HEIGHT = 42;
		final int WIDTH = 42;
		char[] mapaArray = new char[HEIGHT * WIDTH];
		char[][] mapa = new char[HEIGHT][WIDTH];
		int i, j, k = 0;
		BufferedReader entradaMapa = null; 
				
		Grafo g = new Grafo();
		int iOrigem = 0, jOrigem = 0, iDestino = 0, jDestino = 0;
		
		try {
			entradaMapa = new BufferedReader(new FileReader("mapa.txt"));
			for (i = 0; i < HEIGHT * WIDTH; i++) {
				mapaArray[i] = (char) entradaMapa.read();
			}
			
			for (i = 0; i < HEIGHT; i++) {
				for (j = 0; j < WIDTH; j++) {
					mapa[i][j] = mapaArray[k];
					if(mapa[i][j] == 'e') {
						iOrigem = i;
						jOrigem = j;
					}
					if(mapa[i][j] == 'g') {
						iDestino = i;
						jDestino = j;
					}
						
					g.addVertice((char)mapaArray[k], i, j);
					k++;
				}
			}
			
			g.geraGrafo(WIDTH, HEIGHT, mapa);
			
		} catch (FileNotFoundException e) {
			return;
		}
		
		System.out.println("Executa o caminho do vértice ("+iOrigem+","+jOrigem+") ao ("+iDestino+","+jDestino+"):\n");
		
		BuscaCaminho bc = new BuscaCaminho(g, new Vertice('e', iOrigem, jOrigem), new Vertice('g', iDestino, jDestino));
		 
		ArrayList<Vertice> caminho = bc.caminho;
		Jogo.tamanhoCaminho = bc.caminho.size();
		Janela.pegaInstancia(caminho);
		int total = 0;
		g = null; 
		
		executaBatalhas();
		
		for(Vertice x: caminho){
			total += x.getDistanciaPai();
		}
		float custoTotal = total + custoB;
		custoCaminho = total;
		System.out.println("Custo total caminho:" + total);
		System.out.println("Total: " + custoTotal);
	}
	
	public static void executaBatalhas () {
		int i;
		CavaleiroBronze Shun = new CavaleiroBronze("Shun", cosmo[0], 5, true);
		CavaleiroBronze Hyoga = new CavaleiroBronze("Hyoga", cosmo[1], 5, true);
		CavaleiroBronze Shiryu = new CavaleiroBronze("Shiryu", cosmo[2], 5, true);
		CavaleiroBronze Seiya = new CavaleiroBronze("Seiya", cosmo[3], 5, true);
		CavaleiroBronze Ikki = new CavaleiroBronze("Ikki", cosmo[4], 5, true);
		
		CavaleiroOuro Mu = gb.addVerticeBatalhas(dificuldadeCasas[0], true, 0);
		CavaleiroOuro Aldebaran = gb.addVerticeBatalhas(dificuldadeCasas[1], true, 0);
		CavaleiroOuro Saga = gb.addVerticeBatalhas(dificuldadeCasas[2], true, 0);
		CavaleiroOuro MDM = gb.addVerticeBatalhas(dificuldadeCasas[3], true, 0);
		CavaleiroOuro Aioria = gb.addVerticeBatalhas(dificuldadeCasas[4], true, 0);
		CavaleiroOuro Shaka = gb.addVerticeBatalhas(dificuldadeCasas[5], true, 0);
		CavaleiroOuro Dohko = gb.addVerticeBatalhas(dificuldadeCasas[6], true, 0);
		CavaleiroOuro Miro = gb.addVerticeBatalhas(dificuldadeCasas[7], true, 0);
		CavaleiroOuro Aioros = gb.addVerticeBatalhas(dificuldadeCasas[8], true, 0);
		CavaleiroOuro Shura = gb.addVerticeBatalhas(dificuldadeCasas[9], true, 0);
		CavaleiroOuro Camus = gb.addVerticeBatalhas(dificuldadeCasas[10], true, 0);
		CavaleiroOuro Afrodite = gb.addVerticeBatalhas(dificuldadeCasas[11], true, 0);
		CavaleiroOuro Nulo = gb.addVerticeBatalhas(0, true,  0);

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
		
		Collections.sort(gb.getCavaleiros(), Grafo.CavaleiroOuroSort);
		Collections.sort(gb.getArestasBatalhas(), Grafo.ArestaBatalhasSort);
		for(i = 0; i < 12; i++){	
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb1);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb2);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb3);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb4);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb5);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb6);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb7);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb8);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb9);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb10);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb11);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb12);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb13);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb14);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb15);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb16);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb17);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb18);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb19);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb20);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb21);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb22);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb23);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb24);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb25);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb26);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb27);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb28);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb29);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb30);
			gb.addArestaBatalhas(gb.getCavaleiros().get(i), gb.getCavaleiros().get(i+1), cb31);
		}
		
		
		BuscaBatalhas bb = new BuscaBatalhas(gb, Nulo);
		System.out.println("--Caminho das batalhas--");
		float custoBatalhas = 0;
		for(ArestaBatalhas aresta: bb.caminho){
			float somaCosmo = 0;
			for(CavaleiroBronze cav: aresta.getCavaleiros()){
				System.out.println(cav.getNome()+", "+cav.getCosmo() + " - Inimigo: " + aresta.getOrigem().getDificuldade());
				somaCosmo+=cav.getCosmo();
			}
			System.out.println(somaCosmo);
			custoBatalhas += aresta.getOrigem().getDificuldade()/somaCosmo;
			System.out.println("Custo: " + custoBatalhas);
		}
		System.out.println("Custo total batalhas: " + custoBatalhas);
		Jogo.custoB = custoBatalhas;
		ordenaResultado(bb);
		
	} /* Fim main. */
	
	public static void ordenaResultado (BuscaBatalhas bb) {
		for(ArestaBatalhas aresta: bb.caminho) {
			for (int i = 0; i < 12; i++) {
				if (aresta.getOrigem().getDificuldade() == dificuldadeCasas[i])
					Jogo.res.add(aresta);
			}
		}
	}
	
}

