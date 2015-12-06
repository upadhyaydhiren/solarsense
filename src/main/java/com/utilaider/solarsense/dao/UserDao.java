package com.utilaider.solarsense.dao;

import com.utilaider.solarsense.domain.User;

public interface UserDao extends GenericDao<Long, User> {
	public User getUserDetailsByUsername(String username) throws Exception;
}
