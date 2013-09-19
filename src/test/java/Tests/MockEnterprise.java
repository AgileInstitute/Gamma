package Tests;

import StarTrek.Enterprise;
import StarTrek.AbstractSubSystem;

public class MockEnterprise extends Enterprise {

	@Override
	protected AbstractSubSystem determineSubSystemThatIsDamaged() {
		return super.getSubSystems().get("phasers");
	}

}
