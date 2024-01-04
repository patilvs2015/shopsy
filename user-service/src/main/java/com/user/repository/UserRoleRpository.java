package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.UserRole;

@Repository
public interface UserRoleRpository extends JpaRepository<UserRole, Long> {

	
	 UserRole findUserRoleByRoleName(String roleName);
	
}
