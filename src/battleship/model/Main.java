package battleship.model;

import battleship.view.AttackView;
import battleship.view.ShipView;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();
		ShipView shipView = new ShipView(model);
		AttackView attackView = new AttackView(model);
		model.addObserver(shipView);
		model.addObserver(attackView);
	}

}
