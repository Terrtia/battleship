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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import battleship.model.Model;
import battleship.model.ships.Ship;


public class AttackView extends JPanel implements Observer {
	private Model model;
	private JPanel panel;
	
	
	public AttackView(Model m){
		model = m;
		
		//Creation de la fenetre
		JFrame frame = new JFrame("Attack");
		
		frame.setSize(800,500);
		panel = new JPanel();

		panel.setLayout(new GridLayout(model.getGridSize(),model.getGridSize()));
		
		JButton button;
		for(int i = 0;i < model.getGridSize();i++){
			for(int j = 0 ;j < model.getGridSize();j++){
				button = new JButton();
				button.setIcon(IconFactory.getInstance().getWater());
				button.addActionListener(new BoutonListener(j,i));
				panel.add(button);
				
			}
		}
		
		
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
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
		    }
	  }
}