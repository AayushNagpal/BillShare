package com.billshare.dao;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billshare.constants.UserMessages;
import com.billshare.domain.Friend;
import com.billshare.domain.Groups;
import com.billshare.domain.User;
import com.billshare.dto.GroupDTO;
import com.billshare.dto.UserDTO;
import com.billshare.utils.FCMUtils;
import com.billshare.utils.GroupInfo;
import com.billshare.utils.ImageUtils;
import com.google.api.client.util.Base64;

@Repository
@Transactional
public class GroupDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public boolean saveGroup(GroupDTO dto, Groups group) {
		try {
			entityManager.persist(group);
			saveFriend(dto.getFriendsIds(), group.getId(), group.getName());
			entityManager.flush();
			return true;
		} catch (HibernateException exception) {
			exception.printStackTrace();
			return false;
		}

	}

	private void saveFriend(List<Friend> friends, Integer groupId, String name) {
		for (Friend friend : friends) {
			friend.setGroupId(groupId);
			entityManager.persist(friend);
			User userInfo = getUserInfo(friend.getUserId());
			FCMUtils.instance().sendNotification(userInfo.getDeviceId(), "",
					UserMessages.FRIEND_NOTIFICATION + " " + name);
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
			dto.setLimitAmount(group.getAmountLimit());
			dto.setName(group.getName());
			dto.setImage(ImageUtils.instance().getByteStringFromBlob(group.getImageFile()));
			dto.setUsers(getFriendsByGroupId(group));
			groupDTOs.add(dto);
		}

		groupDTOs.addAll(getGroupsByUserId(id));
		// groupDTOs.addAll();
		if (groupDTOs.isEmpty()) {
			return Collections.emptyList();

		} else {
			return groupDTOs;

		}
	}

	private List<GroupInfo> getGroupsByUserId(String userId) {
		List<GroupInfo> groupDTOs = new ArrayList<GroupInfo>();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friend> criteriaQuery = criteriaBuilder.createQuery(Friend.class);
		Root<Friend> root = criteriaQuery.from(Friend.class);
		// Predicate userId = criteriaBuilder.equal(root.get("id"), groupId);
		Predicate _userId = criteriaBuilder.equal(root.get("userId"), userId);
		criteriaQuery.select(root).where(_userId);
		List<Friend> resultList = entityManager.createQuery(criteriaQuery).getResultList();

		for (Friend friend : resultList) {
			CriteriaBuilder criteriaBuilder2 = entityManager.getCriteriaBuilder();
			CriteriaQuery<Groups> criteriaQuery2 = criteriaBuilder2.createQuery(Groups.class);
			Root<Groups> root2 = criteriaQuery2.from(Groups.class);
			Predicate id1 = criteriaBuilder2.equal(root2.get("id"), friend.getGroupId());
			criteriaQuery2.select(root2).where(id1);
			Groups singleResult = entityManager.createQuery(criteriaQuery2).getSingleResult();
			GroupInfo dto = new GroupInfo();
			dto.setGroupId(singleResult.getId());
			dto.setAdminId(singleResult.getAdminId());
			dto.setAmount(singleResult.getAmount());
			dto.setLimitAmount(singleResult.getAmountLimit());
			dto.setName(singleResult.getName());
			dto.setImage(ImageUtils.instance().getByteStringFromBlob(singleResult.getImageFile()));
			dto.setUsers(getFriendsByGroupId(singleResult));
			groupDTOs.add(dto);

			/*
			 * CriteriaBuilder criteriaBuilder1 =
			 * entityManager.getCriteriaBuilder(); CriteriaQuery<User>
			 * criteriaQuery1 = criteriaBuilder1.createQuery(User.class);
			 * Root<User> root1 = criteriaQuery1.from(User.class); Predicate id
			 * = criteriaBuilder1.equal(root1.get("id"), friend.getUserId());
			 * criteriaQuery1.select(root1).where(id);
			 * users.add(entityManager.createQuery(criteriaQuery1).
			 * getSingleResult());
			 */
		}
		if (groupDTOs.isEmpty()) {
			return Collections.emptyList();
		} else {

			return groupDTOs;
		}

	}

	private List<UserDTO> getFriendsByGroupId(Groups group) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friend> criteriaQuery = criteriaBuilder.createQuery(Friend.class);
		Root<Friend> root = criteriaQuery.from(Friend.class);
		// Predicate userId = criteriaBuilder.equal(root.get("id"), groupId);
		Predicate groupID = criteriaBuilder.equal(root.get("groupId"), group.getId());
		criteriaQuery.select(root).where(groupID);
		List<Friend> resultList = entityManager.createQuery(criteriaQuery).getResultList();
		List<UserDTO> users = new ArrayList<UserDTO>();
		for (Friend friend : resultList) {
			CriteriaBuilder criteriaBuilder1 = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery1 = criteriaBuilder1.createQuery(User.class);
			Root<User> root1 = criteriaQuery1.from(User.class);
			Predicate userId = criteriaBuilder1.equal(root1.get("id"), friend.getUserId());
			criteriaQuery1.select(root1).where(userId);
			User singleResult = entityManager.createQuery(criteriaQuery1).getSingleResult();
			UserDTO map = dozerBeanMapper.map((User) singleResult, UserDTO.class);
			users.add(map);
		}
		if (users.isEmpty()) {
			return Collections.emptyList();
		} else {
			User groupAdminInfo = getUserInfo(group.getAdminId());
			if (groupAdminInfo != null) {
				UserDTO map = dozerBeanMapper.map((User) groupAdminInfo, UserDTO.class);
				users.add(map);
			}
			return users;
		}

	}

	private void removeFriendsByGroupId(Groups group) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Friend> criteriaQuery = criteriaBuilder.createQuery(Friend.class);
		Root<Friend> root = criteriaQuery.from(Friend.class);
		// Predicate userId = criteriaBuilder.equal(root.get("id"), groupId);
		Predicate groupID = criteriaBuilder.equal(root.get("groupId"), group.getId());
		criteriaQuery.select(root).where(groupID);
		List<Friend> resultList = entityManager.createQuery(criteriaQuery).getResultList();
		for (Friend friend : resultList) {
			entityManager.remove(friend);
		}

	}

	private User getUserInfo(Integer id) {
		CriteriaBuilder criteriaBuilder1 = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery1 = criteriaBuilder1.createQuery(User.class);
		Root<User> root1 = criteriaQuery1.from(User.class);
		Predicate userId = criteriaBuilder1.equal(root1.get("id"), id);
		criteriaQuery1.select(root1).where(userId);
		return entityManager.createQuery(criteriaQuery1).getSingleResult();
	}

	public boolean update(GroupDTO groupDTO, Groups groups) {
		// Groups find = entityManager.find(Groups.class, groups.getId());

		entityManager.merge(groups);
		removeFriendsByGroupId(groups);
		saveFriend(groupDTO.getFriendsIds(), groups.getId(), groups.getName());
		entityManager.flush();
		if (groupDTO.getAmount().compareTo(groupDTO.getAmountLimit()) == 1) {
			List<Friend> friends = groupDTO.getFriendsIds();
			for (Friend friend : friends) {
			
				entityManager.persist(friend);
				User userInfo = getUserInfo(friend.getUserId());
				FCMUtils.instance().sendNotification(userInfo.getDeviceId(), "", groupDTO.getName()+ " limit amount is exceeded. "
						+ String.valueOf(groupDTO.getAmount().subtract(groupDTO.getAmountLimit())));
			}
		}
		return true;
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
