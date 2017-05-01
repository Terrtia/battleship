package battleship.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import battleship.model.Model;
import battleship.model.ships.Ship;


public class ShipView extends JFrame implements Observer {

	private Model model;
	private JPanel panelShip;
	private JPanel panel;
	private Menu menu;
	
	public ShipView(Model m){
		model = m;
		menu = new Menu(model);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		//Creation de la fenetre
		JFrame frame = new JFrame("BattleShip");
		JLabel label;
		
		frame.setSize(800,500);
		panelShip = new JPanel();
		panelShip.setLayout(new GridLayout(model.getGridSize(),model.getGridSize()));
		for(int i = 0;i < model.getGridSize();i++){
			for(int j = 0 ;j < model.getGridSize();j++){
				 label = new JLabel();
				 label.setIcon(IconFactory.getInstance().getWater());
				panelShip.add(label);
			}
		}
		 
		int index;
		for(Ship ship : model.getHumanFleet()){
			label= new JLabel();
			if(ship.isHorizontal()){
				index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX();
			}else{
				index = (ship.getTopLeftY())*model.getGridSize()+ship.getTopLeftX();
			}
			label = (JLabel) panelShip.getComponent(index);
			label.setIcon(IconFactory.getInstance().getFrontBoat(ship.isHorizontal()));
			for(int i=1; i < ship.getSize()-1;i++){
				if(ship.isHorizontal()){
					index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX()+i;
				}else{
					index = (ship.getTopLeftY()+i)*model.getGridSize()+ship.getTopLeftX();
				}
				label = (JLabel) panelShip.getComponent(index);
				label.setIcon(IconFactory.getInstance().getBoat(ship.isHorizontal()));
			}
			
			if(ship.isHorizontal()){
				index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX()+ship.getSize()-1;
			}else{
				index = (ship.getTopLeftY()+ship.getSize()-1)*model.getGridSize()+ship.getTopLeftX();
			}
			label = (JLabel) panelShip.getComponent(index);
			label.setIcon(IconFactory.getInstance().getRearBoat(ship.isHorizontal()));
			
		}
		
		
		
		panel.add(panelShip,BorderLayout.CENTER);
		panel.add(menu,BorderLayout.NORTH);
		frame.add(panel);
		frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		JLabel label;
		boolean horizontal;
		for(int i = 0; i < model.getGridSize();i++){
			for(int j = 0; j < model.getGridSize();j++){
				label =  (JLabel) panelShip.getComponent(i*model.getGridSize()+j);
				switch(model.getHumanSquare(j,i)){
					case MISS :
						label.setIcon(IconFactory.getInstance().getWater());
						break;
					case HIT :
						horizontal = model.isHorizontal(j, i);
							if(model.isFront(j, i)){
								label.setIcon(IconFactory.getInstance().getExplosiveFrontBoat(horizontal));
							}else if(model.isRear(j, i)){
								label.setIcon(IconFactory.getInstance().getExplosiveRearBoat(horizontal));
							}else{
								label.setIcon(IconFactory.getInstance().getExplosiveBoat(horizontal));
							}
						
						break;
					case EMPTY :
					break;
				}
			}
		}
		int index;
		for(Ship ship : model.getHumanFleet()){
			if(!ship.isAlive()){
				index = (ship.getTopLeftY()*model.getGridSize())+ship.getTopLeftX();
				label = (JLabel) panelShip.getComponent(index);
				horizontal = ship.isHorizontal();
				label.setIcon(IconFactory.getInstance().getExplosiveFrontBoat(horizontal));
				for(int i=1; i < ship.getSize()-1;i++){
					if(ship.isHorizontal()){
						index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX()+i;
					}else{
						index = (ship.getTopLeftY()+i)*model.getGridSize()+ship.getTopLeftX();
					}
					label = (JLabel) panelShip.getComponent(index);
					label.setIcon(IconFactory.getInstance().getExplosiveBoat(horizontal));
				}
				if(ship.isHorizontal()){
					index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX()+ship.getSize()-1;
					
				}else{
					index = (ship.getTopLeftY()+ship.getSize()-1)*model.getGridSize()+ship.getTopLeftX();
					
				}
				label  = (JLabel) panelShip.getComponent(index);
				label.setIcon(IconFactory.getInstance().getExplosiveRearBoat(horizontal));
			}
		}
		
		
	}


}
