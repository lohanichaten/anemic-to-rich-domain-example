package learning.ddd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.ddd.entity.movie.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
