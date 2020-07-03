package gui;


import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import estruturas.Vertice;

@SuppressWarnings("serial")


public class Janela extends JFrame {
	ArrayList<Vertice> caminho;
	private static Janela instancia = null;
	public static Janela pegaInstancia(ArrayList<Vertice> caminho) {
		if (instancia == null)
			instancia = new Janela(caminho);
		return instancia;
	}
	
	
	public Janela(ArrayList<Vertice> caminho) {
		int largura = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		largura = largura/2 - 350;
		Color cor = new Color(247,186,239);
		Menu menu = Menu.pegaInstancia();
		this.caminho = caminho;
		this.setBounds(largura, 0, 700, 744);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Mapa m = Mapa.pegaInstancia(caminho);
		m.setBackground(cor);
		this.add(m);
		this.setTitle("Shun é o melhor!");
		this.setJMenuBar(menu.barraDeMenu);
		this.setVisible(true);
	}

}
