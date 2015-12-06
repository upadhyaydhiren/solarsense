package com.utilaider.solarsense.service;

import com.utilaider.solarsense.domain.User;

public interface UserService extends GenericService<Long, User> {
	public User getUserDetailsByUsername(String username) throws Exception;
}
