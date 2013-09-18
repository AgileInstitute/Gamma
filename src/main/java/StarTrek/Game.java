package StarTrek;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private int GRID_SIZE = 10;
	private Enterprise enterprise;
	private Klingon klingon;
	private Map<Integer, Map<Integer, Object>> grid;
	
	public Game() {
		enterprise = new Enterprise();
		klingon = new Klingon();
		
		grid = new HashMap<Integer, Map<Integer, Object>>(GRID_SIZE);
		for (int i = 0; i<GRID_SIZE; i++) {
			grid.put(i, new HashMap<Integer, Object>(GRID_SIZE));
		}
		
		grid.get(0).put(0, enterprise);
		grid.get(0).put(1, klingon);
	}

    public String firePhaser(int amount) {
		return executeFireWeapon(klingon, amount, enterprise.getPhaser());
	}

	public String firePhotons() {
		return executeFireWeapon(klingon, 1, enterprise.getPhotons());
	}

	public void defendKlingonAttack() {
		System.out.println("Enterprise Shields = " + enterprise.getSheildEnergy());
		int damage = klingon.attack();
		System.out.println("Klingon attacks Enterprise doing " + damage + " damage.");
		enterprise.takeDamage(damage);
		System.out.println("Enterprise Shields = " + enterprise.getSheildEnergy());
	}

	public void transferEnergyToShields(int amount) {
		System.out.println("Enterprise Energy = "+enterprise.getReserveEnergy()+ " Shields = " + enterprise.getSheildEnergy());
		enterprise.transferEnergyToShields(amount);
		System.out.println("Shield Transfer " + amount);
		System.out.println("Enterprise Energy = "+enterprise.getReserveEnergy()+ " Shields = " + enterprise.getSheildEnergy());
	}

	public String scanQuadrant() {
		String result;
		result = "Scan Completed";
		for (int i = 0; i<GRID_SIZE; i++) {
			for (int j = 0; j<GRID_SIZE; j++) {
				Object object = grid.get(i).get(j);
				if (object != null && object instanceof Klingon) {
					System.out.print(" K ");
				} else if (object != null && object instanceof Enterprise) {
					System.out.print(" E ");
				} else {
					System.out.print(" . ");
				}
			}
			System.out.println("");
			result = "Scan Completed";
		}
		return result;
	}

	/* COMMON Processing ---------------------------------------------- */
    private String executeFireWeapon(Klingon enemy, int amount, Weapon weapon) {
		String result;
		if (weapon.canFire(amount)) {
			result = determineHit(amount, enemy, weapon);
			weapon.updateAfterFire(amount);

		} else {
			result = weapon.getCannotFireMessage();
		}
		return result;
	}
    
	private String determineHit(int amount, Klingon enemy, Weapon weapon) {
		int distance = enemy.distance();
		String result;
		if (weapon.isHit(distance)) {
			int damage = weapon.calculateDamage(amount, distance);
			result = weapon.getHitMessage(distance, damage);
			String damageResult = enemy.takeDamage(damage);
			result = result.concat(damageResult);
		} else {
			result = weapon.getMissMessage(distance);
		}
		return result;
	}
	
	/* ACCESSOR METHODS --------------------------------------------------*/
    public int EnergyRemaining() {
        return enterprise.getPhaser().getEnergyLevel();
    }

    public void setTorpedoes(int value) {
    	enterprise.getPhotons().setNumberPhotons(value);
    }
    
    public int getTorpedoes() {
    	return enterprise.getPhotons().getNumberPhotons();
    }

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Map<Integer, Map<Integer, Object>> getGrid() {
		return grid;
	}

	public void setGrid(Map<Integer, Map<Integer, Object>> grid) {
		this.grid = grid;
	}

	public Klingon getKlingon() {
		return klingon;
	}

	public void setKlingon(Klingon klingon) {
		this.klingon = klingon;
	}

}
