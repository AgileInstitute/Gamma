package StarTrek;

public class ShieldsSubSystem extends AbstractSubSystem {

	public ShieldsSubSystem(String n) {
		super(n);
	}

	private int STAR_DATES_FOR_DAMAGE = 500;

	@Override
	public void takeDamage(int damage) {
		double starDatesToRepair = damage / STAR_DATES_FOR_DAMAGE;
		starDatesToRepair = Math.round(starDatesToRepair * 10) / 10;
		super.setStarDatesToRepair(starDatesToRepair);
	}

}
