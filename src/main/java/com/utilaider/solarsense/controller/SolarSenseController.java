package com.utilaider.solarsense.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SolarSenseController {

	private static final Logger loger = LogManager
			.getLogger(SolarSenseController.class);

	@RequestMapping(value = "/")
	public String home() {
		loger.info("home Called");
		return "index";
	}
}
