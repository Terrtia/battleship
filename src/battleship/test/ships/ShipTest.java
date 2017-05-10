package battleship.test.ships;

import static org.junit.Assert.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import battleship.model.ships.Ship;
import battleship.model.ships.modern.GuidedMissileDestroyer;

public class ShipTest {
	
	private Ship ship;
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@org.junit.Before
    public void setUp() throws Exception {
        ship = new GuidedMissileDestroyer();
	}

	@Test
	public void testShip() throws Exception  {
		assertEquals( -1, ship.getTopLeftX());
		assertEquals( -1, ship.getTopLeftY());
		assertTrue(ship.getSize() > 0);
		assertTrue(ship.getHitPoints() > 0);
	}

	@Test
	public void testPlace() throws Exception  {
		ship.place(5, 7, true);
		assertEquals( 5, ship.getTopLeftX());
		assertEquals( 7, ship.getTopLeftY());
		assertEquals( true, ship.isHorizontal());
	}
	
	@Test
	public void testPlaceOutOfGrid() throws Exception {
		thrown.expect(Exception.class);
		ship.place(-5, 15, true);
	}

	@Test
	public void testRemove() throws Exception  {
		ship.remove();
		assertEquals( -1, ship.getTopLeftX());
		assertEquals( -1, ship.getTopLeftY());
	}

	@Test
	public void testCollideTrue() throws Exception  {
		ship.place(5, 7, true);
		assertTrue(ship.collide(5, 7, 3, true));
	}

	@Test
	public void testCollideFalse() throws Exception  {
		ship.place(5, 7, true);
		assertFalse(ship.collide(2, 1, 2, false));
	}
	
	@Test
	public void testCollideOutOfGrid() throws Exception  {
		thrown.expect(Exception.class);
		ship.place(5, 7, true);
		ship.collide(-2, -3, 2, false);
	}
	
	@Test
	public void testIsHitTrue() throws Exception  {
		ship.place(5, 7, true);
		assertTrue(ship.isHit(5, 7));
	}
	
	@Test
	public void testIsHitFalse() throws Exception  {
		ship.place(2, 2, false);
		assertFalse(ship.isHit(5, 5));
	}

}
