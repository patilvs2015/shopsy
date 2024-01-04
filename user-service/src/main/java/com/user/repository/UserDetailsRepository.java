package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
