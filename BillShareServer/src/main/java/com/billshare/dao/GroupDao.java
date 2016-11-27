package com.billshare.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billshare.domain.Friend;
import com.billshare.domain.Groups;
import com.billshare.domain.User;
import com.billshare.dto.GroupDTO;
import com.billshare.utils.GroupInfo;

@Repository
@Transactional
public class GroupDao {
	@PersistenceContext
	private EntityManager entityManager;

	public boolean saveGroup(GroupDTO dto, Groups group) {
		try {
			entityManager.persist(group);
			saveFriend(dto.getFriendsIds(), group.getId());
			entityManager.flush();
			return true;
		} catch (HibernateException exception) {
			exception.printStackTrace();
			return false;
		}

	}

	private void saveFriend(List<Friend> friends, Integer groupId) {
		for (Friend friend : friends) {
			friend.setGroupId(groupId);
			entityManager.persist(friend);
		}
	}

	public List<GroupInfo> getList(String id) {
		List<GroupInfo> groupDTOs = new ArrayList<GroupInfo>();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Groups> criteriaQuery = criteriaBuilder.createQuery(Groups.class);
		Root<Groups> root = criteriaQuery.from(Groups.class);
		Predicate userId = criteriaBuilder.equal(root.get("adminId"), id);
		criteriaQuery.select(root).where(userId);
		List<Groups> resultList = entityManager.createQuery(criteriaQuery).getResultList();
		for (Groups group : resultList) {
			GroupInfo dto = new GroupInfo();
			dto.setGroupId(group.getId());
			dto.setAdminId(group.getAdminId());
			dto.setAmount(group.getAmount());
			dto.setName(group.getName());
			dto.setUsers(getFriends(group.getId()));
			groupDTOs.add(dto);
		}
		if (resultList.isEmpty()) {
			return Collections.emptyList();

		} else {
			return groupDTOs;

		}
	}

	private List<User> getFriends(Integer groupId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friend> criteriaQuery = criteriaBuilder.createQuery(Friend.class);
		Root<Friend> root = criteriaQuery.from(Friend.class);
		// Predicate userId = criteriaBuilder.equal(root.get("id"), groupId);
		Predicate groupID = criteriaBuilder.equal(root.get("groupId"), groupId);
		criteriaQuery.select(root).where(groupID);
		List<Friend> resultList = entityManager.createQuery(criteriaQuery).getResultList();
		List<User> users = new ArrayList<User>();
		for (Friend friend : resultList) {
			CriteriaBuilder criteriaBuilder1 = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery1 = criteriaBuilder1.createQuery(User.class);
			Root<User> root1 = criteriaQuery1.from(User.class);
			Predicate userId = criteriaBuilder1.equal(root1.get("id"), friend.getUserId());
			criteriaQuery1.select(root1).where(userId);
			users.add(entityManager.createQuery(criteriaQuery1).getSingleResult());
		}
		if (users.isEmpty()) {
			return Collections.emptyList();
		} else {
			return users;
		}

	}
	/*
	 * private List<User> getFriends(Integer groupId) { CriteriaBuilder
	 * criteriaBuilder = entityManager.getCriteriaBuilder(); CriteriaQuery<User>
	 * criteriaQuery = criteriaBuilder.createQuery(User.class); Root<User> root
	 * = criteriaQuery.from(User.class); //Predicate userId =
	 * criteriaBuilder.equal(root.get("id"), groupId); Join<User, Friend> join =
	 * root.join("id"); Predicate userId =
	 * criteriaBuilder.equal(join.get("groupId"), groupId);
	 * criteriaQuery.multiselect(join).where(userId); List<User> resultList =
	 * entityManager.createQuery(criteriaQuery).getResultList(); if
	 * (resultList.isEmpty()) { return Collections.emptyList(); } else { return
	 * resultList; }
	 * 
	 * }
	 */
}
