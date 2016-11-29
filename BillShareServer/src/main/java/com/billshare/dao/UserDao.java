package com.billshare.dao;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billshare.domain.User;
import com.billshare.dto.UserDTO;
import com.billshare.utils.ImageUtils;

@Repository
@Transactional
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public boolean register(User user) {
		// user.setPassword(getEncrypted(user.getPassword()));
		try {
			entityManager.persist(user);
			entityManager.flush();
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
		return true;
	}

	private String getEncrypted(String pwd) {
		String key = "Bar12345Bar12345";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(pwd.getBytes());

			StringBuilder sb = new StringBuilder();
			for (byte b : encrypted) {
				sb.append((char) b);
			}

			String enc = sb.toString();
			return enc;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public UserDTO login(User user) {
		// user.setPassword(getEncrypted(user.getPassword()));
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		try {

			Predicate equalPassword = criteriaBuilder.equal(root.get("password"), user.getPassword());
			Predicate equalUserName = criteriaBuilder.equal(root.get("emailId"), user.getEmailId());

			criteriaQuery.where(equalUserName, equalPassword);
			User result = null;
			try {
				result = (User) entityManager.createQuery(criteriaQuery).getSingleResult();
				UserDTO map = dozerBeanMapper.map((User) result, UserDTO.class);
				map.setProfilePic(ImageUtils.instance().getByteStringFromBlob(result.get_profilePic()));
				return map;
			} catch (NoResultException e) {
				return null;
				// e.printStackTrace();

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public List<UserDTO> getUserList(String id) {
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
		for (User user : users) {
			if (user.getId().equals(Long.parseLong(id))) {
				users.remove(user);
				break;
			}
		}
		if (!users.isEmpty()) {

			for (User user : users) {
				UserDTO map = dozerBeanMapper.map(user, UserDTO.class);
				map.setProfilePic(ImageUtils.instance().getByteStringFromBlob(user.get_profilePic()));
				dtos.add(map);
			}
			return dtos;
		} else {
			return Collections.emptyList();
		}

	}
}
