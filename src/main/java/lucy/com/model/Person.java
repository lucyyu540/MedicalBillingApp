package lucy.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity 
public class Person { 
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String sex;
	@NotBlank
	private String dob;
	/*CONSTRUCTORS*/
	public Person(
			@JsonProperty("name") String name, 
			@JsonProperty("sex") String sex, 
			@JsonProperty("dob") String dob) {
		this.setName(name);
		this.setSex(sex);
		this.setDob(dob);
	}
	public Person() {}
	@Override
	public boolean equals(Object p) { return this.getId()==((Person) p).getId();}
	@Override
	public int hashCode() {return ((Long)this.getId()).hashCode();}
	/*GETTER SETTER*/
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getSex() {return sex;}
	public void setSex(String sex) {this.sex = sex;}
	public String getDob() {return dob;}
	public void setDob(String dob) {this.dob = dob;}
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

}
