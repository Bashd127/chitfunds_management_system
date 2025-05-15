package com.example.forms;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface adminagent extends CrudRepository<adminlogin, Integer> {
	Optional<adminlogin>  findByAdminid(String adminid);

}
