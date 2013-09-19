package StarTrek;

public class SubSystem {

	private String name;
	private int energy;
	private int unitsDamage;

	public SubSystem(String n, int i) {
		name = n;
		energy = i;
	}
	public void reduceEnergy(int reduce) {
		energy -= reduce;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
