package learning.ddd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.ddd.entity.movie.Movies;

public interface MovieRepository extends JpaRepository<Movies,Long>{

}
