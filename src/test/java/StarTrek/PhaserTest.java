package StarTrek;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Tests.MockRandom;

public class PhaserTest {
	
	private int startingEnergy = 10000;
	private Phaser weapon;
	
    @Before
    public void setUp() {
    	weapon = new Phaser(startingEnergy);
    	Weapon.generator = new MockRandom();
    }
	
	@Test
	public void testUpdateAfterFire() {
		weapon.updateAfterFire(1000);
		assertEquals((startingEnergy - 1000), weapon.getEnergyLevel());
	}

	@Test
	public void testCalculateDamage() {
		int damage = weapon.calculateDamage(20000, 200);
		assertEquals(18900, damage);
	}

	@Test
	public void testIsHit() {
		assertTrue(weapon.isHit(1000));
	}

	@Test
	public void testCanFire() {
		assertTrue(weapon.canFire(1000));
	}

}
