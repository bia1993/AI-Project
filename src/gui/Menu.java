package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu extends AbstractAction {

	JMenuBar barraDeMenu =  new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem iniciar = new JMenuItem("Iniciar");
	
	private static Menu instancia = null;
	
	public static Menu pegaInstancia () {
        if (instancia == null) 
            instancia = new Menu ();
        return instancia;
    }
	
	private Menu () {
		this.iniciar.addActionListener(this);
		this.menu.add(this.iniciar);
		iniciar.setMnemonic(KeyEvent.VK_I);
		this.barraDeMenu.add(this.menu);
		
	}
	
	public void actionPerformed(ActionEvent a) {
		
		if (a.getActionCommand() == "Iniciar") {
			Mapa.iniciar = true;
		}
	}
}
