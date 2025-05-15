package com.example.forms;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface pagent extends CrudRepository<premium, Integer> {
		Optional<premium> findByMemberid(String memberid);
		void deleteByMemberid(String memberid);
}
