package br.ufpe.ormsmells.onetomanyCollectionsSmell.badcase;

import java.util.Collection;
import java.util.List;

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
import br.ufpe.ormsmells.onetomanyCollectionsSmell.goodcase.Student;

/**
 * @testcase_name BadCaseOnetomanyCollectionsSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Unilateral OneToMany with Inappropriate Use of Collections
 * 
 * @description A domain class Person with unilaterals OneToMany
 * @number_of_smell_instances 2
 * @challenges the detector must point the smells from unilateral OneToMany with List type (students) and Collection type (principalAddress, othersAdress) 
 */
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Email email;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Address principalAddress;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Student> students;
	
	@OneToMany
	private Collection<Address> worksAdress;
	
	@OneToMany
	private Collection<Address> housesAdress;
	
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

	

	
}
