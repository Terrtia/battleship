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
		/*System.out.println(model.toStringGameStatut());
		System.out.println(model.getIAGrid().toStringFriendlyGrid());
		System.out.println(model.getIAGrid().toStringFleet());
		System.out.println("-");
		System.out.println(model.getHumanGrid().toStringFriendlyGrid());
		System.out.println(model.getHumanGrid().toStringFleet());
	}

}
