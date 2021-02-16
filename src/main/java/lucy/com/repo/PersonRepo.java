package lucy.com.repo;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lucy.com.model.Person;
@Repository("personRepo")
public interface PersonRepo extends CrudRepository<Person, Long>  {
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	//List<Movie> searchByTitleLike(@Param("title") String title);
	@Query("SELECT p FROM Person p WHERE p.name LIKE %:name%")
	Iterable<Person> findPeopleByName(@Param("name")String name);
	
}
