package battleship.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import battleship.model.Model;
import battleship.model.ships.Ship;


public class ShipView implements Observer {

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
		
		frame.setSize(800,500);
		panelShip = new JPanel();

		panelShip.setLayout(new GridLayout(model.getGridSize(),model.getGridSize()));
		for(int i = 0;i < model.getGridSize();i++){
			for(int j = 0 ;j < model.getGridSize();j++){
				panelShip.add(new JTextField(" "));
			}
		}
		 
		int index;
		JTextField field;
		for(Ship ship : model.getHumanFleet()){
			field = new JTextField(ship.getSize()+"");
			for(int i=0; i < ship.getSize();i++){
				if(ship.isHorizontal()){
					index = ship.getTopLeftY()*model.getGridSize()+ship.getTopLeftX()+i;
				}else{
					index = (ship.getTopLeftY()+i)*model.getGridSize()+ship.getTopLeftX();
				}
				((JTextComponent) panelShip.getComponent(index)).setText(ship.getSize()+"");
			}
		}
		
		
		
		panel.add(panelShip,BorderLayout.CENTER);
		panel.add(menu,BorderLayout.NORTH);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		JTextField field;
		for(int i = 0; i < model.getGridSize();i++){
			for(int j = 0; j < model.getGridSize();j++){
				field =  (JTextField) panelShip.getComponent(i*model.getGridSize()+j);
				switch(model.getHumanSquare(j,i)){
					case MISS :
						field.setText("PLOUF");
						break;
					case HIT :
						field.setText("X");
						break;
					case EMPTY :
					break;
				}
			}
		}
	}


}
