package com.order.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.entity.User;

@FeignClient(name = "user-service", url = "http://localhost:8811/")
public interface UserClients {
	
	
	@GetMapping ("user/username/{id}")
    public List<Object[]> getUserNameById(@PathVariable("id") Long id);
}
