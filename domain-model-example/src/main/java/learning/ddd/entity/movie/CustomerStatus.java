package learning.ddd.entity.movie;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import ch.qos.logback.core.util.Duration;
import learning.ddd.entity.common.ExpirationDate;

@Embeddable
public class CustomerStatus {

	@Transient
	private Date _expirationDate;

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private CustomerStatusType customerStatusType;
	
	@Column(name="status_expiration_date")
	private ExpirationDate expirationDate;
	
	private CustomerStatus(CustomerStatusType type,ExpirationDate expirationDate) {
		this.customerStatusType=type;
		this.expirationDate=expirationDate;
	}
	
	private CustomerStatus() {
	}

	
	public BigDecimal getDiscount() {
		return isAdvanced()?new BigDecimal(0.25):BigDecimal.ZERO;
	}
	
	public CustomerStatus promote() {
		return new CustomerStatus(CustomerStatusType.Advanced,ExpirationDate.create(new Date(Duration.buildByDays(365).getMilliseconds())).getData());
	}

	public CustomerStatusType getCustomerStatusType() {
		return customerStatusType;
	}

	public void setCustomerStatusType(CustomerStatusType customerStatusType) {
		this.customerStatusType = customerStatusType;
	}

	public ExpirationDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(ExpirationDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	public boolean isAdvanced() {
		return this.customerStatusType.equals(CustomerStatusType.Advanced)&& !expirationDate.isExpired();
	}
	
	
	public static CustomerStatus regular() {
		return new CustomerStatus(CustomerStatusType.Regular,null);
	}
	
	
}
