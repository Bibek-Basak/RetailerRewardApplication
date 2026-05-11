package com.assignment.RetailerRewardApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.RetailerRewardApplication.entity.CustomerDetails;

public interface RewardRepository extends JpaRepository<CustomerDetails, Long>{

}
