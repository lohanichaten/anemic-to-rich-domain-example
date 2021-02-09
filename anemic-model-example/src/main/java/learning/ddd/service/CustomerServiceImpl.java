package learning.ddd.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.util.Duration;
import learning.ddd.entity.Customer;
import learning.ddd.entity.CustomerStatus;
import learning.ddd.entity.LicensingModel;
import learning.ddd.entity.Movie;
import learning.ddd.entity.PurchasedMovie;

@Service
public class CustomerServiceImpl implements CustomerService{

	private MovieService movieService;
	
	
	
	public CustomerServiceImpl(MovieService movieService) {
		this.movieService=movieService;
	}
	
	
	@Override
	public BigDecimal calculatePrice(CustomerStatus status, Date statusExpirationDate, LicensingModel licensingModel) {
		BigDecimal price=null;
		switch(licensingModel) {
		  case TWODAYS:price=new BigDecimal(4);
		                           break;
		  case LIFELONG:price=new BigDecimal(8); break;
		  
		  default:throw new IllegalArgumentException();
		}
		
		if(status==CustomerStatus.ADVANCED &&(statusExpirationDate==null||statusExpirationDate.after(new Date(Instant.now().toEpochMilli())))) {
			price=price.multiply(new BigDecimal(0.75));
		}
		return price;
	}

	@Override
	public void purchaseMovie(Customer customer, Movie movie) {
			Date expirationDate=	movieService.getExipirationDate(movie.getLicensingModel());
			BigDecimal price = calculatePrice(customer.getStatus(), customer.getStatusExpirationDate(), movie.getLicensingModel());
			
			PurchasedMovie purchasedMovie=new PurchasedMovie();
			purchasedMovie.setMovie(movie);
			purchasedMovie.setExpirationDate(expirationDate);
			purchasedMovie.setPrice(price);
			purchasedMovie.setCustomer(customer);
			customer.getPurchasedMovies().add(purchasedMovie);
			purchasedMovie.setPurchaseDate(Date.from(Instant.now()));
			
			customer.setMoneySpent(price.add(customer.getMoneySpent()));
			
	}

	@Override
	public boolean promoteCustomer(Customer customer) {
		
		 // at least 2 active movies during the last 30 days
		  boolean leastTwoMovieActiveInMonth=customer
				                                .getPurchasedMovies()
				                                .stream()
				                                .filter(lastMonthActiveMovieRule())
				                                .count()>2;
		if(leastTwoMovieActiveInMonth) {
			return true;
		}	                               
			
	   // at least 100 dollars spent during the last year
		
		boolean purchasedAmountInYearGtThenHundread=customer.getPurchasedMovies()
											.stream()
											.filter(moviePurchasedInYear())
											.map(movie->movie.getPrice())
											.reduce(new BigDecimal(0),(acc,val)->acc.add(val))
											.compareTo(new BigDecimal(100))>0;
											
								
	 return purchasedAmountInYearGtThenHundread;
	 
	}
	
	
	private Predicate<PurchasedMovie> lastMonthActiveMovieRule() {
		Predicate<PurchasedMovie> nullExpirationDate=(movie)->movie.getExpirationDate()==null;
		Predicate<PurchasedMovie> purchasedInMonth=  (movie)->movie.getExpirationDate().after(new Date(Duration.buildByDays(30).getMilliseconds()));
		
		return nullExpirationDate.or(purchasedInMonth);
	}
	
	
	private Predicate<PurchasedMovie> moviePurchasedInYear(){
		Predicate<PurchasedMovie> purchasedMovieInYearPredicate=(movie)->movie.getPurchaseDate().after(new Date(Duration.buildByDays(365).getMilliseconds()));
		return purchasedMovieInYearPredicate;
	}
	
}
