package com.utilaider.solarsense.service;

import java.util.List;
import java.util.Map;

import com.utilaider.solarsense.domain.ElectronicMeterReadingData;

public interface ElectronicMeterReadingDataService extends
		GenericService<Long, ElectronicMeterReadingData> {
	public ElectronicMeterReadingData getInstantData(Long id) throws Exception;

	public Map<String, List<ElectronicMeterReadingData>> getDailyData(Long id)
			throws Exception;

	public Map<String, List<ElectronicMeterReadingData>> getWeeklyData(Long id)
			throws Exception;

	public Map<String, List<ElectronicMeterReadingData>> getMonthlyData(Long id)
			throws Exception;
}
