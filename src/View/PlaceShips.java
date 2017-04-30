package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Model;
import model.game.GameMode;
import model.players.Grid;
import model.ships.Ship;

public class PlaceShips extends JDialog implements Observer {

	private Grid grid;
	private GameMode gamemode;

	private JPanel panel;
	private ButtonGroup boatg;
	private JComboBox horizontal;
	private JRadioButton boat1,boat2,boat3,boat4,boat5;

	public PlaceShips(GameMode gm){
		super();
		gamemode = gm;
		grid = gamemode.getHumanGrid();

		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		//les bateaux
		JPanel boatPanel = new JPanel();
		boatPanel.setBackground(Color.white);
		boatPanel.setBorder(BorderFactory.createTitledBorder("Places tes bateaux"));
		boatPanel.setSize(new Dimension(1000, 50));
		boat1 = new JRadioButton("Carrier");
		boat1.setSelected(true);
		boat2 = new JRadioButton("Cruiser");
		boat3 = new JRadioButton("Destroyer");
		boat4 = new JRadioButton("Submarine");
		boat5 = new JRadioButton("TorpedoBoat");
		boatg = new ButtonGroup();
		boatg.add(boat1);
		boatg.add(boat2);
		boatg.add(boat3);
		boatg.add(boat4);
		boatg.add(boat5);

		horizontal = new JComboBox();
		horizontal.addItem("horizontal");
		horizontal.addItem("vertical");
		boatPanel.add(boat1);
		boatPanel.add(boat2);
		boatPanel.add(boat3);
		boatPanel.add(boat4);
		boatPanel.add(boat5);
		boatPanel.add(horizontal);
		content.add(boatPanel);

		panel = new JPanel();
		panel.setSize(new Dimension(1000,550));
		panel.setLayout(new GridLayout(grid.getGridSize(),grid.getGridSize()));

		JButton button;
		for(int i = 0;i < grid.getGridSize();i++){
			for(int j = 0 ;j < grid.getGridSize();j++){
				button = new JButton();
				button.addActionListener(new BoutonListener(j,i));
				panel.add(button);
			}
		}

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new okBoutonListener());
		content.add(panel);
		content.add(okButton);
		this.add(content);
		this.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for(int i = 0; i < grid.getGridSize() * grid.getGridSize();i++)
			panel.getComponent(i).setEnabled(true);
		for(Ship s: grid.getFleet()){
			if(s.isPlaced()){
				if(s.isHorizontal())
					for(int i=0; i<s.getSize(); i++) 
						panel.getComponent(s.getTopLeftY()*grid.getGridSize() + s.getTopLeftX()+i).setEnabled(false);
				else
					for(int i=0; i<s.getSize(); i++) 
						panel.getComponent((s.getTopLeftY()+i)*grid.getGridSize() + s.getTopLeftX()).setEnabled(false);
			}
		}
	}

	public int getSelectedBoat(){
		return (boat1.isSelected()) ? 0 : 
			(boat2.isSelected()) ? 1 : 
				(boat3.isSelected()) ? 2 : 
					(boat4.isSelected()) ? 3 : 
						4;
	}

	class BoutonListener implements ActionListener{
		private int x;
		private int y;
		public BoutonListener(int x,int y){
			this.x = x;
			this.y = y;
		}

		public void actionPerformed(ActionEvent arg0) {
			int selected = getSelectedBoat();
			Ship s = grid.getFleet().get(selected);
			grid.placeShip(s, x, y, horizontal.getSelectedIndex() == 0);
			update(null, null);
		}

	}

	class okBoutonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(grid.allShipsPlaced()){
				gamemode.run();
				dispose();
			}else{
				System.out.println("nope");
			}

		}

	}



}
