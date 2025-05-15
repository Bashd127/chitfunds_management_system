package com.example.forms;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
import java.util.Optional;


public interface agent extends CrudRepository<users,Integer> {
	users findByMail(String mail);
	Optional<users> findByMemberid(int memberid);  // ✅ Correct datatype (int)

}
