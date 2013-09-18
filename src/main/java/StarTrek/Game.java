package StarTrek;

import java.util.HashMap;
import java.util.Map;

import Untouchables.WebGadget;

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
	
    public void executeCommand(Galaxy galaxy) {
    	
    	String command = galaxy.parameter("command");
    	
    	String result = null;

    	if (command.equals("phaser")) {
    		result = firePhaser(galaxy);

		} else if (command.equals("photon")) {
			result = firePhotons(galaxy);
			
		} else if (command.equals("defend")) {
			defendKlingonAttack(galaxy);
		} else if (command.equals("move")) {
			//TODO
		} else if (command.equals("rest")) {
			//TODO
		} else if (command.equals("shieldTransfer")) {
			transferEnergyToShields(galaxy);
			
		} else if (command.equals("scan")) {
			result = scanQuadrant();
		}  else if (command.equals("status")) {
			//TODO
		}
    	if (result != null) {
    		galaxy.writeLine(result);
    	}
	}

    public String firePhaser(Galaxy galaxy) {
		String result;
		Klingon enemy = (Klingon) galaxy.variable("target");
		int amount = Integer.parseInt(galaxy.parameter("amount"));
		result = executeFireWeapon(enemy, amount, enterprise.getPhaser());
		return result;
	}

	public String firePhotons(Galaxy galaxy) {
		String result;
		Klingon enemy = (Klingon) galaxy.variable("target");
		result = executeFireWeapon(enemy, 1, enterprise.getPhotons());
		return result;
	}

	public void defendKlingonAttack(Galaxy galaxy) {
		galaxy.writeLine("Enterprise Shields = " + enterprise.getSheildEnergy());
		int damage = klingon.attack();
		galaxy.writeLine("Klingon attacks Enterprise doing " + damage + " damage.");
		enterprise.takeDamage(damage);
		galaxy.writeLine("Enterprise Shields = " + enterprise.getSheildEnergy());
	}

	public void transferEnergyToShields(Galaxy galaxy) {
		galaxy.writeLine("Enterprise Energy = "+enterprise.getReserveEnergy()+ " Shields = " + enterprise.getSheildEnergy());
		int amount = Integer.parseInt(galaxy.parameter("amount"));
		enterprise.transferEnergyToShields(amount);
		galaxy.writeLine("Shield Transfer " + amount);
		galaxy.writeLine("Enterprise Energy = "+enterprise.getReserveEnergy()+ " Shields = " + enterprise.getSheildEnergy());
	}

	private String scanQuadrant() {
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
					System.out.print(" X ");
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

    public void fireWeapon(Galaxy galaxy) {
        executeCommand(galaxy);
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
