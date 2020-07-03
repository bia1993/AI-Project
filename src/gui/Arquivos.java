package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Arquivos {
	
	public static char[][] mapa = new char[42][42];
	public static char[] mapaArray = new char[1764];
    static BufferedReader entradaMapa = null; 
    static int[] dificuldadeCasas = new int[12];
    static float[] cosmo = new float[5];
    static int i, j, k = 0;
    static int coordX, coordY, destinoX, destinoY;
    static Scanner entradaCosmo;
	static Scanner entradaDificuldade;
	
    public static void leCosmo () {
    	try {
			entradaCosmo = new Scanner(new FileReader("cosmo.txt"));
			for (i = 0; i < 5; i++) {
				cosmo[i] = Float.parseFloat(entradaCosmo.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static void leDificuldade () {
    	try {
			entradaDificuldade = new Scanner(new FileReader("dificuldade.txt"));
			for (i = 0; i < 12; i++){
				dificuldadeCasas[i] = entradaDificuldade.nextInt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static void leMapa () {
		   try {
			    entradaMapa = new BufferedReader(new FileReader("mapa.txt"));
			    for(i = 0; i < 1764; i++) {
			    	entradaMapa.read(mapaArray);
			   	}
			} catch (IOException e) {
				return;
			}
		   
		   for (i = 0; i < 42; i++) {
					for (j = 0; j < 42; j++) {
						mapa[i][j] = mapaArray[k];
						if (mapa[i][j] == 'e') {
							coordX = i;
							coordY = j;
						}
						if(mapa[i][j] == 'g') {
							destinoX = i;
							destinoY = j;
						}
						k++;
					}
			}
	   }
	   
	   public static char[][] pegaMapa () {
		   leMapa();
		   return mapa;
	   }
	   /* Pega dificuldade das casas na ordem que aparecem: de Aries até Peixes. */
	   public static int[] pegaDificuldade () {
		   leDificuldade();
		   return dificuldadeCasas;
	   }
	   /* Pega cosmo dos cavaleiros na ordem em que aparecem na fila: Shun, Hyoga, Shiryu, Seiya, Ikki. */
	   public static float[] pegaCosmo () {
		   leCosmo();
		   return cosmo;
	   }
	   
	   public static int pegaIniX () {
		   return coordX;
	   }
	   public static int pegaIniY () {
		   return coordY;
	   }
}
