package battleship.model.ships.modern;

import battleship.model.ships.Ship;
import battleship.model.ships.ShipFactory;

/**
 * Factory de bateu modern
 *
 */
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
