package br.ufpe.ormsmells.projectionSmell.badcase;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import br.ufpe.ormsmells.auxiliary.Email;
import br.ufpe.ormsmells.eagerSmell.badcase.Person;
import br.ufpe.ormsmells.eagerSmell.goodcase.Student;

/**
 * @testcase_name BadCaseprojectionSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Data Retrieval With-out Projection for Read-only
 * 
 * @description A DAO class GeneralDao with ORM queries retrieving objects without projection
 * @number_of_smell_instances 3
 * @number_of_others_smell_instances 0
 * @challenges 	The detector must point the smells in queries without projection in methods: 
 * 				findStudentById, findStudents, findPersonByEmail
 */

public class GeneralDao {
	@Autowired
	private EntityManager entityManager;
		
	public Student findStudentById(Integer id) {
		String hql = "FROM Student d WHERE d.id = :id";
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Student) q.getSingleResult();
	}
	
	public Student findStudents(Integer id){
		StringBuilder hql = new StringBuilder("SELECT st");
		hql.append(" FROM Student st");
		hql.append(" WHERE st.id = :id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Student) q.getSingleResult();
	}
	
	public Person findPersonByEmail(Email email){
		StringBuilder hql = new StringBuilder("select person");
		hql.append(" FROM Person person");
		hql.append(" JOIN FETCH person.email em");
		hql.append(" JOIN FETCH person.principalAddress ad");
		hql.append(" LEFT JOIN FETCH person.students st");
		hql.append(" WHERE em.id = :email_id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("email_id", email.getId());
		return (Person) q.getSingleResult();
	}
	
}
