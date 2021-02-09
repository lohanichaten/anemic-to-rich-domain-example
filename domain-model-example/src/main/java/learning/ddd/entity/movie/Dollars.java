package learning.ddd.entity.movie;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import learning.ddd.utils.Result;

@Embeddable
public class Dollars {

	@Column(name="price")
	private BigDecimal value;

	public Dollars() {
	}
	
	private Dollars(BigDecimal value) {
		this.value = value;
	}
	
	public static Result<Dollars> create(BigDecimal dollarAmount){
		if(dollarAmount.compareTo(new BigDecimal(-1))<=0) {
			return Result.error("Dollar amount cannot be negative");
		}
		
		return Result.ok(new Dollars(dollarAmount));
		
	}
	
	public static Dollars of(BigDecimal dollarAmount) {
		Dollars zeroAmount= create(dollarAmount).getData();
		System.out.println(zeroAmount.getValue());
		return zeroAmount;
		
	}
	
	public static Dollars multiply(Dollars dollar,double multiplier) {
		return new Dollars(dollar.value.multiply(new BigDecimal(multiplier)));
	}
	
	
	public static Dollars add(Dollars dollars1,Dollars dollars2) {
		return new Dollars(dollars1.value.add(dollars2.value));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dollars other = (Dollars) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	public boolean isZero() {
		return new BigDecimal(0).equals(this.value);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
