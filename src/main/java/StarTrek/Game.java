package StarTrek;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private int GRID_SIZE = 100;
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
		
		grid = new HashMap<Integer, Map<Integer, Object>>(GRID_SIZE);
		for (int i = 0; i<GRID_SIZE; i++) {
			grid.put(i, new HashMap<Integer, Object>(GRID_SIZE));
		}
		
		Location enterpriseLocation = new Location(0,0,0,0);
		enterprise.setLocation(enterpriseLocation);
		
		int xLocation = enterpriseLocation.getAbsoluteX();
		int yLocation = enterpriseLocation.getAbsoluteY();

		grid.get(xLocation).put(yLocation, enterprise);
		grid.get(0).put(1, klingon);
	}

	public void moveEnterprise(int quadX, int quadY, int sectorX, int sectorY) {
		Location enterpriseLocation = enterprise.getLocation();
		grid.get(enterpriseLocation.getAbsoluteX()).remove(enterpriseLocation.getAbsoluteY());
		
		enterpriseLocation.setQuadrantX(quadX);
		enterpriseLocation.setQuadrantY(quadY);
		enterpriseLocation.setSectorX(sectorX);
		enterpriseLocation.setSectorY(sectorY);
		
		int xLocation = enterpriseLocation.getAbsoluteX();
		int yLocation = enterpriseLocation.getAbsoluteY();
		
		grid.get(xLocation).put(yLocation, enterprise);
	}
	public void moveEnterprise(int sectorX, int sectorY) {
		Location enterpriseLocation = enterprise.getLocation();
		grid.get(enterpriseLocation.getAbsoluteX()).remove(enterpriseLocation.getAbsoluteY());

		enterpriseLocation.setSectorX(sectorX);
		enterpriseLocation.setSectorY(sectorY);
		
		int xLocation = enterpriseLocation.getAbsoluteX();
		int yLocation = enterpriseLocation.getAbsoluteY();
		
		grid.get(xLocation).put(yLocation, enterprise);
	}
	
    public void rest(int starDates) {
    	enterprise.repair(starDates);
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

	public void scanSector(int qx, int qy) {
		System.out.println("Scan Quadrant (" + qx + ", " + qy + ")");
		
		int startx = qx*10;
		int starty = qy*10;
		
		for (int i = startx; i<(startx+10); i++) {
			for (int j = starty; j<(starty+10); j++) {
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
		}
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
