package br.ufpe.ormsmells.eagerSmell.goodcase;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufpe.ormsmells.auxiliary.Address;
import br.ufpe.ormsmells.auxiliary.ClassDate;
import br.ufpe.ormsmells.eagerSmell.badcase.Person;
/**
 * @testcase_name GoodCaseEagerSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Eager as a Fetch Strategy In Class-level (Static) Relationships
 * 
 * @description A domain class Student with relationships Lazy
 * @number_of_smell_instances 0
 * @number_of_others_smell_instances 0
 * @challenges the detector must ignore the relationships with implicit Lazy (classDates) and explicit Lazy (person, address) 
 */

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;
	
	@OneToMany
	private Set<ClassDate> classDates;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Address> address;

	private String enrollment;

	public int getId() {
		return id;
	}


	public Set<ClassDate> getClassDates() {
		return classDates;
	}


	public void setClassDates(Set<ClassDate> classDates) {
		this.classDates = classDates;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public String getEnrollment() {
		return enrollment;
	}


	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
   
}
