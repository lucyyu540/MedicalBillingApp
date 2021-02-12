package lucy.com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.model.Person;
import lucy.com.repo.PersonRepo;
@Service 
public class PersonService {
	private final PersonRepo personRepo;
	@Autowired
	public PersonService(@Qualifier("personRepo") PersonRepo personRepo) {
		this.personRepo = personRepo;
	}
	/*CREATE*/
	public void addPerson(Person p) {
		p.setId(this.personRepo.count()+1);
		this.personRepo.save(p);
	}
	/*READ*/
	public Person getPersonById(long id) {
		return this.personRepo.findById(id).orElse(null);
	}
	public Iterable<Person> getAllPeople() {
		return this.personRepo.findAll();
	}
	public Iterable<Person> getPeopleByName(String name) {
		return this.personRepo.findPeopleByName(name);
	}
	/*UPDATE*/
	public int updatePersonById(long id, Person person) {
		try { 
			Person p = getPersonById(id);
			System.out.println(p.getName());
			p.setName(person.getName());
			p.setDob(person.getDob());
			p.setSex(person.getSex());
			this.personRepo.save(p);
			return 1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	/*DELETE*/
	public void deletePersonById(long id) {
		this.personRepo.deleteById(id);
	}
}
