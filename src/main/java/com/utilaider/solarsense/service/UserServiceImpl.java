package com.utilaider.solarsense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utilaider.solarsense.dao.GenericDao;
import com.utilaider.solarsense.dao.UserDao;
import com.utilaider.solarsense.domain.User;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<Long, User> implements
		UserService {
	private UserDao userDao;

	public UserServiceImpl() {
	}

	@Autowired
	public UserServiceImpl(
			@Qualifier("userDaoImpl") GenericDao<Long, User> genericDao) {
		super(genericDao);
		this.userDao = (UserDao) genericDao;
	}

	@Override
	public User getUserDetailsByUsername(String username) throws Exception {
		return userDao.getUserDetailsByUsername(username);
	}
}
