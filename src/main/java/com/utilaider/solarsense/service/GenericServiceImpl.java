/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilaider.solarsense.service;

import com.utilaider.solarsense.dao.GenericDao;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dhiren
 * @param <PK>
 * @param <T>
 */
@Service
@Transactional
public abstract class GenericServiceImpl<PK extends Serializable, T> implements
		GenericService<PK, T> {

	private GenericDao<PK, T> genericDao;

	public GenericServiceImpl(GenericDao<PK, T> genericDao) {
		this.genericDao = genericDao;
	}

	public GenericServiceImpl() {
	}

	@Override
	public Serializable save(T entity) throws Exception {
		return genericDao.save(entity);
	}

	@Override
	public void update(T entity) throws Exception {
		genericDao.update(entity);
	}

	@Override
	public void delete(T entity) throws Exception {
		genericDao.delete(entity);
	}

	@Override
	public T getById(PK key) throws Exception {
		return genericDao.getById(key);
	}

	@Override
	public List<T> getAll() throws Exception {
		return genericDao.getAll();
	}

}
