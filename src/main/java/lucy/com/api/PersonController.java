package lucy.com.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lucy.com.model.Person;
import lucy.com.service.PersonService;
@RequestMapping("api/v1/person")
@RestController
public class PersonController {
	private final PersonService personService;
	@Autowired
	public PersonController(PersonService ps) {
		this.personService = ps;
	}
	@PostMapping
	public Person addPerson(@RequestBody @Valid @NonNull Person p) {
		return this.personService.addPerson(p);
	}
	@GetMapping
	public Iterable<Person> getAllPeople() {
		return this.personService.getAllPeople();
	}
	@GetMapping(path = "id={id}")
	public Person getPersonById(@PathVariable("id") long id) {
		return this.personService.getPersonById(id);
	}
	@GetMapping(path = "name={name}")
	public Iterable<Person> getPeopleByName(@PathVariable("name") String name) {
		return this.personService.getPeopleByName(name);
	}
	@PutMapping(path = "{id}")
	public void updatePerson(@PathVariable("id") Long id, @RequestBody @Valid @NonNull Person p) {
		this.personService.updatePersonById(id, p);
	}
	@DeleteMapping(path = "{id}")
	public void deletePersonById(@PathVariable("id") Long id) {
		this.personService.deletePersonById(id);
	}
	
}
