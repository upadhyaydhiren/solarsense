package com.utilaider.solarsense.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.utilaider.solarsense.domain.ElectronicMeter;

@Repository
@Transactional
public class ElectronicMeterDaoImpl extends GenericDaoImpl<Long, ElectronicMeter>
		implements ElectronicMeterDao {
}
