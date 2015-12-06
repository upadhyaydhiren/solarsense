package com.utilaider.solarsense.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utilaider.solarsense.dao.ElectronicMeterReadingDataDao;
import com.utilaider.solarsense.dao.GenericDao;
import com.utilaider.solarsense.domain.ElectronicMeterReadingData;

@Service
@Transactional
public class ElectronicMeterReadingDataServiceImpl extends
		GenericServiceImpl<Long, ElectronicMeterReadingData> implements
		ElectronicMeterReadingDataService {
	private ElectronicMeterReadingDataDao electronicMeterReadingDataDao;

	public ElectronicMeterReadingDataServiceImpl() {
	}

	@Autowired
	public ElectronicMeterReadingDataServiceImpl(
			@Qualifier("electronicMeterReadingDataDaoImpl") GenericDao<Long, ElectronicMeterReadingData> genericDao) {
		super(genericDao);
		this.electronicMeterReadingDataDao = (ElectronicMeterReadingDataDao) genericDao;
	}

	@Override
	public ElectronicMeterReadingData getInstantData(Long id) throws Exception {
		return electronicMeterReadingDataDao.getInstantData(id);
	}

	@Override
	public Map<String, List<ElectronicMeterReadingData>> getDailyData(Long id)
			throws Exception {
		return electronicMeterReadingDataDao.getDailyData(id);
	}

	@Override
	public Map<String, List<ElectronicMeterReadingData>> getWeeklyData(Long id)
			throws Exception {
		return electronicMeterReadingDataDao.getWeeklyData(id);
	}

	@Override
	public Map<String, List<ElectronicMeterReadingData>> getMonthlyData(Long id)
			throws Exception {
		return electronicMeterReadingDataDao.getMonthlyData(id);
	}

}
