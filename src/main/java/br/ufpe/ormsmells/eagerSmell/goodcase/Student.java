package br.ufpe.ormsmells.eagerSmell.goodcase;

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

import br.ufpe.ormsmells.eagerSmell.badcase.Person;
/**
 * @testcase_name GoodCaseEagerSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * 
 * @description A domain class Student with relationships Lazy
 * @number_of_smells 0
 * @challenges the detector must ignore the relationships with implicit Lazy (listPerson) and explicit Lazy (person) 
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
	private Set<Person> listPerson;


	public int getId() {
		return id;
	}
   
}
