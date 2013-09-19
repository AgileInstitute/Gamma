package Tests;

import StarTrek.Enterprise;
import StarTrek.SubSystem;

public class MockEnterprise extends Enterprise {

	@Override
	protected SubSystem determineSubSystemThatIsDamaged() {
		return super.getSubSystems().get("phasers");
	}

}
