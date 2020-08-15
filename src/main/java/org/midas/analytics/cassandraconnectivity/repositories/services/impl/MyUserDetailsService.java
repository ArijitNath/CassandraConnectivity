package org.midas.analytics.cassandraconnectivity.repositories.services.impl;

import java.util.ArrayList;

import org.midas.analytics.cassandraconnectivity.repositories.services.CassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CassandraService cassandraService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return new User(userName, cassandraService.getUserByID(userName).getLoginPassword(), new ArrayList<>());
	}

}