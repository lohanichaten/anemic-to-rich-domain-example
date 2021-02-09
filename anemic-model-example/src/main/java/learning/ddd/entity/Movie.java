package learning.ddd.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie extends BaseEntity{

	private String name;
	
	@Enumerated(EnumType.STRING)
	private LicensingModel	licensingModel;
	
	public Movie() {
		super();
		
	}

	public Movie(String name, LicensingModel licensingModel) {
		super();
		this.name = name;
		this.licensingModel = licensingModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LicensingModel getLicensingModel() {
		return licensingModel;
	}

	public void setLicensingModel(LicensingModel licensingModel) {
		this.licensingModel = licensingModel;
	}


	

}
