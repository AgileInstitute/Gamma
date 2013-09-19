package Tests;

import StarTrek.Enterprise;
import StarTrek.AbstractSubSystem;

public class MockEnterprise extends Enterprise {
	private String subSystemsName = "phasers";
	@Override
	protected AbstractSubSystem determineSubSystemThatIsDamaged() {
		return super.getSubSystems().get(subSystemsName);
	}
	public String getSubSystemsName() {
		return subSystemsName;
	}

	public void setSubSystemsName(String subSystemsName) {
		this.subSystemsName = subSystemsName;
	}
}
