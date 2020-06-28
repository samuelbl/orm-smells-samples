package br.ufpe.ormsmells.eagerSmell.badcase;

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

import br.ufpe.ormsmells.auxiliary.Address;
import br.ufpe.ormsmells.auxiliary.Email;
import br.ufpe.ormsmells.eagerSmell.goodcase.Student;

/**
 * @testcase_name BadCaseEagerSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Eager as a Fetch Strategy In Class-level (Static) Relationships
 * 
 * @description A domain class Person with relationships EAGER
 * @number_of_smell_instances 3
 * @challenges the detector must point the smells from implicit EAGER (email) and explicit Eager (principalAddress, students) 
 */
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	private Email email;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Address principalAddress;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Student> students;
	
	@OneToMany
	private Set<Address> othersAdress;
	
	private Integer cpf;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Set<Address> getOthersAdress() {
		return othersAdress;
	}

	public void setOthersAdress(Set<Address> othersAdress) {
		this.othersAdress = othersAdress;
	}

	public Address getPrincipalAddress() {
		return principalAddress;
	}

	public void setPrincipalAddress(Address principalAddress) {
		this.principalAddress = principalAddress;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	
}
