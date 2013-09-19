package StarTrek;

public class Location {

	private int quadrantX;
	private int quadrantY;
	private int sectorX;
	private int sectorY;
	
	public Location(int qx, int qy, int sx, int sy) {
		quadrantX = qx;
		quadrantY = qy;
		sectorX = sx;
		sectorY = sy;
	}
	public int getQuadrantX() {
		return quadrantX;
	}
	public void setQuadrantX(int quadrantX) {
		this.quadrantX = quadrantX;
	}
	public int getQuadrantY() {
		return quadrantY;
	}
	public void setQuadrantY(int quadrantY) {
		this.quadrantY = quadrantY;
	}
	public int getSectorX() {
		return sectorX;
	}
	public void setSectorX(int sectorX) {
		this.sectorX = sectorX;
	}
	public int getSectorY() {
		return sectorY;
	}
	public void setSectorY(int sectorY) {
		this.sectorY = sectorY;
	}
	public int getAbsoluteX() {
		return (this.getQuadrantX() * 10 + this.getSectorX());
	}
	public int getAbsoluteY() {
		return (this.getQuadrantX() * 10 + this.getSectorX());
	}
}
