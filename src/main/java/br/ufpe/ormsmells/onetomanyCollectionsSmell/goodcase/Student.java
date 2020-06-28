package br.ufpe.ormsmells.onetomanyCollectionsSmell.goodcase;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import br.ufpe.ormsmells.auxiliary.ClassDate;
import br.ufpe.ormsmells.eagerSmell.badcase.Person;
/**
 * @testcase_name GoodCaseOnetomanyCollectionsSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Unilateral OneToMany with Inappropriate Use of Collections
 * 
 * @description A domain class Student with bilateral and unilateral (with appropriate use of collections) OneToMany.
 * @number_of_smell_instances 0
 * @challenges the detector must ignore the smells from unilateral OneToMany with Set type (classDates) and bilateral OneToMany (listPerson) 
 */

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "listPerson")
	private List<Person> listPerson;
	
	@OneToMany
	private Set<ClassDate> classDates;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;

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
