package com.billshare.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billshare.domain.User;
@Repository
@Transactional
public class UserDao {
	 @PersistenceContext
	  private EntityManager entityManager;

	public boolean register(User user) {
		    entityManager.persist(user);
		    return true;
		  }
	
	public User login(User user){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder
				.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		try {
			Predicate equalPassword = criteriaBuilder.equal(
					root.get("password"), user.getPassword());
			Predicate equalUserName = criteriaBuilder.equal(
					root.get("emailId"), user.getEmailId());
		
			criteriaQuery.where(equalUserName, equalPassword);
			User result = null;
			try {
				result = (User)entityManager.createQuery(criteriaQuery).getSingleResult();

				return result;
			} catch (NoResultException e) {
				return null;
				//e.printStackTrace();

			}
		} catch (Exception exception) {

		}
		return null;
	}
	}

