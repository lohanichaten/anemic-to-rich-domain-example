package learning.ddd.service;

import java.math.BigDecimal;
import java.util.Date;

import learning.ddd.entity.Customer;
import learning.ddd.entity.CustomerStatus;
import learning.ddd.entity.LicensingModel;
import learning.ddd.entity.Movie;

public interface CustomerService {

	BigDecimal calculatePrice(CustomerStatus status,Date statusExpirationDate, LicensingModel licensingModel);
	void purchaseMovie(Customer customer, Movie movie);
	boolean promoteCustomer(Customer customer);
	
	
}
