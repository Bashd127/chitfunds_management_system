package com.example.forms;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface eagent extends CrudRepository<elite, Integer> {
		Optional<elite> findByMemberid(String memberid);
		void deleteByMemberid(String memberid);
}
