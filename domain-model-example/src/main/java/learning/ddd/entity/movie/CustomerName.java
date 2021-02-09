package learning.ddd.entity.movie;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import learning.ddd.utils.Result;

@Embeddable
public class CustomerName {

	@Column(name="name")
	private String value;
	
	
	
	public CustomerName() {
		super();
	}

	private CustomerName(String name) {
		this.value=name;
	}
	
	public static Result<CustomerName> create(String customerName){
		
		customerName=(Objects.isNull(customerName))?"":customerName.trim();
		
		if(customerName.length()==0) {
			return Result.error("Customer name should not be empty");
		}
		
		if(customerName.length()>100) {
			return Result.error("Customer name is too long");
		}
		
		return Result.ok(new CustomerName(customerName));
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
		CustomerName other = (CustomerName) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
	
}
