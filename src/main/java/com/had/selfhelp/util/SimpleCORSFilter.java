package com.had.selfhelp.util;

import com.had.selfhelp.dao.RoleRepository;
import com.had.selfhelp.entity.Customer;
import com.had.selfhelp.entity.Role;
import com.had.selfhelp.entity.User;
import com.had.selfhelp.service.CustomerServices;
import com.had.selfhelp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCORSFilter implements Filter {
	@Autowired
	UserService userService;
	@Autowired
	CustomerServices customerServices;
	@Autowired
	RoleRepository roleRepository;

	private final Logger log = LoggerFactory.getLogger(SimpleCORSFilter.class);

	public SimpleCORSFilter() {
		log.info("SimpleCORSFilter init");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest  req = (HttpServletRequest) request;
         System.out.println("corsFilter");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, Connection, User-Agent, authorization, sw-useragent, sw-version");

		// Just REPLY OK if request method is OPTIONS for CORS (pre-flight)
		if ( req.getMethod().equals("OPTIONS") ) {
			res.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		chain.doFilter(request, response);
	}

		public void init(FilterConfig filterConfig) {
			User u = new User();
			u.setUserId(1);
			u.setPassword("$2a$12$vqGx879jLRhp7EmrrOOXz.V23YPDuOM1G9wfcjCdrwVVUz9NhbWGe");
			u.setUsername("owner");
			userService.save(u);
			Customer c = new Customer();
			c.setPassword("owner");
			c.setUsername("owner");
			c.setId(1);
			customerServices.save(c);
			Role r= new Role();
			r.setId(1);
			r.setName("User");
			roleRepository.save(r);
		}

		public void destroy() {
		}

}