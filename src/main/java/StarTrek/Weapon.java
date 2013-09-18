package StarTrek;

import java.util.Random;

public abstract class Weapon {
	
	public abstract void updateAfterFire(int amount);
	
	public abstract int calculateDamage(int amount, int distance);

	public abstract boolean isHit(int distance);
	
	public abstract boolean canFire(int amount);

	public abstract String getMissMessage(int distance);
	
	public abstract String getHitMessage(int distance, int damage);
	
	public abstract String getCannotFireMessage();
	
    // note we made generator public in order to mock it
    // it's ugly, but it's telling us something about our *design!* ;-)
	public static Random generator = new Random();
	
	protected static int rnd(int maximum) {
		return generator.nextInt(maximum);
	}
}
