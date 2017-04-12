package model.ships.modern;

import model.ships.Ship;
import model.ships.ShipFactory;

public class ModernShipFactory extends ShipFactory {

	@Override
	public Ship createCarrier() {return new SuperCarrier();}

	@Override
	public Ship createCruiser() {return new MissileCruiser();}

	@Override
	public Ship createDestroyer() {return new GuidedMissileDestroyer();}

	@Override
	public Ship createSubmarine() {return new NuclearSubmarine();}

	@Override
	public Ship createTorpedoBoat() {return new FastAttackCraft();}
}
