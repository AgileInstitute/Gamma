package Tests;

import StarTrek.Enterprise;
import StarTrek.AbstractSubSystem;

public class MockEnterprise extends Enterprise {
	private String subSystemsName = "phasers";
	private boolean isDocked;
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
	public boolean isDocked() {
		return isDocked;
	}
	public void setDocked(boolean isDocked) {
		this.isDocked = isDocked;
	}
}
