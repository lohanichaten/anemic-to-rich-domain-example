package learning.ddd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.ddd.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long>{

}
