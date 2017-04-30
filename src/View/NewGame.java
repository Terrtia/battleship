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
import model.game.GameMode.epoch;
import model.game.GameMode.gamemode;

public class NewGame extends JDialog{
	private Model model;
	private gamemode selectedGamemode;
	private epoch selectedEpoch;

	private JComboBox epoque,modeJeu;
	private JPanel panel;
	
	/** TODO : Faire que les listes soient en fonction des énumérations "gamemode" et "epoch" 
	 * 
	 */
	
	public NewGame(Model m){
		super();
		model = m;
		selectedGamemode = gamemode.CLASSIC;
		selectedEpoch = epoch.MODERN;

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

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new okBoutonListener());
		content.add(okButton);
		this.add(content);
		this.setVisible(true);
	}

	class okBoutonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.newGame(selectedGamemode, selectedEpoch);
			new PlaceShips(model.getGameMode());
			dispose();
		}
	}
}
