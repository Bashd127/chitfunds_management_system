package com.example.forms;

import java.util.List;

import java.util.Optional;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

 @Transactional(readOnly = true)

public interface bagent extends CrudRepository<basic, Integer> {
	Optional<basic>findByMemberid(String memberid);
	//Optional<basic>deleteByMemberid(String memberid);
	void deleteByMemberid(String memberid);

}
