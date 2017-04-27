package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import View.AttackView.BoutonListener;

import model.Model;

public class NewGame extends JDialog implements Observer {
	private Model model;
	private ButtonGroup bg,boatg;
	private JComboBox horizontal,epoque,modeJeu;
	private JRadioButton boat1,boat2,boat3,boat4,boat5;
	private int[][] boats ;
	private JPanel panel;
	
	public NewGame(Model m){
		super();
		model = m;
		boats = new int[5][4];
		for(int i = 0; i < 5;i++){
			boats[i][0] = -1;
		}
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
	    this.setSize(1000, 700);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    

	    //this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    
	    //le mode de jeu
	    JPanel panMode = new JPanel();
	    panMode.setBackground(Color.white);
	    panMode.setBorder(BorderFactory.createTitledBorder("Mode de jeu"));
	    panMode.setSize(new Dimension(1000,50));
	    modeJeu = new JComboBox();
	    modeJeu.addItem("Classic");
	    modeJeu.addItem("Alternatif");
	    panMode.add(modeJeu);
	    content.add(panMode);
	    
	    //l'epoque
	    JPanel panEpoque = new JPanel();
	    panEpoque.setBackground(Color.white);
	    panEpoque.setSize(new Dimension(1000, 50));
	    panEpoque.setBorder(BorderFactory.createTitledBorder("Epoque"));
	    epoque = new JComboBox();
	    epoque.addItem("Modern");
	    epoque.addItem("Past");
	    JLabel epoqueLabel = new JLabel("Epoque : ");
	    panEpoque.add(epoqueLabel);
	    panEpoque.add(epoque);
	    content.add(panEpoque);
	    
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
		panel.setLayout(new GridLayout(model.getGridSize(),model.getGridSize()));
		
		JButton button;
		for(int i = 0;i < model.getGridSize();i++){
			for(int j = 0 ;j < model.getGridSize();j++){
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
		    	boats[selected][0] =x;
		    	boats[selected][1] =y;
		    	boats[selected][2] =horizontal.getSelectedIndex();
		    	
		    	//update de la grille
		    	JButton button;
		    	
		    	//nettoyage
		    	for(int i = 0; i < model.getGridSize();i++){
					for(int j = 0; j < model.getGridSize();j++){
						button = (JButton) panel.getComponent(i*model.getGridSize()+j);
						button.setText("");
					}
		    	}
		    	int boatSize=0,index = 0;
		    	String boatName="";
		    	for(int i = 0; i < boats.length;i++){
		    			switch(i){
		    			case 0:
		    				boats[i][3] = 5;
		    				boatName = "Carrier";
		    				break;
		    			case 1:
		    				boats[i][3] = 4;
		    				boatName = "Cruiser";
		    				break;
		    			case 2:
		    				boats[i][3] = 3;
		    				boatName = "Destroyer";
		    				break;
		    			case 3:
		    				boats[i][3] = 3;
		    				boatName = "Submarine";
		    				break;
		    			case 4:
		    				boats[i][3] = 2;
		    				boatName = "TorpedoBoat";
		    				break;
		    			}
		    			
		    			if(boats[i][2] == 0){
		    				if((boats[i][0]+boats[i][3]>model.getGridSize())){
		    					boats[i][0] = -1;
		    				}
		    			}else{
		    				if((boats[i][1]+boats[i][3]>model.getGridSize())){
		    					boats[i][0] = -1;
		    				}
		    			}
		    			
		    			if(boats[i][0]!=-1){
		    			for(int j=0; j < boats[i][3];j++){
		    				if(boats[i][2]==0){
		    					index = boats[i][1]*model.getGridSize()+boats[i][0]+j;
		    				}else{
		    					index = (boats[i][1]+j)*model.getGridSize()+boats[i][0];
		    				}
		    				button = (JButton) panel.getComponent(index);
		    				button.setText(boatName);
		    			}
		    		}
		    	}
		    	
		    }

		  }
	  
	  class okBoutonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(model.newGameValide(boats)){
				model.newGame(boats,modeJeu.getSelectedIndex(),epoque.getSelectedIndex());
			}else{
				System.out.println("nope");
			}
			
		}
		 
		  }


}
