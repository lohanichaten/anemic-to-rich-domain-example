package learning.ddd.entity.movie;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import learning.ddd.entity.common.ExpirationDate;

@Entity
@DiscriminatorValue(value = "LIFELONG")
public class LifeLongMovie extends Movies{


	@Override
	public Dollars getBasePrice() {
		return Dollars.of(new BigDecimal(4));
	}

	@Override
	public ExpirationDate getExpirationDate() {
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 10000);		
		ExpirationDate expirationDate= ExpirationDate.create(cal.getTime()).getData();
		return expirationDate;
	}

}
