package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

	@Query("select e.userName from User e where e.userId = ?1")
	List<Object[]> findUsernameByUserId(Long userId);

	
	
	/*
	 * //@Query("SELECT e.userName, e.userPassword from User e where id =?1")
	 * List<Object[]> getUserNameAndUserPasswordOnly();
	 */
}
