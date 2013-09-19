package StarTrek;

public class StandardSubSystem extends AbstractSubSystem {

	public StandardSubSystem(String n) {
		super(n);
	}

	private int STAR_DATES_FOR_DAMAGE = 100;

	@Override
	public void takeDamage(int damage) {
		double starDatesToRepair = damage / STAR_DATES_FOR_DAMAGE;
		starDatesToRepair = Math.round(starDatesToRepair * 10) / 10;
		super.setStarDatesToRepair(starDatesToRepair);
	}

}
