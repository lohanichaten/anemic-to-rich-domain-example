package learning.ddd.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerDto {

	private long id;
	
	private String name;
	
	private String email;
	
	
	private String status;
	
	private Date expirationDate;
	
	private BigDecimal moneySpent;
	
	private List<PurchasedMovieDto> purchasedMovies;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(BigDecimal moneySpent) {
		this.moneySpent = moneySpent;
	}

	public List<PurchasedMovieDto> getPurchasedMovies() {
		return purchasedMovies;
	}

	public void setPurchasedMovies(List<PurchasedMovieDto> purchasedMovies) {
		this.purchasedMovies = purchasedMovies;
	}
	
	
	
}
