package learning.ddd.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PurchasedMovieDto {

	private MovieDto movies;
	private BigDecimal prices;
	private Date purchasedDate;
	private Date expirationDate;
	
	public PurchasedMovieDto() {
		super();
	}
	
	public PurchasedMovieDto(MovieDto movies, BigDecimal prices, Date purchasedDate, Date expirationDate) {
		super();
		this.movies = movies;
		this.prices = prices;
		this.purchasedDate = purchasedDate;
		this.expirationDate = expirationDate;
	}
	public MovieDto getMovies() {
		return movies;
	}
	public void setMovies(MovieDto movies) {
		this.movies = movies;
	}
	public BigDecimal getPrices() {
		return prices;
	}
	public void setPrices(BigDecimal prices) {
		this.prices = prices;
	}
	public Date getPurchasedDate() {
		return purchasedDate;
	}
	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
	
	
}
