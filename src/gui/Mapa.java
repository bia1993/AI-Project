package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import cavaleiros.CriaCavaleiros;
import estruturas.ArestaBatalhas;
import estruturas.Vertice;

/* 
 * Toda a parte gráfica é gerada aqui. As imagens e os sons são carregados e exibidos na tela em seu devido momento. A execução do
 * A* do caminho é exibida aqui. O caminho já vem pronto uma vez que o algoritmo é executado em Jogo.java, mas pegamos ele aqui e
 * através do método segueCaminho() atualizamos a posição do personagem para que possamos ver ele andando pelo mapa conforme o 
 * caminho escolhido pelo A*.
 * 
 * */

@SuppressWarnings("serial")
public class Mapa extends JPanel {
	ArrayList<ArestaBatalhas> resultadoBatalhas = Jogo.res;
	static boolean iniciar = false;
	boolean fim = false;
	public static char[][] mapa = Arquivos.pegaMapa();
    private static Mapa instancia = null;
    int linha, coluna, coordX = Arquivos.pegaIniX(), coordY = Arquivos.pegaIniY();
    int i, j, k = 0, tamanho = 32;
    int ligarCamera = 1;
    int x = Arquivos.pegaIniX(), y = Arquivos.pegaIniY();
    BufferedReader entradaMapa = null;
    static char direcao = 'S';
    static Image agenteShun = null, hyoga = null, shiryu = null, seiya = null,ikki = null;
    private Image[] shunFramesN = new Image[3];
    private Image[] shunFramesS = new Image[3];
    private Image[] shunFramesL = new Image[3];
    private Image[] shunFramesO = new Image[3];
    private Image[] hyogaFramesN = new Image[3];
    private Image[] hyogaFramesS = new Image[3];
    private Image[] hyogaFramesL = new Image[3];
    private Image[] hyogaFramesO = new Image[3];
    private Image[] shiryuFramesN = new Image[3];
    private Image[] shiryuFramesS = new Image[3];
    private Image[] shiryuFramesL = new Image[3];
    private Image[] shiryuFramesO = new Image[3];
    private Image[] seiyaFramesN = new Image[3];
    private Image[] seiyaFramesS = new Image[3];
    private Image[] seiyaFramesL = new Image[3];
    private Image[] seiyaFramesO = new Image[3];
    private Image[] ikkiFramesN = new Image[3];
    private Image[] ikkiFramesS = new Image[3];
    private Image[] ikkiFramesL = new Image[3];
    private Image[] ikkiFramesO = new Image[3];
	private Image[] shunFramesFantasmaN = new Image[3];
    private Image[] shunFramesFantasmaS = new Image[3];
    private Image[] shunFramesFantasmaL = new Image[3];
    private Image[] shunFramesFantasmaO = new Image[3];
    private Image[] hyogaFramesFantasmaN = new Image[3];
    private Image[] hyogaFramesFantasmaS = new Image[3];
    private Image[] hyogaFramesFantasmaL = new Image[3];
    private Image[] hyogaFramesFantasmaO = new Image[3];
    private Image[] shiryuFramesFantasmaN = new Image[3];
    private Image[] shiryuFramesFantasmaS = new Image[3];
    private Image[] shiryuFramesFantasmaL = new Image[3];
    private Image[] shiryuFramesFantasmaO = new Image[3];
    private Image[] seiyaFramesFantasmaN = new Image[3];
    private Image[] seiyaFramesFantasmaS = new Image[3];
    private Image[] seiyaFramesFantasmaL = new Image[3];
    private Image[] seiyaFramesFantasmaO = new Image[3];
    private Image[] ikkiFramesFantasmaN = new Image[3];
    private Image[] ikkiFramesFantasmaS = new Image[3];
    private Image[] ikkiFramesFantasmaL = new Image[3];
    private Image[] ikkiFramesFantasmaO = new Image[3];
    private int numFrame = 0;
    int passos = Jogo.tamanhoCaminho - 1;
    static int f = 0;
    Image plano = null;
    Image montanha = null;
    Image rocha = null;
    Image casa = null;
    Image mu = null;
	Image aldebaran = null;
	Image saga = null;
	Image mdm = null;
	Image aioria = null;
	Image shaka = null;
	Image dohko = null;
	Image miro = null;
	Image aioros = null;
	Image shura = null;
	Image camus = null;
	Image afrodite = null;
	Image saori = null;
	Image sagaFim = null;
	Image fundoAbertura = null;
	Image fundoFim = null;
	int[] xAnt = {coordX, coordX, coordX, coordX};
	int[] yAnt = {coordY, coordY, coordY, coordY};
	AudioInputStream correnteDeAndromeda = null, poDeDiamante = null, coleraDoDragao = null, meteoroDePegaso = null, aveFenix = null;
	AudioInputStream musicaFundo = null, musicaAbertura = null, musicaEncerramento = null;
	Clip clipShun = null, clipHyoga = null, clipShiryu = null, clipSeiya = null, clipIkki = null;
	Clip clipFundo = null, clipAbertura =  null, clipEncerramento = null;
	int xMu, yMu, xAldebaran, yAldebaran, xSaga, ySaga, xMDM, yMDM, xAioria, yAioria, xShaka, yShaka, 
	xDohko, yDohko, xMiro, yMiro, xAioros, yAioros, xShura, yShura, xCamus, yCamus, xAfrodite, yAfrodite;
	
	ActionListener fazAnimacao = new ActionListener() {
   	 public void actionPerformed(ActionEvent evt) {
   		Image[] framesShun = new Image[3];
   		Image[] framesHyoga = new Image[3];
   		Image[] framesShiryu = new Image[3];
   		Image[] framesSeiya = new Image[3];
   		Image[] framesIkki = new Image[3];
   	
   		switch (direcao) {
   			case 'L':
   				//if(!CriaCavaleiros.Shun.isVivo())
   					//framesShun = shunFramesFantasmaL;
   				//else
   					framesShun = shunFramesL;
   				
   				framesHyoga = hyogaFramesL;
   				framesShiryu = shiryuFramesL;
   				framesSeiya = seiyaFramesL;
   				framesIkki = ikkiFramesL;
   				break;
   			case 'O':
   				framesShun = shunFramesO;
   				framesHyoga = hyogaFramesO;
   				framesShiryu = shiryuFramesO;
   				framesSeiya = seiyaFramesO;
   				framesIkki = ikkiFramesO;
   				break;
   			case 'S':
   				framesShun = shunFramesS;
   				framesHyoga = hyogaFramesS;
   				framesShiryu = shiryuFramesS;
   				framesSeiya = seiyaFramesS;
   				framesIkki = ikkiFramesS;
   				break;
   			case 'N':
   				framesShun = shunFramesN;
   				framesHyoga = hyogaFramesN;
   				framesShiryu = shiryuFramesN;
   				framesSeiya = seiyaFramesN;
   				framesIkki = ikkiFramesN;
   				break;
   			default:
   				break;
   		}
   		
   	agenteShun = framesShun[numFrame];
   	hyoga = framesHyoga[numFrame];
   	shiryu = framesShiryu[numFrame];
   	seiya = framesSeiya[numFrame];
   	ikki = framesIkki[numFrame];
   	
   	numFrame++;
		if (numFrame >= framesShun.length) 
			numFrame = 0;
		
		repaint();
   	
        }
   };

	Timer timer = new Timer(100, fazAnimacao);
    ArrayList<Vertice> caminho;
    
    public Mapa(ArrayList<Vertice> caminho){
    	
    	this.caminho = caminho;
    	carregaImagens();
   	 	timer.setInitialDelay(0);
   	 	timer.start();
   	 	carregaAudio();
   	 	exibeCusto();
   	 	exibeCustoFim();
   	 	
    }
    
    public static Mapa pegaInstancia (ArrayList<Vertice> caminho) {
        if (instancia == null) 
            instancia = new Mapa (caminho);
        return instancia;
    }
    
    public void carregaAudio () {
    	try {
			correnteDeAndromeda = AudioSystem.getAudioInputStream(new File("src/audio/corrente_de_andromeda.wav"));
			poDeDiamante = AudioSystem.getAudioInputStream(new File("src/audio/po_de_diamante.wav"));
			coleraDoDragao = AudioSystem.getAudioInputStream(new File("src/audio/colera_do_dragao.wav"));
			meteoroDePegaso = AudioSystem.getAudioInputStream(new File("src/audio/meteoro_de_pegaso.wav"));
			aveFenix = AudioSystem.getAudioInputStream(new File("src/audio/ave_fenix.wav"));
			musicaFundo = AudioSystem.getAudioInputStream(new File("src/audio/saint_seiya_bgmusic.wav"));
			musicaAbertura = AudioSystem.getAudioInputStream(new File("src/audio/pegasus_fantasy.wav"));
			musicaEncerramento = AudioSystem.getAudioInputStream(new File("src/audio/encerramento.wav"));
			
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
    	try {
			clipShun = AudioSystem.getClip();
			clipHyoga = AudioSystem.getClip();
			clipShiryu = AudioSystem.getClip();
			clipSeiya = AudioSystem.getClip();
			clipIkki = AudioSystem.getClip();
			clipFundo = AudioSystem.getClip();
			clipAbertura = AudioSystem.getClip();
			clipEncerramento = AudioSystem.getClip();
			
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
    	try {
			clipShun.open(correnteDeAndromeda);
			clipHyoga.open(poDeDiamante);
			clipShiryu.open(coleraDoDragao);
			clipSeiya.open(meteoroDePegaso);
			clipIkki.open(aveFenix);
			clipFundo.open(musicaFundo);
			clipAbertura.open(musicaAbertura);
			clipEncerramento.open(musicaEncerramento);
			
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void carregaImagens () {
        
		try {
			plano = ImageIO.read(new File("src/imagens/normal_tile.jpg"));
		    montanha = ImageIO.read(new File("src/imagens/mountain_tile.png"));
		    rocha = ImageIO.read(new File("src/imagens/rock_tile.jpg"));
		    agenteShun = ImageIO.read(new File("src/imagens/shunS2.png"));
		    casa = ImageIO.read(new File("src/imagens/casacdz.png"));
		    mu = ImageIO.read(new File("src/imagens/mu.png"));
			aldebaran = ImageIO.read(new File("src/imagens/aldebaran.png"));
			saga = ImageIO.read(new File("src/imagens/gemini.png"));
			mdm = ImageIO.read(new File("src/imagens/mdm.png"));
			aioria = ImageIO.read(new File("src/imagens/aioria.png"));
			shaka = ImageIO.read(new File("src/imagens/shaka.png"));
			dohko = ImageIO.read(new File("src/imagens/dohko.png"));
			miro = ImageIO.read(new File("src/imagens/miro.png"));
			aioros = ImageIO.read(new File("src/imagens/aioros.png"));
			shura = ImageIO.read(new File("src/imagens/shura.png"));
			camus = ImageIO.read(new File("src/imagens/camus.png"));
			afrodite = ImageIO.read(new File("src/imagens/afrodite.png"));
		    shunFramesN[0] = ImageIO.read(new File("src/imagens/shunN1.png"));
		    shunFramesN[1] = ImageIO.read(new File("src/imagens/shunN2.png"));
		    shunFramesN[2] = ImageIO.read(new File("src/imagens/shunN3.png"));
		    shunFramesS[0] = ImageIO.read(new File("src/imagens/shunS1.png"));
		    shunFramesS[1] = ImageIO.read(new File("src/imagens/shunS2.png"));
		    shunFramesS[2] = ImageIO.read(new File("src/imagens/shunS3.png"));
		    shunFramesL[0] = ImageIO.read(new File("src/imagens/shunL1.png"));
		    shunFramesL[1] = ImageIO.read(new File("src/imagens/shunL2.png"));
		    shunFramesL[2] = ImageIO.read(new File("src/imagens/shunL3.png"));
		    shunFramesO[0] = ImageIO.read(new File("src/imagens/shunO1.png"));
		    shunFramesO[1] = ImageIO.read(new File("src/imagens/shunO2.png"));
		    shunFramesO[2] = ImageIO.read(new File("src/imagens/shunO3.png"));
		    hyogaFramesN[0] = ImageIO.read(new File("src/imagens/hyogaN1.png"));
		    hyogaFramesN[1] = ImageIO.read(new File("src/imagens/hyogaN2.png"));
		    hyogaFramesN[2] = ImageIO.read(new File("src/imagens/hyogaN3.png"));
		    hyogaFramesS[0] = ImageIO.read(new File("src/imagens/hyogaS1.png"));
		    hyogaFramesS[1] = ImageIO.read(new File("src/imagens/hyogaS2.png"));
		    hyogaFramesS[2] = ImageIO.read(new File("src/imagens/hyogaS3.png"));
		    hyogaFramesL[0] = ImageIO.read(new File("src/imagens/hyogaL1.png"));
		    hyogaFramesL[1] = ImageIO.read(new File("src/imagens/hyogaL2.png"));
		    hyogaFramesL[2] = ImageIO.read(new File("src/imagens/hyogaL3.png"));
		    hyogaFramesO[0] = ImageIO.read(new File("src/imagens/hyogaO1.png"));
		    hyogaFramesO[1] = ImageIO.read(new File("src/imagens/hyogaO2.png"));
		    hyogaFramesO[2] = ImageIO.read(new File("src/imagens/hyogaO3.png")); 
			shiryuFramesN[0] = ImageIO.read(new File("src/imagens/shiryuN1.png"));
		    shiryuFramesN[1] = ImageIO.read(new File("src/imagens/shiryuN2.png"));
		    shiryuFramesN[2] = ImageIO.read(new File("src/imagens/shiryuN3.png"));
		    shiryuFramesS[0] = ImageIO.read(new File("src/imagens/shiryuS1.png"));
		    shiryuFramesS[1] = ImageIO.read(new File("src/imagens/shiryuS2.png"));
		    shiryuFramesS[2] = ImageIO.read(new File("src/imagens/shiryuS3.png"));
		    shiryuFramesL[0] = ImageIO.read(new File("src/imagens/shiryuL1.png"));
		    shiryuFramesL[1] = ImageIO.read(new File("src/imagens/shiryuL2.png"));
		    shiryuFramesL[2] = ImageIO.read(new File("src/imagens/shiryuL3.png"));
		    shiryuFramesO[0] = ImageIO.read(new File("src/imagens/shiryuO1.png"));
		    shiryuFramesO[1] = ImageIO.read(new File("src/imagens/shiryuO2.png"));
		    shiryuFramesO[2] = ImageIO.read(new File("src/imagens/shiryuO3.png"));
		    seiyaFramesN[0] = ImageIO.read(new File("src/imagens/seiyaN1.png"));
		    seiyaFramesN[1] = ImageIO.read(new File("src/imagens/seiyaN2.png"));
		    seiyaFramesN[2] = ImageIO.read(new File("src/imagens/seiyaN3.png"));
		    seiyaFramesS[0] = ImageIO.read(new File("src/imagens/seiyaS1.png"));
		    seiyaFramesS[1] = ImageIO.read(new File("src/imagens/seiyaS2.png"));
		    seiyaFramesS[2] = ImageIO.read(new File("src/imagens/seiyaS3.png"));
		    seiyaFramesL[0] = ImageIO.read(new File("src/imagens/seiyaL1.png"));
		    seiyaFramesL[1] = ImageIO.read(new File("src/imagens/seiyaL2.png"));
		    seiyaFramesL[2] = ImageIO.read(new File("src/imagens/seiyaL3.png"));
		    seiyaFramesO[0] = ImageIO.read(new File("src/imagens/seiyaO1.png"));
		    seiyaFramesO[1] = ImageIO.read(new File("src/imagens/seiyaO2.png"));
		    seiyaFramesO[2] = ImageIO.read(new File("src/imagens/seiyaO3.png"));
			ikkiFramesN[0] = ImageIO.read(new File("src/imagens/ikkiN1.png"));
		    ikkiFramesN[1] = ImageIO.read(new File("src/imagens/ikkiN2.png"));
		    ikkiFramesN[2] = ImageIO.read(new File("src/imagens/ikkiN3.png"));
		    ikkiFramesS[0] = ImageIO.read(new File("src/imagens/ikkiS1.png"));
		    ikkiFramesS[1] = ImageIO.read(new File("src/imagens/ikkiS2.png"));
		    ikkiFramesS[2] = ImageIO.read(new File("src/imagens/ikkiS3.png"));
		    ikkiFramesL[0] = ImageIO.read(new File("src/imagens/ikkiL1.png"));
		    ikkiFramesL[1] = ImageIO.read(new File("src/imagens/ikkiL2.png"));
		    ikkiFramesL[2] = ImageIO.read(new File("src/imagens/ikkiL3.png"));
		    ikkiFramesO[0] = ImageIO.read(new File("src/imagens/ikkiO1.png"));
		    ikkiFramesO[1] = ImageIO.read(new File("src/imagens/ikkiO2.png"));
		    ikkiFramesO[2] = ImageIO.read(new File("src/imagens/ikkiO3.png"));
			shunFramesFantasmaN[0] = ImageIO.read(new File("src/imagens/shun_ghostN1.png"));
		    shunFramesFantasmaN[1] = ImageIO.read(new File("src/imagens/shun_ghostN2.png"));
		    shunFramesFantasmaN[2] = ImageIO.read(new File("src/imagens/shun_ghostN3.png"));
		    shunFramesFantasmaS[0] = ImageIO.read(new File("src/imagens/shun_ghostS1.png"));
		    shunFramesFantasmaS[1] = ImageIO.read(new File("src/imagens/shun_ghostS2.png"));
		    shunFramesFantasmaS[2] = ImageIO.read(new File("src/imagens/shun_ghostS3.png"));
		    shunFramesFantasmaL[0] = ImageIO.read(new File("src/imagens/shun_ghostL1.png"));
		    shunFramesFantasmaL[1] = ImageIO.read(new File("src/imagens/shun_ghostL2.png"));
		    shunFramesFantasmaL[2] = ImageIO.read(new File("src/imagens/shun_ghostL3.png"));
		    shunFramesFantasmaO[0] = ImageIO.read(new File("src/imagens/shun_ghostO1.png"));
		    shunFramesFantasmaO[1] = ImageIO.read(new File("src/imagens/shun_ghostO2.png"));
		    shunFramesFantasmaO[2] = ImageIO.read(new File("src/imagens/shun_ghostO3.png"));
		    hyogaFramesFantasmaN[0] = ImageIO.read(new File("src/imagens/hyoga_ghostN1.png"));
		    hyogaFramesFantasmaN[1] = ImageIO.read(new File("src/imagens/hyoga_ghostN2.png"));
		    hyogaFramesFantasmaN[2] = ImageIO.read(new File("src/imagens/hyoga_ghostN3.png"));
		    hyogaFramesFantasmaS[0] = ImageIO.read(new File("src/imagens/hyoga_ghostS1.png"));
		    hyogaFramesFantasmaS[1] = ImageIO.read(new File("src/imagens/hyoga_ghostS2.png"));
		    hyogaFramesFantasmaS[2] = ImageIO.read(new File("src/imagens/hyoga_ghostS3.png"));
		    hyogaFramesFantasmaL[0] = ImageIO.read(new File("src/imagens/hyoga_ghostL1.png"));
		    hyogaFramesFantasmaL[1] = ImageIO.read(new File("src/imagens/hyoga_ghostL2.png"));
		    hyogaFramesFantasmaL[2] = ImageIO.read(new File("src/imagens/hyoga_ghostL3.png"));
		    hyogaFramesFantasmaO[0] = ImageIO.read(new File("src/imagens/hyoga_ghostO1.png"));
		    hyogaFramesFantasmaO[1] = ImageIO.read(new File("src/imagens/hyoga_ghostO2.png"));
		    hyogaFramesFantasmaO[2] = ImageIO.read(new File("src/imagens/hyoga_ghostO3.png")); 
			shiryuFramesFantasmaN[0] = ImageIO.read(new File("src/imagens/shiryu_ghostN1.png"));
		    shiryuFramesFantasmaN[1] = ImageIO.read(new File("src/imagens/shiryu_ghostN2.png"));
		    shiryuFramesFantasmaN[2] = ImageIO.read(new File("src/imagens/shiryu_ghostN3.png"));
		    shiryuFramesFantasmaS[0] = ImageIO.read(new File("src/imagens/shiryu_ghostS1.png"));
		    shiryuFramesFantasmaS[1] = ImageIO.read(new File("src/imagens/shiryu_ghostS2.png"));
		    shiryuFramesFantasmaS[2] = ImageIO.read(new File("src/imagens/shiryu_ghostS3.png"));
		    shiryuFramesFantasmaL[0] = ImageIO.read(new File("src/imagens/shiryu_ghostL1.png"));
		    shiryuFramesFantasmaL[1] = ImageIO.read(new File("src/imagens/shiryu_ghostL2.png"));
		    shiryuFramesFantasmaL[2] = ImageIO.read(new File("src/imagens/shiryu_ghostL3.png"));
		    shiryuFramesFantasmaO[0] = ImageIO.read(new File("src/imagens/shiryu_ghostO1.png"));
		    shiryuFramesFantasmaO[1] = ImageIO.read(new File("src/imagens/shiryu_ghostO2.png"));
		    shiryuFramesFantasmaO[2] = ImageIO.read(new File("src/imagens/shiryu_ghostO3.png"));
		    seiyaFramesFantasmaN[0] = ImageIO.read(new File("src/imagens/seiya_ghostN1.png"));
		    seiyaFramesFantasmaN[1] = ImageIO.read(new File("src/imagens/seiya_ghostN2.png"));
		    seiyaFramesFantasmaN[2] = ImageIO.read(new File("src/imagens/seiya_ghostN3.png"));
		    seiyaFramesFantasmaS[0] = ImageIO.read(new File("src/imagens/seiya_ghostS1.png"));
		    seiyaFramesFantasmaS[1] = ImageIO.read(new File("src/imagens/seiya_ghostS2.png"));
		    seiyaFramesFantasmaS[2] = ImageIO.read(new File("src/imagens/seiya_ghostS3.png"));
		    seiyaFramesFantasmaL[0] = ImageIO.read(new File("src/imagens/seiya_ghostL1.png"));
		    seiyaFramesFantasmaL[1] = ImageIO.read(new File("src/imagens/seiya_ghostL2.png"));
		    seiyaFramesFantasmaL[2] = ImageIO.read(new File("src/imagens/seiya_ghostL3.png"));
		    seiyaFramesFantasmaO[0] = ImageIO.read(new File("src/imagens/seiya_ghostO1.png"));
		    seiyaFramesFantasmaO[1] = ImageIO.read(new File("src/imagens/seiya_ghostO2.png"));
		    seiyaFramesFantasmaO[2] = ImageIO.read(new File("src/imagens/seiya_ghostO3.png"));
			ikkiFramesFantasmaN[0] = ImageIO.read(new File("src/imagens/ikki_ghostN1.png"));
		    ikkiFramesFantasmaN[1] = ImageIO.read(new File("src/imagens/ikki_ghostN2.png"));
		    ikkiFramesFantasmaN[2] = ImageIO.read(new File("src/imagens/ikki_ghostN3.png"));
		    ikkiFramesFantasmaS[0] = ImageIO.read(new File("src/imagens/ikki_ghostS1.png"));
		    ikkiFramesFantasmaS[1] = ImageIO.read(new File("src/imagens/ikki_ghostS2.png"));
		    ikkiFramesFantasmaS[2] = ImageIO.read(new File("src/imagens/ikki_ghostS3.png"));
		    ikkiFramesFantasmaL[0] = ImageIO.read(new File("src/imagens/ikki_ghostL1.png"));
		    ikkiFramesFantasmaL[1] = ImageIO.read(new File("src/imagens/ikki_ghostL2.png"));
		    ikkiFramesFantasmaL[2] = ImageIO.read(new File("src/imagens/ikki_ghostL3.png"));
		    ikkiFramesFantasmaO[0] = ImageIO.read(new File("src/imagens/ikki_ghostO1.png"));
		    ikkiFramesFantasmaO[1] = ImageIO.read(new File("src/imagens/ikki_ghostO2.png"));
		    ikkiFramesFantasmaO[2] = ImageIO.read(new File("src/imagens/ikki_ghostO3.png")); 
		    saori = ImageIO.read(new File("src/imagens/saori.png"));
		    sagaFim = ImageIO.read(new File("src/imagens/saga_evil.png"));
		    fundoAbertura = ImageIO.read(new File("src/imagens/saintseiya.jpg"));
		    fundoFim = ImageIO.read(new File("src/imagens/end.jpg"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
    }
    
    public void exibeCusto () {
		final JLabel mostraCusto = new JLabel();
		final Color cor = new Color(247,213,243);
		mostraCusto.setSize(100, 50);
		mostraCusto.setOpaque(false);
		mostraCusto.setForeground(Color.BLACK);
		mostraCusto.setFont(new Font("Century Gothic", Font.BOLD, 14));
		this.add(mostraCusto);
		Timer timerCusto = new Timer(1, new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				if (Mapa.f > 0)
					mostraCusto.setForeground(cor);
				mostraCusto.setText(String.valueOf(Mapa.f));
				if (fim) {
					mostraCusto.setVisible(false);
				}
			}
		});
		timerCusto.start();
		
	}
    
    public void exibeCustoFim () {
    	final JLabel custos = new JLabel();
		final Color cor = new Color(247,213,243);
		custos.setSize(100, 50);
		custos.setOpaque(false);
		custos.setForeground(cor);
		custos.setFont(new Font("Century Gothic", Font.BOLD, 14));
		this.add(custos);
		Timer timerCusto = new Timer(1, new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				if (fim) {
					float total = Jogo.custoCaminho + Jogo.custoB;
					String custoTudo = "<HTML>Custo do caminho: "+Jogo.custoCaminho+"<BR> Custo das batalhas: "
							+Jogo.custoB+"<BR> CustoTotal: "+total+ "</HTML>";
								
					if (total > 720)
						custoTudo = "<HTML>Custo do caminho: "+Jogo.custoCaminho+"<BR> Custo das batalhas: "
								+Jogo.custoB+"<BR> CustoTotal: "+total+ "<BR> Perdoe-nos, Athena! :'(</HTML>";
					
					
					custos.setText(custoTudo);
					custos.setVisible(true);
				}
			}
		});
		timerCusto.start();
		
		
    }
    
    protected void paintComponent (Graphics g2) { 
    	Camera.setPosition(x, y);
    	int xOffset = ((Camera.getX() * 17));
    	int yOffset = ((Camera.getY() * 17));
    	
    	Graphics2D g = (Graphics2D) g2;
        super.paintComponent(g);
		
        for (linha = 0; linha < 42; linha++) { 
        	for (coluna = 0; coluna < 42; coluna++){ 
            	switch (mapa[linha][coluna]) {
            		case 'p':
            			g.drawImage(plano, 10+(tamanho * coluna) - xOffset, 10+(tamanho * linha) - yOffset, tamanho, tamanho, null);
            			break;
            		case 'm':
            			g.drawImage(montanha,10+(tamanho * coluna) - xOffset, 10+(tamanho * linha) - yOffset, tamanho, tamanho, null);
            			break;
            		case 'r':
            			g.drawImage(rocha,10+(tamanho * coluna) - xOffset, 10+(tamanho * linha) - yOffset, tamanho, tamanho, null);
            			break;
            		default:
            			g.drawImage(plano, 10+(tamanho * coluna) - xOffset, 10+(tamanho * linha) - yOffset, tamanho, tamanho, null);
            			break;
            		
            	}
            	
        	}   	
        		
        }
        /* Desenha casas dos Cavaleiros de Ouro. */
        for (i = 0; i < 42; i++) {
        	for (j = 0; j < 42; j++) {
        		if (mapa[i][j] == 'U' || mapa[i][j] == 'A' || mapa[i][j] == 'S' || mapa[i][j] == 'D'
        				|| mapa[i][j] == 'I' || mapa[i][j] == 'K' || mapa[i][j] == 'O' || mapa[i][j] == 'M'
        				|| mapa[i][j] == 'R' || mapa[i][j] == 'H' || mapa[i][j] == 'C' || mapa[i][j] == 'F')
        			g.drawImage(casa, 5 + (tamanho * j) - xOffset, (tamanho * i) - 12 - yOffset, 45, 58, null);  
        	}
        }
        
        for (i = 0; i < 42; i++) {
        	for (j = 0; j < 42; j++) {
        		switch (mapa[i][j]) {
	        		case 'U':
	        			xMu = i;
	        			yMu = j;
	        			if (CriaCavaleiros.Mu.isVivo())
	        				g.drawImage(mu, 1 + (tamanho * j) - xOffset, (tamanho * i) - yOffset - 12, 52, 63, null);
	        			break;
	        		case 'A':
	        			xAldebaran = i;
	        			yAldebaran = j;
	        			if (CriaCavaleiros.Aldebaran.isVivo())
	        				g.drawImage(aldebaran, (tamanho * j) - xOffset - 3, (tamanho * i) - yOffset - 14, 58, 65, null);
	        			break;
	        		case 'S':
	        			xSaga = i;
	        			ySaga = j;
	        			if (CriaCavaleiros.Saga.isVivo())
	        				g.drawImage(saga, 1 + (tamanho * j) - xOffset, (tamanho * i) - yOffset - 12, 52, 63, null);
	        			break;
	        		case 'D':
	        			xMDM = i;
	        			yMDM = j;
	        			if (CriaCavaleiros.MDM.isVivo())
	        				g.drawImage(mdm, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset - 12, 52, 65, null);
	        			break;
	        		case 'I':
	        			xAioria = i;
	        			yAioria = j;
	        			if (CriaCavaleiros.Aioria.isVivo())
	        				g.drawImage(aioria, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset - 12, 52, 63, null);
	        			break;
	        		case 'K':
	        			xShaka = i;
	        			yShaka = j;
	        			if (CriaCavaleiros.Shaka.isVivo())
	        				g.drawImage(shaka, (tamanho * j) - xOffset - 2, 1 + (tamanho * i) - yOffset - 12, 55, 68, null);
	        			break;
	        		case 'O':
	        			xDohko = i;
	        			yDohko = j;
	        			if (CriaCavaleiros.Dohko.isVivo())
	        				g.drawImage(dohko, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset -12, 52, 66, null);
	        			break;
	        		case 'M':
	        			xMiro = i;
	        			yMiro = j;
	        			if (CriaCavaleiros.Miro.isVivo())
	        				g.drawImage(miro, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset - 12, 52, 63, null);
	        			break;
	        		case 'R':
	        			xAioros = i;
	        			yAioros = j;
	        			if (CriaCavaleiros.Aioros.isVivo())
	        				g.drawImage(aioros, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset - 12, 52, 66, null);
	        			break;
	        		case 'H':
	        			xShura = i;
	        			yShura = j;
	        			if (CriaCavaleiros.Shura.isVivo())
	        				g.drawImage(shura, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset - 12, 52, 63, null);
	        			break;
	        		case 'C':
	        			xCamus = i;
	        			yCamus = j;
	        			if (CriaCavaleiros.Camus.isVivo())
	        				g.drawImage(camus, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset - 12, 52, 63, null);
	        			break;
	        		case 'F':
	        			xAfrodite = i;
	        			yAfrodite = j;
	        			if (CriaCavaleiros.Afrodite.isVivo())
	        				g.drawImage(afrodite, 1 + (tamanho * j) - xOffset, 1 + (tamanho * i) - yOffset - 12, 52, 63, null);
	        			break;
	        		default:
	        			break;
	        	
        		}
        	}
        }
        
        if (iniciar) {
        	clipFundo.start();
        	clipAbertura.stop();
	        if(!(caminho.get(passos).getI() == Arquivos.destinoX && caminho.get(passos).getJ() == Arquivos.destinoY)) 
	        	segueCaminho();
        }
        
        g.drawImage(agenteShun,(tamanho * coordX) - xOffset, (tamanho * coordY) - 20 - yOffset, 50, 70, null); 
        if (iniciar) {
	        switch (direcao) {
	        	case 'L': 
	        		g.drawImage(hyoga,(tamanho * xAnt[0]) - xOffset, (tamanho * yAnt[0]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(shiryu,(tamanho * xAnt[1]) - xOffset, (tamanho * yAnt[1]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(seiya,(tamanho * xAnt[2]) - xOffset, (tamanho * yAnt[2]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(ikki,(tamanho * xAnt[3]) - xOffset, (tamanho * yAnt[3]) - 20 - yOffset, 50, 70, null); 
	        		break;
	        	case 'O':
	        		g.drawImage(hyoga,(tamanho * xAnt[0]) - xOffset, (tamanho * yAnt[0]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(shiryu,(tamanho * xAnt[1]) - xOffset, (tamanho * yAnt[1]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(seiya,(tamanho * xAnt[2]) - xOffset, (tamanho * yAnt[2]) - 20 - yOffset, 50, 70, null); 
	        		g.drawImage(ikki,(tamanho * xAnt[3]) - xOffset, (tamanho * yAnt[3]) - 20 - yOffset, 50, 70, null);
	        		break;
	        	case 'S': 
	        		g.drawImage(hyoga,(tamanho * xAnt[0]) - xOffset, (tamanho * yAnt[0]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(shiryu,(tamanho * xAnt[1]) - xOffset, (tamanho * yAnt[1]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(seiya,(tamanho * xAnt[2]) - xOffset, (tamanho * yAnt[2]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(ikki,(tamanho * xAnt[3]) - xOffset, (tamanho * yAnt[3]) - 20 - yOffset, 50, 70, null);
	        		break;
	        	case 'N': 
	        		g.drawImage(hyoga,(tamanho * xAnt[0]) - xOffset, (tamanho * yAnt[0]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(shiryu,(tamanho * xAnt[1]) - xOffset, (tamanho * yAnt[1]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(seiya,(tamanho * xAnt[2]) - xOffset, (tamanho * yAnt[2]) - 20 - yOffset, 50, 70, null);
	        		g.drawImage(ikki,(tamanho * xAnt[3]) - xOffset, (tamanho * yAnt[3]) - 20 - yOffset, 50, 70, null);
	        		break;
	        	default:
	        		break;
	        }
        }
        
        for (i = 0; i < 42; i++) {
        	for (j = 0; j < 42; j++) {
        		switch (mapa[i][j]) {
        			case 'e':
        				g.drawImage(saori, (tamanho * j) - xOffset + 20, 1 + (tamanho * i) - yOffset, 43, 71, null);
        				break;
        			case 'g':
        				g.drawImage(sagaFim, (tamanho * j) - xOffset + 20, 1 + (tamanho * i) - yOffset, 60, 73, null);
        				break;
        			default:
        				break;
        		}
        	}
        }
        
        tocaSomBatalha();
        
        if(!iniciar) {
        	g.drawImage(fundoAbertura, 0, 0, 700, 700, null);
        	clipAbertura.start();
        }
        if (fim) {
        	clipFundo.stop();
        	clipEncerramento.start();
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        	g.drawImage(fundoFim, 0, 0, 700, 700, null);
        }

	 	
        
   } /* Fim do método paint. */
    
    public void tocaSomBatalha () {
    	String nome;
    	if (clipShun.getFramePosition() == clipShun.getFrameLength())
    		clipShun.setFramePosition(0);
    	if (clipHyoga.getFramePosition() == clipHyoga.getFrameLength())
    		clipHyoga.setFramePosition(0);
    	if (clipShiryu.getFramePosition() == clipShiryu.getFrameLength())
    		clipShiryu.setFramePosition(0);
    	if (clipSeiya.getFramePosition() == clipSeiya.getFrameLength())
    		clipSeiya.setFramePosition(0);
    	if (clipIkki.getFramePosition() == clipIkki.getFrameLength())
    		clipIkki.setFramePosition(0);
    	
    	 if (coordX == yMu && coordY == xMu) {
     		CriaCavaleiros.Mu.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(0).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(0).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
         }
    	 
    	 if (coordX == yAldebaran && coordY == xAldebaran) {
     		CriaCavaleiros.Aldebaran.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(1).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(1).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
          	
          }
    	 
    	 if (coordX == ySaga && coordY == xSaga) {
    		 CriaCavaleiros.Saga.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(2).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(2).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
             	
         }
    	 
    	 if (coordX == yMDM && coordY == xMDM) {
    		 CriaCavaleiros.MDM.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(3).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(3).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
          	
          }
     	 
     	 if (coordX == yAioria && coordY == xAioria) {
     		CriaCavaleiros.Aioria.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(4).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(4).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
           	
           }
     	 
     	 if (coordX == yShaka && coordY == xShaka) {
     		 CriaCavaleiros.Shaka.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(5).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(5).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
              	
          }
     	 
     	if (coordX == yDohko && coordY == xDohko) {
     		CriaCavaleiros.Dohko.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(6).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(6).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
         	
         }
    	 
    	 if (coordX == yMiro && coordY == xMiro) {
    		CriaCavaleiros.Miro.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(7).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(7).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
          	
          }
    	 
    	 if (coordX == yAioros && coordY == xAioros) {
    		CriaCavaleiros.Aioros.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(8).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(8).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
             	
         }
    	 
    	 if (coordX == yShura && coordY == xShura) {
    		CriaCavaleiros.Shura.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(9).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(9).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
          	
          }
     	 
     	 if (coordX == yCamus && coordY == xCamus) {
     		CriaCavaleiros.Camus.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(10).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(10).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
           	
           }
     	 
     	 if (coordX == yAfrodite && coordY == xAfrodite) {
     		CriaCavaleiros.Afrodite.setVivo(false);
     		for (int i = 0; i < resultadoBatalhas.get(11).getCavaleiros().size(); i++) {
     			nome = resultadoBatalhas.get(11).getCavaleiros().get(i).getNome();
     			switch (nome) {
     				case "Shun":
     					clipShun.start();
     					break;
     				case "Hyoga":
     					clipHyoga.start();
     					break;
     				case "Shiryu":
     					clipShiryu.start();
     					break;
     				case "Seiya":
     					clipSeiya.start();
     					break;
     				case "Ikki":
     					clipIkki.start();
     					break;
     				default:
     					break;
     				
     			}
     			
     		}
              	
          }
    }
    
	public void segueCaminho(){
		if(passos == Jogo.tamanhoCaminho - 1) coordY = coordY - 1;
		xAnt[3] = xAnt[2];
		yAnt[3] = yAnt[2];
		xAnt[2] = xAnt[1];
		yAnt[2] = yAnt[1];
		xAnt[1] = xAnt[0];
		yAnt[1] = yAnt[0];
		xAnt[0] = coordX;
		yAnt[0] = coordY;
		if(caminho.get(passos).getI() < caminho.get(passos-1).getI()){
			y++;
			coordY = coordY + 1;
            direcao = 'S';
		}
		else if(caminho.get(passos).getI() > caminho.get(passos-1).getI()){
			y--;
			coordY = coordY - 1;
            direcao = 'N';
		}
		else if(caminho.get(passos).getJ() < caminho.get(passos-1).getJ()){
			x++;
			coordX = coordX + 1;
            direcao = 'L';
		}
		else if(caminho.get(passos).getJ() > caminho.get(passos-1).getJ()){
			x--;
			coordX = coordX - 1;
            direcao = 'O';
		}
		repaint();
		try {
			
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		passos--;
		if(passos == 1) { 
			fim = true;
			timer.stop();
			return;
			
		}
		
		Mapa.f+=caminho.get(passos).getDistanciaPai();
		System.out.println(coordX+","+coordY+" Peso: "+Mapa.f);
		
	}
	
}
