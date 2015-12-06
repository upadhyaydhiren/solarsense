package com.utilaider.solarsense.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.utilaider.solarsense.domain.User;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

	@Override
	public User getUserDetailsByUsername(String username) throws Exception {
		Criteria criteria = getSession().createCriteria(User.class);
		if (username.contains("@")) {
			criteria.add(Restrictions.eq("userName", username));
		} else {
			criteria.add(Restrictions.eq("userName", username));
		}
		return (User) criteria.uniqueResult();
	}

}
