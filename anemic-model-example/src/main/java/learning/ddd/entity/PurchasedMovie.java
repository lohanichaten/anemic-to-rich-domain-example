package learning.ddd.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchased_movie")
public class PurchasedMovie extends BaseEntity{

	@OneToOne
	@JoinColumn(name="movie_id")
	private Movie movie;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	private BigDecimal price;
	
	private Date purchaseDate;
	
	private Date expirationDate;
	
	
	
	public PurchasedMovie() {
		
	}

	
	
	

	public PurchasedMovie(Movie movie, Customer customer, BigDecimal price, Date purchaseDate, Date expirationDate) {
		super();
		this.movie = movie;
		this.customer = customer;
		this.price = price;
		this.purchaseDate = purchaseDate;
		this.expirationDate = expirationDate;
	}





	public Customer getCustomer() {
		return customer;
	}





	public void setCustomer(Customer customer) {
		this.customer = customer;
	}









	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
}
