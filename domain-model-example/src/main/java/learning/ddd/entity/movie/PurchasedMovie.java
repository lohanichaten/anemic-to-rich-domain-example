package learning.ddd.entity.movie;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import learning.ddd.entity.common.ExpirationDate;
import learning.ddd.utils.BaseEntity;

@Entity
@Table(name="purchased_movie")
public class PurchasedMovie extends BaseEntity{

	@OneToOne
	@JoinColumn(name="movie_id")
	private Movies movie;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="purchase_date")
	private Date purchaseDate;
	
	private ExpirationDate expirationDate;
	
	private Dollars price;
	
	public PurchasedMovie() {
		
	}

	public PurchasedMovie(Movies movie, Customer customer, Dollars price, ExpirationDate expirationDate){
		if (price == null || price.isZero()) {
			throw new IllegalArgumentException("Invalid price");
		}
		
		if(expirationDate==null||expirationDate.isExpired()) {
			throw new IllegalArgumentException("Date is already expired");
		}
		
	    Objects.requireNonNull(movie,"Invalid movie");
	    Objects.requireNonNull(customer, "Invalid Customer");
	    
	    this.movie=movie;
	    this.customer=this.customer;
	    this.price=price;
	    this.expirationDate=expirationDate;
	    this.purchaseDate=new Date();
	 }
	

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public ExpirationDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(ExpirationDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	 public Dollars getPrice() {
			return price;
	 }


	 public void setPrice(Dollars price) {
			this.price = price;
	 }

}
