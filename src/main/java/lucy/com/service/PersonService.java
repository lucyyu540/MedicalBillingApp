package lucy.com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.dao.PersonRepo;
import lucy.com.model.Person;
@Service 
public class PersonService {
	private final PersonRepo personRepo;
	@Autowired
	public PersonService(@Qualifier("personRepo") PersonRepo personRepo) {
		this.personRepo = personRepo;
	}
	public void addPerson(Person p) {
		p.setId(this.personRepo.count()+1);
		this.personRepo.save(p);
	}
	public Iterable<Person> getAllPeople() {
		return this.personRepo.findAll();
	}
	public Iterable<Person> getPeopleByName(String name) {
		return this.personRepo.findPeopleByName(name);
	}
	public Person getPersonById(long id) {
		return this.personRepo.findById(id).orElse(null);
	}
	public void deletePersonById(long id) {
		this.personRepo.deleteById(id);
	}
	public int updatePersonById(long id, Person person) {
		Person p = getPersonById(id);
		if(p!=null) {
			System.out.println(p.getName());
			p.setName(person.getName());
			p.setDob(person.getDob());
			p.setSex(person.getSex());
			this.personRepo.save(p);
			return 1;
		}
		else {
			System.out.println("Not found");
			return 0;
		}
	}
}
