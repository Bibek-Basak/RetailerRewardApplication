package com.assignment.RetailerRewardApplication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.RetailerRewardApplication.entity.RewardResponse;
import com.assignment.RetailerRewardApplication.service.RewardServiceImpl;

class RewardServiceImplTest {

    private RewardServiceImpl rewardService;

    @BeforeEach
    void setUp() {
        rewardService = new RewardServiceImpl();
    }

    @Test
    void testGetRewardPoints() {
        List<RewardResponse> rewards = rewardService.getRewardPoints();

        assertNotNull(rewards);
        assertEquals(2, rewards.size()); 

        RewardResponse bibekReward = rewards.stream()
                .filter(r -> r.getCustomerId().equals(1L))
                .findFirst()
                .orElse(null);
        assertNotNull(bibekReward);
        assertEquals("Bibek", bibekReward.getCustomerName());
        assertEquals(325, bibekReward.getTotalRewards());

        Map<String, Integer> bibekMonthly = bibekReward.getMonthlyRewards();
        assertEquals(90, bibekMonthly.get("JANUARY"));
        assertEquals(25, bibekMonthly.get("FEBRUARY"));
        assertEquals(210, bibekMonthly.get("MARCH"));

        RewardResponse sudhirReward = rewards.stream()
                .filter(r -> r.getCustomerId().equals(2L))
                .findFirst()
                .orElse(null);
        assertNotNull(sudhirReward);
        assertEquals("Sudhir", sudhirReward.getCustomerName());
        assertEquals(335, sudhirReward.getTotalRewards());

        Map<String, Integer> sudhirMonthly = sudhirReward.getMonthlyRewards();
        assertEquals(45, sudhirMonthly.get("JANUARY"));
        assertEquals(290, sudhirMonthly.get("FEBRUARY"));
        assertEquals(0, sudhirMonthly.get("MARCH"));
    }

}
