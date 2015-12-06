package com.utilaider.solarsense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utilaider.solarsense.dao.GenericDao;
import com.utilaider.solarsense.domain.ElectronicMeter;

@Service
@Transactional
public class ElectronicMeterServiceImpl extends
		GenericServiceImpl<Long, ElectronicMeter> implements
		ElectronicMeterService {
	public ElectronicMeterServiceImpl() {
	}

	@Autowired
	public ElectronicMeterServiceImpl(
			@Qualifier("electronicMeterDaoImpl") GenericDao<Long, ElectronicMeter> genericDao) {
		super(genericDao);
	}
}
