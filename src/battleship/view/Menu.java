package battleship.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import battleship.model.Model;


/**
 * 
 * Classe représentant la barre de menu
 *
 */
public class Menu extends JMenuBar {
	private JMenu menu1 = new JMenu("Fichier");
	private JMenuItem item1 = new JMenuItem("Ouvrir");
	private JMenuItem item2 = new JMenuItem("Enregistrer");
	private JMenu menu2 = new JMenu("Partie");
	private JMenuItem item3 = new JMenuItem("Nouvelle Partie");
	private Model model;

	/**
	 * Constructeur du menu selon un model
	 * @param m
	 * le model
	 */
	public Menu(final Model m){
		super();
		this.model = m;
		//charger
		item1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser chooser = new JFileChooser();
					File fichier;
					
					fichier = chooser.getSelectedFile();
					if (chooser.showOpenDialog(null)== 
						    JFileChooser.APPROVE_OPTION) {
						    fichier = chooser.getSelectedFile();
						    m.charger(fichier.getPath());
						}
				
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
	    	  });

		//sauvegarder
		item2.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		
	    		  JFileChooser chooser = new JFileChooser();
	    		  int retrival = chooser.showSaveDialog(null);
	    		    if (retrival == JFileChooser.APPROVE_OPTION) {
	    		        try {
	    		        	m.sauvegarder(chooser.getSelectedFile().getPath());
	    		        } catch (Exception ex) {
	    		            ex.printStackTrace();
	    		        }
	    		    }
	    		  
	    		 /* try(FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt")) {
	    			  fw.write(sb.toString());
	    		  }*/
	    		
	    	  }
		});
		//nouvel partie
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
