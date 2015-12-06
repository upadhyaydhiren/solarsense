package com.utilaider.solarsense.dao;

import java.util.List;
import java.util.Map;

import com.utilaider.solarsense.domain.ElectronicMeterReadingData;

public interface ElectronicMeterReadingDataDao extends
		GenericDao<Long, ElectronicMeterReadingData> {
	public ElectronicMeterReadingData getInstantData(Long id) throws Exception;

	public Map<String, List<ElectronicMeterReadingData>> getDailyData(Long id)
			throws Exception;

	public Map<String, List<ElectronicMeterReadingData>> getWeeklyData(Long id)
			throws Exception;

	public Map<String, List<ElectronicMeterReadingData>> getMonthlyData(Long id)
			throws Exception;
}
