package br.ufpe.ormsmells.n1JoinFetchSmell.badcase;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import br.ufpe.ormsmells.auxiliary.Email;
import br.ufpe.ormsmells.eagerSmell.badcase.Person;

/**
 * @testcase_name BadCasen1JoinFetchSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Lack of Join Fetch in ORM Queries to Retrieve Objects With Eager Attributes
 * 
 * @description A DAO class PersonDao with ORM queries retrieving objects with Eager attributes 
 * @number_of_smell_instances 3
 * @challenges 	The detector must point the smells showing the Eager attributes without Join Fetch in the methods:
 * 			   	findPersonById - attributes email, principalAddress, students;
 * 				findPersonWithStudents - attributes email, principalAddress;
 * 				findPersonByEmail - attributes students;
 */

@Entity
public class PersonDao {
	
	@Autowired
	private EntityManager entityManager;
		
	public Person findPersonById(Integer id) {
		String hql = "SELECT new Person(p.cpf, p.name) FROM Person p WHERE p.id = :id";
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Person) q.getSingleResult();
	}

	public Person findPersonWithStudents(Integer id){
		StringBuilder hql = new StringBuilder("Select new Person(pe.cpf, pe.name)");
		hql.append(" FROM Person pe");
		hql.append(" LEFT JOIN FETCH pe.students st");
		hql.append(" WHERE p.id = :id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Person) q.getSingleResult();
	}
	
	public Person findPersonByEmail(Email email){
		StringBuilder hql = new StringBuilder("select new Person(pe.cpf, pe.name)");
		hql.append(" FROM Person pe");
		hql.append(" LEFT JOIN FETCH pe.email em");
		hql.append(" LEFT JOIN FETCH pe.principalAddress ad");
		hql.append(" WHERE em.id = :email_id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("email_id", email.getId());
		return (Person) q.getSingleResult();
	}

}
