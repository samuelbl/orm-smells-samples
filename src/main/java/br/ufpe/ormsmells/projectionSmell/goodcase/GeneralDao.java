package br.ufpe.ormsmells.projectionSmell.goodcase;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufpe.ormsmells.eagerSmell.badcase.Person;
import br.ufpe.ormsmells.eagerSmell.goodcase.Student;

/**
 * @testcase_name GoodCaseprojectionSmell
 * @version 0.1
 * @author Samuel Bristot Loli 
 * @author_mail sbl@cin.ufpe.br
 * @code_smell Data Retrieval With-out Projection for Read-only
 * 
 * @description A DAO class GeneralDao with ORM queries retrieving objects with projection
 * @number_of_smell_instances 0
 * @challenges 	The detector must ignore queries with projection in methods: 
 * 				findStudentById, findIdStudentbyEnrollment, findPersonByid
 */

public class GeneralDao {
private EntityManager entityManager;
	
	public Student findStudentById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new Student(enrollment) ");
		hql.append("FROM Student WHERE id = :id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Student) q.getSingleResult();
	}   
	

	public Integer findIdStudentbyEnrollment(Integer enrollment) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT id ");
		hql.append("FROM Student WHERE enrollment = :enrollment");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("enrollment", enrollment);
		return (Integer) q.getSingleResult();
	}   
	
	public Person findPersonByid(Integer id) {

		String projecao = 
				"  pe.id" +
				", pe.cpf" +
				", pe.email.id" +
				", pe.addres.id ";
		StringBuilder hql = new StringBuilder();
		hql.append( " SELECT "+ projecao +" FROM Person pe");
		hql.append(" JOIN FETCH pe.email em");
		hql.append(" JOIN FETCH pe.principalAddress ad");
		hql.append(" LEFT JOIN FETCH pe.students st");
		hql.append(" WHERE pe.id = :id");
		Query q = entityManager.createQuery(hql.toString());
		q.setParameter("id", id);
		return (Person) q.getSingleResult();
	}
}
