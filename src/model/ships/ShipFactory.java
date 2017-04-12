package model.ships;

public abstract class ShipFactory {
	public abstract Ship createCarrier();
	public abstract Ship createCruiser();
	public abstract Ship createDestroyer();
	public abstract Ship createSubmarine();
	public abstract Ship createTorpedoBoat();	
}
