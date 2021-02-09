package learning.ddd.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer extends BaseEntity{

	private String name;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	
	private Date statusExpirationDate;
	
	private BigDecimal moneySpent;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	@OrderColumn
	private List<PurchasedMovie> purchasedMovies;
	
	
	public Customer(String name, String email, CustomerStatus status, Date statusExpirationDate, BigDecimal moneySpent,
			List<PurchasedMovie> purchasedMovies) {
		super();
		this.name = name;
		this.email = email;
		this.status = status;
		this.statusExpirationDate = statusExpirationDate;
		this.moneySpent = moneySpent;
		this.purchasedMovies = purchasedMovies;
	}

	

	public Customer() {
		super();
		
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public Date getStatusExpirationDate() {
		return statusExpirationDate;
	}

	public void setStatusExpirationDate(Date statusExpirationDate) {
		this.statusExpirationDate = statusExpirationDate;
	}

	public BigDecimal getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(BigDecimal moneySpent) {
		this.moneySpent = moneySpent;
	}

	public List<PurchasedMovie> getPurchasedMovies() {
		return purchasedMovies;
	}

	public void setPurchasedMovies(List<PurchasedMovie> purchasedMovies) {
		this.purchasedMovies = purchasedMovies;
	}
	
	
	
	
}