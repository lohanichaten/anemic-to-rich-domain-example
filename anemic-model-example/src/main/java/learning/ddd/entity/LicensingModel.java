package learning.ddd.entity;

public enum LicensingModel {
	TWODAYS(1),
	LIFELONG(2);
	
	private int days;
	private LicensingModel(int days) {
		this.days=days;
	}
	
}
