package lucy.com.repo;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lucy.com.model.Person;

@Repository("personRepo")
public interface PersonRepo extends CrudRepository<Person, Long>  {
	@Query("SELECT p FROM Person p WHERE p.name=?1")
	Iterable<Person> findPeopleByName(String name);
	@Query("SELECT p.id FROM Person p WHERE p.name=?1")
	Optional<Long> findIdByName(String name);
	
}
