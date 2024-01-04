package com.recommendation.product.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient (name = "user-service", url = "http://localhost:8811/")
public interface UserClient {

    @GetMapping ("user/username/{id}")
    public List<Object[]> getUserNameById(@PathVariable("id") Long id);

}
