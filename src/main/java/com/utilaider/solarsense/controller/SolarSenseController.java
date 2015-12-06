package com.utilaider.solarsense.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.utilaider.solarsense.domain.ElectronicMeter;
import com.utilaider.solarsense.domain.ElectronicMeterReadingData;
import com.utilaider.solarsense.domain.ElectronicMeterType;
import com.utilaider.solarsense.domain.User;
import com.utilaider.solarsense.service.ElectronicMeterReadingDataService;
import com.utilaider.solarsense.service.ElectronicMeterService;
import com.utilaider.solarsense.service.UserService;

@Controller
public class SolarSenseController {
	private static final Logger loger = LogManager
			.getLogger(SolarSenseController.class);

	@Autowired(required = true)
	UserService userservice;
	@Autowired(required = true)
	ElectronicMeterReadingDataService electronicMeterReadingDataService;
	@Autowired(required = true)
	ElectronicMeterService electronicMeterService;

	@RequestMapping(value = "/login")
	public String home(@RequestParam(required = false) String error,
			@ModelAttribute User user, ModelMap map) {
		try {
			if (error != null) {
				map.addAttribute("error", "Incorrect Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("Login Error", e.fillInStackTrace());
		}
		return "login";
	}

	@RequestMapping(value = "/register")
	public String register() {
		try {
			User user = new User("9978459511",
					new BCryptPasswordEncoder().encode("dhiren56"));
			userservice.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("Registration Error", e.fillInStackTrace());
		}
		return "redirect:login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "home")
	public ModelAndView home(ModelMap map) {
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			if (auth instanceof AnonymousAuthenticationToken) {
				return new ModelAndView("redirect:/logout");
			}
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			map.addAttribute("user", userservice
					.getUserDetailsByUsername(userDetails.getUsername()));
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("Home Error", e.fillInStackTrace());
		}
		return new ModelAndView("home", map);
	}

	@RequestMapping(value = "loadinstantdata/{meterid}")
	@ResponseBody
	public ElectronicMeterReadingData getInstantDataById(
			@PathVariable("meterid") Long meterid) {
		try {
			ElectronicMeterReadingData data = electronicMeterReadingDataService
					.getInstantData(meterid);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("Instant Data", e.fillInStackTrace());
			return null;
		}
	}

	@RequestMapping(value = "loaddata/{meterid}")
	@ResponseBody
	public Map<String, List<ElectronicMeterReadingData>> getAllElectronicMeterReadingDatas(
			@PathVariable("meterid") Long meterid,
			@RequestParam("dataview") String dataview) {
		try {
			System.out.println(dataview + "Test");
			if (dataview.equals("daily")) {
				return electronicMeterReadingDataService.getDailyData(meterid);
			}
			if (dataview.equals("weekly")) {
				return electronicMeterReadingDataService.getWeeklyData(meterid);
			} else if (dataview.equals("monthly")) {
				return electronicMeterReadingDataService
						.getMonthlyData(meterid);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("Data Fetching", e.fillInStackTrace());
			return null;
		}
	}

	@RequestMapping(value = "addmeter")
	public String addMeter() {
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			if (auth instanceof AnonymousAuthenticationToken) {
				return "redirect:logout";
			}
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			ElectronicMeter electronicMeter = new ElectronicMeter();
			electronicMeter
					.setElectronicMeterType(ElectronicMeterType.SINGLE_PHASE);
			electronicMeter.setUser(userservice
					.getUserDetailsByUsername(userDetails.getUsername()));
			electronicMeter.setMeterSerialNo("1002655");
			electronicMeter.setLatitude(23.013413);
			electronicMeter.setLongitude(72.562410);
			electronicMeterService.save(electronicMeter);
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("add Meter", e.fillInStackTrace());
		}
		return "redirect:home";
	}

	@RequestMapping(value = "addreadingdata")
	public String addReading() {
		try {
			ElectronicMeterReadingData data = new ElectronicMeterReadingData();
			data.setCurrent(0.46);
			data.setVoltage(249.36);
			data.setPower(112.2);
			data.setPowerFactor(0.9784);
			ElectronicMeter electronicMeter = electronicMeterService
					.getById((long) 5);
			data.setInstantSurveyDatameter(electronicMeter);
			data.setCuurrentSnapTime(Calendar.getInstance().getTimeInMillis());
			electronicMeterReadingDataService.save(data);
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("addReading Data", e.fillInStackTrace());
		}
		return "redirect:home";
	}
}