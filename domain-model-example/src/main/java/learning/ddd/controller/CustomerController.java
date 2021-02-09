package learning.ddd.controller;

import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.ddd.dto.CreateCustomerDto;
import learning.ddd.dto.UpdateCustomerDto;
import learning.ddd.entity.common.Email;
import learning.ddd.entity.movie.Customer;
import learning.ddd.entity.movie.CustomerName;
import learning.ddd.entity.movie.Movies;
import learning.ddd.repository.CustomerRepository;
import learning.ddd.repository.MovieRepository;
import learning.ddd.utils.Result;

@RestController
@RequestMapping("/api")
public class CustomerController {

	private CustomerRepository customerRepository;
	private MovieRepository movieRepository;
	
	public CustomerController(CustomerRepository customerRepository,MovieRepository movieRepository) {
		this.customerRepository=customerRepository;
		this.movieRepository=movieRepository;
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity searchCustomerById(@PathVariable("id") long id){
		//to do
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/customer")
	public ResponseEntity retrieveAllCustomer() {
		//to do
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/customer")
	public ResponseEntity saveCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
		Result<CustomerName> customerNameorError=CustomerName.create(createCustomerDto.getName());
		Result<Email>  emailOrError=Email.create(createCustomerDto.getEmail());
		System.out.println(emailOrError.getErrorMessage());
		
		Result  result=Result.combine(customerNameorError,emailOrError);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result.getErrorMessage());
		}
		Customer customer=new Customer(customerNameorError.getData(),emailOrError.getData());
		
		customerRepository.save(customer);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("/customer/{id}")
	public ResponseEntity updateCustomer(@RequestBody UpdateCustomerDto updateCustomerDto,@PathVariable("id") long id) {
		Result<CustomerName> result=CustomerName.create(updateCustomerDto.getName());
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result.getErrorMessage());
		}
		
		Optional<Customer> optionalCustomer=customerRepository.findById(id);
		if(optionalCustomer.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		Customer customer=optionalCustomer.get();
		customer.setCustomerName(result.getData());
		customerRepository.save(customer);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/customer/{id}/movies")
	public ResponseEntity purchaseMovie(@PathVariable("id") Long customerId,@RequestBody Long movieId) {
		Optional<Movies> optionalMovie=movieRepository.findById(movieId);
		if(optionalMovie.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		
		if(optionalCustomer.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		Customer customer=optionalCustomer.get();
		if(customer.hasPurchased(optionalMovie.get())) {
			return ResponseEntity.badRequest().build();
		}
		
		customer.purchaseMovie(optionalMovie.get());
		customerRepository.save(customer);
		
		return ResponseEntity.ok().build();
		
	}
	
	@PostMapping("/{id}/promote")
	public ResponseEntity promoteCustomer(@PathVariable("id") long id) {
		Optional<Customer> optionalCustomer=  customerRepository.findById(id);
	
		if(optionalCustomer.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		Customer customer=optionalCustomer.get();
		Result<Boolean> canPromote=customer.canPromote();
		if(!canPromote.isSuccess()) {
			return ResponseEntity.badRequest().build();
		}
		
		customer.promote();
		customerRepository.save(customer);
	
		return ResponseEntity.ok().build();
	}
}
