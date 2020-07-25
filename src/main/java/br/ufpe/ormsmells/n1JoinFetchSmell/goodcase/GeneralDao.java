package br.ufpe.ormsmells.n1JoinFetchSmell.goodcase;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.ormsmells.auxiliary.Email;
import br.ufpe.ormsmells.eagerSmell.badcase.Person;
import br.ufpe.ormsmells.eagerSmell.goodcase.Student;
/**
 * @testcase_name GoodCasen1JoinFetchSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Lack of Join Fetch in ORM Queries to Retrieve Objects With Eager Attributes
 * 
 * @description A DAO class GeneralDao with ORM queries retrieving objects with Eager and Lazy attributes 
 * @number_of_smell_instances 0
 * @challenges 	The detector must ignore the queries with Lazy attributes (findStudents) and 
 * 				also ignore Eager attributes using Join Fetch (findPersonById, findPersonByEmail)
 */
@Entity
public class GeneralDao {
	
	@Autowired
	private EntityManager entityManager;
		
	public Person findPersonById(Integer id) {
		StringBuilder hql = new StringBuilder("select new Person(pe.cpf, pe.name)");
		hql.append(" FROM Person pe");
		hql.append(" LEFT JOIN FETCH pe.students st");
		hql.append(" JOIN FETCH pe.email em");
		hql.append(" JOIN FETCH pe.principalAddress ad");
		hql.append(" WHERE pe.id = :id ");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Person) q.getSingleResult();
	}

	public Student findStudents(Integer id){
		StringBuilder hql = new StringBuilder("SELECT new Student(st.id, st.enrollment)");
		hql.append(" FROM Student st");
		hql.append(" WHERE st.id = :id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Student) q.getSingleResult();
	}
	
	public Person findPersonByEmail(Email email){
		StringBuilder hql = new StringBuilder("select new Person(pe.cpf, pe.name)");
		hql.append(" FROM Person pe");
		hql.append(" JOIN FETCH pe.email em");
		hql.append(" JOIN FETCH pe.principalAddress ad");
		hql.append(" LEFT JOIN FETCH pe.students st");
		hql.append(" WHERE em.id = :email_id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("email_id", email.getId());
		return (Person) q.getSingleResult();
	}

}