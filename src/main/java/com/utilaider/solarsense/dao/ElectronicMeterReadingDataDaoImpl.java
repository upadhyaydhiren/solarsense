package com.utilaider.solarsense.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.utilaider.solarsense.domain.ElectronicMeterReadingData;
import com.utilaider.solarsense.domain.ElectronicMeterType;

@Repository
@Transactional
public class ElectronicMeterReadingDataDaoImpl extends
		GenericDaoImpl<Long, ElectronicMeterReadingData> implements
		ElectronicMeterReadingDataDao {

	@Override
	public ElectronicMeterReadingData getInstantData(Long id) throws Exception {
		Criteria criteria = getSession().createCriteria(
				ElectronicMeterReadingData.class);
		criteria.createAlias("instantSurveyDatameter", "instantmeter");
		criteria.add(Restrictions.eq("instantmeter.id", id));
		criteria.add(Restrictions.eq("instantmeter.electronicMeterType",
				ElectronicMeterType.SINGLE_PHASE));
		Calendar currentTime = Calendar.getInstance();
		currentTime.add(Calendar.MINUTE, -15);
		criteria.add(Restrictions.between("cuurrentSnapTime", currentTime
				.getTimeInMillis(), Calendar.getInstance().getTimeInMillis()));
		return (ElectronicMeterReadingData) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<ElectronicMeterReadingData>> getDailyData(Long id)
			throws Exception {
		Criteria criteria = getSession().createCriteria(
				ElectronicMeterReadingData.class);
		criteria.createAlias("instantSurveyDatameter", "instantmeter");
		criteria.add(Restrictions.eq("instantmeter.id", id));
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(new Date());
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Calendar nextDate = new GregorianCalendar();
		nextDate.setTime(new Date());
		nextDate.add(Calendar.DATE, 1);
		nextDate.set(Calendar.HOUR_OF_DAY, 0);
		nextDate.set(Calendar.MINUTE, 0);
		nextDate.set(Calendar.SECOND, 0);
		criteria.add(Restrictions.between("cuurrentSnapTime",
				currentDate.getTimeInMillis(), nextDate.getTimeInMillis()));
		List<ElectronicMeterReadingData> meterReadingDatas = criteria.list();
		System.out.println(meterReadingDatas.size());
		Map<String, List<ElectronicMeterReadingData>> finalMeterReading = new HashMap<String, List<ElectronicMeterReadingData>>();
		currentDate.add(Calendar.DATE, -1);
		while (currentDate.getTime().before(nextDate.getTime())) {
			List<ElectronicMeterReadingData> electronicMeterReadingDatas = new ArrayList<ElectronicMeterReadingData>();
			for (ElectronicMeterReadingData electronicMeterReadingData : new ArrayList<ElectronicMeterReadingData>(
					meterReadingDatas)) {
				if (electronicMeterReadingData.getCuurrentSnapTime() <= currentDate
						.getTimeInMillis()) {
					electronicMeterReadingDatas.add(electronicMeterReadingData);
					meterReadingDatas.remove(electronicMeterReadingData);
				} else {
					break;
				}
			}
			finalMeterReading.put(
					String.valueOf(currentDate.get(Calendar.HOUR_OF_DAY)),
					electronicMeterReadingDatas);
			currentDate.add(Calendar.MINUTE, 60);
		}
		return finalMeterReading;
	}

	@Override
	public Map<String, List<ElectronicMeterReadingData>> getWeeklyData(Long id)
			throws Exception {
		Criteria criteria = getSession().createCriteria(
				ElectronicMeterReadingData.class);
		criteria.createAlias("instantSurveyDatameter", "instantmeter");
		criteria.add(Restrictions.eq("instantmeter.id", id));
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(new Date());
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Calendar endDate = new GregorianCalendar();
		endDate.setTime(currentDate.getTime());
		endDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		criteria.add(Restrictions.between("cuurrentSnapTime",
				currentDate.getTimeInMillis(), endDate.getTimeInMillis()));
		criteria.addOrder(Order.asc("cuurrentSnapTime"));
		@SuppressWarnings("unchecked")
		List<ElectronicMeterReadingData> meterReadingDatas = criteria.list();
		Map<String, List<ElectronicMeterReadingData>> finalMeterReading = new HashMap<String, List<ElectronicMeterReadingData>>();
		currentDate.add(Calendar.DATE, -7);
		while (currentDate.getTime().before(endDate.getTime())) {
			List<ElectronicMeterReadingData> electronicMeterReadingDatas = new ArrayList<ElectronicMeterReadingData>();
			for (ElectronicMeterReadingData electronicMeterReadingData : new ArrayList<ElectronicMeterReadingData>(
					meterReadingDatas)) {
				if (electronicMeterReadingData.getCuurrentSnapTime() <= currentDate
						.getTimeInMillis()) {
					electronicMeterReadingDatas.add(electronicMeterReadingData);
					meterReadingDatas.remove(electronicMeterReadingData);
				} else {
					break;
				}
			}
			finalMeterReading.put(currentDate.getDisplayName(
					Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US),
					electronicMeterReadingDatas);
			currentDate.add(Calendar.DATE, 1);
		}
		return finalMeterReading;
	}

	@Override
	public Map<String, List<ElectronicMeterReadingData>> getMonthlyData(Long id)
			throws Exception {
		Criteria criteria = getSession().createCriteria(
				ElectronicMeterReadingData.class);
		criteria.createAlias("instantSurveyDatameter", "instantmeter");
		criteria.add(Restrictions.eq("instantmeter.id", id));
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(new Date());
		currentDate.set(Calendar.MONTH, Calendar.JANUARY);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Calendar endDate = new GregorianCalendar();
		endDate.setTime(currentDate.getTime());
		endDate.set(Calendar.MONTH, Calendar.DECEMBER);
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		criteria.add(Restrictions.between("cuurrentSnapTime",
				currentDate.getTimeInMillis(), endDate.getTimeInMillis()));
		criteria.addOrder(Order.asc("cuurrentSnapTime"));
		@SuppressWarnings("unchecked")
		List<ElectronicMeterReadingData> meterReadingDatas = criteria.list();
		Map<String, List<ElectronicMeterReadingData>> finalMeterReading = new HashMap<String, List<ElectronicMeterReadingData>>();
		currentDate.set(Calendar.MONTH, Calendar.JANUARY);
		while (currentDate.getTime().before(endDate.getTime())) {
			List<ElectronicMeterReadingData> electronicMeterReadingDatas = new ArrayList<ElectronicMeterReadingData>();
			for (ElectronicMeterReadingData electronicMeterReadingData : new ArrayList<ElectronicMeterReadingData>(
					meterReadingDatas)) {
				if (electronicMeterReadingData.getCuurrentSnapTime() <= currentDate
						.getTimeInMillis()) {
					electronicMeterReadingDatas.add(electronicMeterReadingData);
					meterReadingDatas.remove(electronicMeterReadingData);
				} else {
					break;
				}
			}
			finalMeterReading.put(String.format(Locale.US, "%tB", currentDate),
					electronicMeterReadingDatas);
			currentDate.add(Calendar.MONTH, 1);
		}
		return finalMeterReading;
	}
}
