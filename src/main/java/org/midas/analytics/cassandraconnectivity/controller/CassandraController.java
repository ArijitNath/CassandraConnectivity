package org.midas.analytics.cassandraconnectivity.controller;

import java.util.List;

import org.midas.analytics.cassandraconnectivity.model.LoginDetails;
import org.midas.analytics.cassandraconnectivity.repositories.services.CassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cassandra")
public class CassandraController {
	
	@Autowired
	private CassandraService cassandraService;
	
	@GetMapping(value = "/user", produces = "application/json")
	public List<LoginDetails> getAlluser() {
		return cassandraService.getAllUser();
	}
	
	@GetMapping(value = "/user/{id}", produces = "application/json")
	public LoginDetails getUserByID( @PathVariable("id") String id ) {
		return cassandraService.getUserByID(id);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public LoginDetails addUser(@RequestBody LoginDetails loginDetails) {
		return cassandraService.saveOrUpdate(loginDetails);
	}
	
	@DeleteMapping(value = "/delete")
	public boolean deleteUser(@RequestBody LoginDetails loginDetails) {
		return cassandraService.delete(loginDetails);
	}
}
