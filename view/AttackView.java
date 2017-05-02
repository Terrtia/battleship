package battleship.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import battleship.model.Model;
import battleship.model.ships.Ship;

/**
 * 
 * Classe représnetant la fenetre d'attaque
 *
 */
public class AttackView extends JFrame implements Observer {
	/**
	 * le model observé
	 */
	private Model model;
	/**
	 * le panneau de tir
	 */
	private JPanel panel;
	
	
	
	/**
	 * le Constructeur de la vue
	 * @param m
	 * le model observé
	 */
	public AttackView(Model m){
		//creation de la fenetre
		super("Attack");
		model = m;
		
		this.setSize(800,500);
		panel = new JPanel();

		panel.setLayout(new GridLayout(model.getGridSize(),model.getGridSize()));
		
		//creation des boutons
		JButton button;
		for(int i = 0;i < model.getGridSize();i++){
			for(int j = 0 ;j < model.getGridSize();j++){
				button = new JButton();
				button.setIcon(IconFactory.getInstance().getWater());
				button.addActionListener(new BoutonListener(j,i));
				panel.add(button);
				
			}
		}
		
		
		
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	/**
	 * Mise à jour de la vue
	 */
	public void update(Observable arg0, Object arg1) {
		//mise à jour des boutons
		JButton button;
		for(int i = 0; i < model.getGridSize();i++){
			for(int j = 0; j < model.getGridSize();j++){
				button = (JButton) panel.getComponent(i*model.getGridSize()+j);
				switch(model.getIASquare(j,i)){
					case MISS :
						button.setIcon(IconFactory.getInstance().getWaterExplosion());
						button.setEnabled(false);
						button.setDisabledIcon(IconFactory.getInstance().getWaterExplosion());
						break;
					case HIT :
						button.setIcon(IconFactory.getInstance().getExplosion());
						button.setEnabled(false);
						button.setDisabledIcon(IconFactory.getInstance().getExplosion());
						break;
					case EMPTY :
					break;
				}
			}
		}
		
		//affichage des bateaux morts
		int index;
		boolean horizontal;
		for(Ship ship : model.getIAFleet()){
			if(!ship.isAlive()){
				horizontal = ship.isHorizontal();
				index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX();
				button = (JButton) panel.getComponent(index);
				
				button.setIcon(IconFactory.getInstance().getExplosiveFrontBoat(horizontal));
				button.setDisabledIcon(IconFactory.getInstance().getExplosiveFrontBoat(horizontal));
				
				
			
				button.setHorizontalAlignment(SwingConstants.CENTER);
				for(int i=1; i < ship.getSize()-1;i++){
					
					if(ship.isHorizontal()){
						index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX()+i;
					}else{
						index = (ship.getTopLeftY()+i)*model.getGridSize()+ship.getTopLeftX();
					}
					button = (JButton) panel.getComponent(index);
					button.setIcon(IconFactory.getInstance().getExplosiveBoat(horizontal));
					button.setDisabledIcon(IconFactory.getInstance().getExplosiveBoat(horizontal));
					button.setHorizontalAlignment(SwingConstants.CENTER);
				}
				if(ship.isHorizontal()){
					index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX()+ship.getSize()-1;
				}else{
					index = (ship.getTopLeftY()+ship.getSize()-1)*model.getGridSize()+ship.getTopLeftX();
				}
				button  = (JButton) panel.getComponent(index);
				button.setIcon(IconFactory.getInstance().getExplosiveRearBoat(horizontal));
				button.setDisabledIcon(IconFactory.getInstance().getExplosiveRearBoat(horizontal));
				button.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
	}

	
	/**
	 * 
	 * controleur du bouton d'attaque
	 *
	 */
	  class BoutonListener implements ActionListener{
		  private int x;
		  private int y;
		  public BoutonListener(int x,int y){
			  this.x = x;
			  this.y = y;
		  }
		  
		    //Redéfinition de la méthode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		    	model.shoot(x, y);
		    	JOptionPane jp;
		    	if(model.humanLost()){
		    		jp = new JOptionPane();
		    		jp.showMessageDialog(null, "YOU LOSE", "LOOOSER", JOptionPane.INFORMATION_MESSAGE);
		    	}else if(model.iaLost()){
		    		jp = new JOptionPane();
		    		jp.showMessageDialog(null, "YOU WIN", "WINNER", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    }
	  }
}