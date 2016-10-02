package com.billshare.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
