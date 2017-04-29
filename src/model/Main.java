package model;

import View.AttackView;
import View.ShipView;

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
