package test.player;

import static org.junit.Assert.*;

import model.players.Grid;
import model.players.Grid.Square;
import model.ships.Ship;
import model.ships.modern.GuidedMissileDestroyer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GridTest {
	
	private Grid grid;
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@org.junit.Before
    public void setUp() throws Exception {
        this.grid = new Grid();
	}

	@Test
	public void testGrid() throws Exception {
		assertFalse(grid.getFleet() == null);
		for(int i=0; i < grid.getGridSize();i++){
			for(int j = 0; j < grid.getGridSize();j++){
				assertTrue(grid.getFriendlyGrid()[i][j] instanceof Square);
			}
		}
	}

	@Test
	public void testPlaceShip() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer(); 
		grid.addShip(gmd);
		assertTrue(grid.placeShip(gmd, 5, 5, true));
		assertTrue(gmd.isPlaced());
	}
	
	@Test
	public void testAlreadyPlaceShip() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer(); 
		grid.addShip(gmd);
		assertTrue(grid.placeShip(gmd, 5, 5, true));
		assertTrue(grid.placeShip(gmd, 6, 2, true));
		assertTrue(gmd.isPlaced());
	}
	
	@Test
	public void testCollisionPlaceShip() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer(); 
		Ship gmd2 = new GuidedMissileDestroyer();
		grid.addShip(gmd);
		grid.addShip(gmd2);
		assertTrue(grid.placeShip(gmd, 5, 5, true));
		assertFalse(grid.placeShip(gmd2, 5, 5, true));
		assertFalse(gmd2.isPlaced());
	}
	
	@Test
	public void testPlaceShipOutOfGrid() throws Exception {
		thrown.expect(Exception.class);
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer(); 
		grid.addShip(gmd);
		assertTrue(grid.placeShip(gmd, -5, 50, true));
		assertTrue(gmd.isPlaced());
	}

	@Test
	public void testIsHitTrue() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer(); 
		grid.addShip(gmd);
		grid.placeShip(gmd, 5, 5, true);
		assertTrue(grid.isHit(5, 5));
	}
	
	@Test
	public void testIsHitFalse() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer();
		grid.addShip(gmd);
		grid.placeShip(gmd, 5, 5, true);
		assertFalse(grid.isHit(0, 0));
	}
	
	@Test
	public void testIsHitOutOfGrid() throws Exception {
		thrown.expect(Exception.class);
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer(); 
		grid.addShip(gmd);
		grid.placeShip(gmd, 5, 5, true);
		grid.isHit(-5, 50);
	}

	@Test
	public void testAllShipsPlacedTrue() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer();
		Ship gmd2 = new GuidedMissileDestroyer(); 
		grid.addShip(gmd);
		grid.addShip(gmd2);
		grid.placeShip(gmd, 2, 3, true);
		grid.placeShip(gmd2, 3, 4, true);
		assertTrue(grid.allShipsPlaced());
	}
	
	@Test
	public void testAllShipsPlacedFalse() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer();
		Ship gmd2 = new GuidedMissileDestroyer(); 
		grid.addShip(gmd);
		grid.addShip(gmd2);
		grid.placeShip(gmd, 2, 3, true);
		assertFalse(grid.allShipsPlaced());
	}

	@Test
	public void testBoatStillFloatsTrue() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer();
		grid.addShip(gmd);
		grid.placeShip(gmd, 2, 3, true);
		assertTrue(grid.boatStillFloats());
	}
	
	@Test
	public void testBoatStillFloatsFalse() throws Exception {
		grid.clear();
		Ship gmd = new GuidedMissileDestroyer();
		gmd.setHitPoints(0);
		grid.addShip(gmd);
		assertFalse(grid.boatStillFloats());
	}

}
