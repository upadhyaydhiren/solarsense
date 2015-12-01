package com.utilaider.solarsense.utilty;

import javax.servlet.Filter;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * @author Dhiren
 * @Date 01-12-2015
 * @Description -Initialize Spring Controller
 */

public class SpringWebIntilalizer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DelegatingFilterProxy(
				"springSecurityFilterChain") };
	}
}
