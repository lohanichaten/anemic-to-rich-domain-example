package learning.ddd.entity.movie;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import learning.ddd.entity.common.ExpirationDate;
import learning.ddd.entity.common.LicensingModel;
import learning.ddd.utils.BaseEntity;



@Entity
@Table(name="movie")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="licensing_model")
public abstract class Movies extends BaseEntity{

	protected String name;
	
	@Transient
	//@Enumerated(EnumType.STRING)
	protected LicensingModel licensingModel;

	public abstract Dollars getBasePrice();
	public abstract ExpirationDate getExpirationDate();
	
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
	

	  public Dollars calculatePrice(CustomerStatus status)
      {
          BigDecimal modifier = BigDecimal.ONE.subtract(status.getDiscount());
          return Dollars.of(getBasePrice().getValue().multiply(modifier));
      }

	
}
