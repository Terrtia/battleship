package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Model;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	private JMenu menu1 = new JMenu("Fichier");
	private JMenuItem item1 = new JMenuItem("Ouvrir");
	private JMenuItem item2 = new JMenuItem("Enregistrer");
	private JMenu menu2 = new JMenu("Partie");
	private JMenuItem item3 = new JMenuItem("Nouvelle Partie");
	private Model model;

	public Menu(final Model m){
		super();
		this.model = m;
		//charger
		item1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					m.charger();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
	    	  });

		//sauvegarder
		item2.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		m.sauvegarder();
	    	  }
		});

		item3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				NewGame ng = new NewGame(model);
			}
		});

		this.menu1.add(item1);
		this.menu1.addSeparator();
		this.menu1.add(item2); 
		this.menu1.addSeparator();

		this.menu2.add(item3);

		this.add(menu1);
		this.add(menu2);
	}
}
