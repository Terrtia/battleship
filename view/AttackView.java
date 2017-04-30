package battleship.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import battleship.model.Model;
import battleship.model.ships.Ship;


public class AttackView implements Observer {
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
				button.addActionListener(new BoutonListener(j,i));
				panel.add(button);
			}
		}
		
		
		
		frame.add(panel);
		frame.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		JButton button;
		for(int i = 0; i < model.getGridSize();i++){
			for(int j = 0; j < model.getGridSize();j++){
				button = (JButton) panel.getComponent(i*model.getGridSize()+j);
				switch(model.getIASquare(j,i)){
					case MISS :
						button.setEnabled(false);
						button.setText("MISS");
						break;
					case HIT :
						button.setEnabled(false);
						button.setText("HIT");
						break;
					case EMPTY :
					break;
				}
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
		    	model.shoot(x,y);
		    }

		  }
}
