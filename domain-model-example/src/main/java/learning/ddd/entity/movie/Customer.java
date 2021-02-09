package learning.ddd.entity.movie;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import learning.ddd.entity.common.Email;
import learning.ddd.entity.common.ExpirationDate;
import learning.ddd.utils.BaseEntity;
import learning.ddd.utils.Result;


@Entity
@Table(name="customer")
public class Customer extends BaseEntity{

	
	private CustomerName customerName;
	
	private Email email;

	
	@Embedded
	@AttributeOverrides(
				@AttributeOverride(name = "value",column = @Column(name="money_spent"))
			)
	private Dollars moneySpent;
	
	private CustomerStatus customerStatus;
	
	
	
	public Customer() {
	
	}

	public Customer(CustomerName customerName, Email email) {
		super();
		this.customerName = customerName;
		this.email = email;
		
		this.moneySpent=Dollars.of(BigDecimal.ZERO);
		this.customerStatus=CustomerStatus.regular();
	}

	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(CustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}

	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<PurchasedMovie> purchasedMovies;

	public CustomerName getCustomerName() {
		return customerName;
	}

	public void setCustomerName(CustomerName customerName) {
		this.customerName = customerName;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Dollars getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(Dollars moneySpent) {
		this.moneySpent = moneySpent;
	}

	public List<PurchasedMovie> getPurchasedMovies() {
		return purchasedMovies;
	}

	public void setPurchasedMovies(List<PurchasedMovie> purchasedMovies) {
		this.purchasedMovies = purchasedMovies;
	}

	public Result<Boolean> canPromote(){
		if(customerStatus.isAdvanced()) {
			return Result.error("The customer already has the Advanced status");
		}
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.YEAR,-1);
		
		
		
		if(purchasedMovies.stream()
						   .filter(pm->pm.getPurchaseDate().after( cal.getTime()))
						   .map(pm->(pm.getPrice().getValue()))
						   .reduce(new BigDecimal(0),(acc,val)->acc.add(val)).compareTo(new BigDecimal(100))<0) {
			return Result.error("The customer has to have at least 100 dollars spent during the last year");
		}
		
		return Result.ok(true);
	}

	
	public void promote() {
		if(!canPromote().isSuccess()) {
			throw new RuntimeException();
		}
		
		this.customerStatus=this.customerStatus.promote();
		
	}
	
	public boolean hasPurchased(Movies movie) {
		return this.purchasedMovies.stream().anyMatch(pm->pm.getMovie().getId()==movie.getId());
	}
	
	
	public void purchaseMovie(Movies movie) {
		ExpirationDate expirationDate=movie.getExpirationDate();
		Dollars price=movie.calculatePrice(this.customerStatus);
		
		PurchasedMovie purchasedMovie=new PurchasedMovie(movie,this,price,expirationDate);
		purchasedMovie.setCustomer(this);
		this.purchasedMovies.add(purchasedMovie);
		this.moneySpent=Dollars.add(this.moneySpent, price);
		
	}
}
