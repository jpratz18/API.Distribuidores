package com.qacg.dealers.api.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qacg.dealers.api.entity.User;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
}
