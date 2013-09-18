package Tests;

import StarTrek.Klingon;


public class MockSuperKlingon extends Klingon {
    @Override
	public int attack() {
		return 20000;
	}

	private int overrideDistance;
    private boolean deleteCalled = false;

    public MockSuperKlingon() {
        //overrideDistance = distance;
    }
    public MockSuperKlingon(int distance) {
        overrideDistance = distance;
    }

    public MockSuperKlingon(int distance, int energy) {
        overrideDistance = distance;
        setEnergy(energy);
    }

    public int distance() {
        return overrideDistance;
    }

    public void delete() {
        deleteCalled = true;
    }

    boolean deleteWasCalled() {
        return deleteCalled;
    }
}
