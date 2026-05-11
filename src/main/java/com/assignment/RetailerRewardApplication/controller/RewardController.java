package com.assignment.RetailerRewardApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.RetailerRewardApplication.entity.RewardResponse;
import com.assignment.RetailerRewardApplication.service.RewardService;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/points")
    public List<RewardResponse> getRewards() {
        return rewardService.getRewardPoints();
    }

}
