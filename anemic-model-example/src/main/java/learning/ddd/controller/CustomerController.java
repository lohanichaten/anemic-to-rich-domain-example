package learning.ddd.controller;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.ddd.entity.Customer;
import learning.ddd.entity.CustomerStatus;
import learning.ddd.entity.Movie;
import learning.ddd.repository.CustomerRepository;
import learning.ddd.repository.MovieRepository;
import learning.ddd.service.CustomerService;
import learning.ddd.service.MovieService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	
	private CustomerRepository customerRepository;
	private CustomerService customerService;
	private MovieService movieService;
	private MovieRepository movieRepository;
	
	
	public CustomerController(CustomerRepository customerRepository, CustomerService customerService,
			MovieService movieService, MovieRepository movieRepository) {
		
		this.customerRepository = customerRepository;
		this.customerService = customerService;
		this.movieService = movieService;
		this.movieRepository = movieRepository;
	}





	@GetMapping("/customer/{id}")
	public ResponseEntity searchCustomerById(@PathVariable("id") long id){
		Optional<Customer> customer=customerRepository.findById(id);
		if(customer.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(customer.get());
		
	}
	
	
	@GetMapping("/customer")
	public ResponseEntity retrieveAllCustomer() {
		return ResponseEntity.ok(customerRepository.findAll());
	}
	
	
	@PostMapping("/customer")
	public ResponseEntity saveCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		
		if(customerRepository.findByEmail(customer.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Duplicate Email");
		}
		
		customerRepository.save(customer);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("/customer/{id}")
	public ResponseEntity updateCustomer(@Valid @RequestBody Customer customer,BindingResult result,@PathVariable("id") long id){
		
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		
		Optional<Customer> dbCustomer=customerRepository.findById(id);
		if(dbCustomer.isEmpty()) {
			return ResponseEntity.badRequest().body("No Customer Found with id");
		}
		
	    Customer persistedCustomer=    dbCustomer.get();
		
	    persistedCustomer.setName(customer.getName());
	    
	    return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("/customer/{id}/movies")
	public ResponseEntity purchaseMovie(@PathVariable("id") Long customerId,@RequestBody Long movieId) {
		Optional<Movie> movie=		movieRepository.findById(movieId);
	
		if(movie.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		
		Optional<Customer> dbCustomer=customerRepository.findById(customerId);
		if(dbCustomer.isEmpty()) {
			return ResponseEntity.badRequest().body("No Customer Found with id");
		}
		
		Customer customer=dbCustomer.get();
		
		if(customer.getPurchasedMovies()
									.stream()
									.anyMatch(purchasedMovie->(purchasedMovie.getMovie().getId()==movieId 
											&& purchasedMovie.getExpirationDate()==null
											||purchasedMovie.getExpirationDate().after(new Date(Instant.now().toEpochMilli()))))) {
			return ResponseEntity.badRequest().body("Movie Already purchased");
		}
		
		
	   customerService.purchaseMovie(customer, movie.get());
	   customerRepository.save(customer);
		
	   
	   return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/promote")
	public ResponseEntity promoteCustomer(@PathVariable("id") long id) {
		Optional<Customer> customerOptional=customerRepository.findById(id);
		if(customerOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Customer customer=customerOptional.get();
		
		if(customer.getStatus()==CustomerStatus.ADVANCED 
		  && (customer.getStatusExpirationDate()==null
		      || customer.getStatusExpirationDate().before(new Date(Instant.now().toEpochMilli())))) {
			return ResponseEntity.badRequest().body("Cannot promote the customer");
		}
		
		boolean success=customerService.promoteCustomer(customer);
		if(success) {
			return ResponseEntity.badRequest().body("Cannot promote the customer");
		}
		
		customer.setStatus(CustomerStatus.ADVANCED);
		customerRepository.save(customer);
		
		return ResponseEntity.ok().build();
		
	}
	
}
