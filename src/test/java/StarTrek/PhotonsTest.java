package StarTrek;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Tests.MockRandom;

public class PhotonsTest {

	private Photons weapon;
	private int numberOfPhotons = 4;
	
    @Before
    public void setUp() {
    	weapon = new Photons(numberOfPhotons);
    	Weapon.generator = new MockRandom();
    }
	
	@Test
	public void testUpdateAfterFire() {
		weapon.updateAfterFire(1);
		assertEquals((numberOfPhotons - 1), weapon.getNumberPhotons());
	}

	@Test
	public void testCalculateDamage() {
		int damage = weapon.calculateDamage(1, 200);
		assertEquals(825, damage);
	}

	@Test
	public void testIsHit() {
		assertTrue(weapon.isHit(500));
	}

	@Test
	public void testCanFire() {
		assertTrue(weapon.canFire(1000));
	}


}
