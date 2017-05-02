package battleship.model.ships.past;

import battleship.model.ships.Ship;
import battleship.model.ships.ShipFactory;

public class PastShipFactory extends ShipFactory{

	@Override
	public Ship createCarrier() {
		return new ManOWar();
	}

	@Override
	public Ship createCruiser() {
		return new Vessel();
	}

	@Override
	public Ship createDestroyer() {
		return new Boat();
	}

	@Override
	public Ship createSubmarine() {
		return new Hunter();
	}

	@Override
	public Ship createTorpedoBoat() {
		return new Raft();
	}

}
