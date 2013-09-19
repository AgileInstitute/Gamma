package StarTrek;

import java.text.DecimalFormat;

public abstract class AbstractSubSystem {

	private String name;
	private double starDatesToRepair = 0;

	public AbstractSubSystem(String n) {
		name = n;
	}
	
	public abstract void takeDamage(int damage);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void repair() {
		starDatesToRepair -= 1;
		if (starDatesToRepair < 0) {
			starDatesToRepair = 0;
		}
	}

	public double getStarDatesToRepair() {
//		DecimalFormat df = new DecimalFormat("#.#");
//		String starDatesString = df.format(starDatesToRepair);
		return starDatesToRepair;
	}

	public void setStarDatesToRepair(double starDatesToRepair) {
		this.starDatesToRepair = starDatesToRepair;
	}

}
