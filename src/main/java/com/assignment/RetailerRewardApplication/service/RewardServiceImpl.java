package com.assignment.RetailerRewardApplication.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.assignment.RetailerRewardApplication.entity.CustomerDetails;
import com.assignment.RetailerRewardApplication.entity.RewardResponse;

@Service
public class RewardServiceImpl implements RewardService {

	@Override
	public List<RewardResponse> getRewardPoints() {
		try {
			List<CustomerDetails> transactions = List.of(

					new CustomerDetails(1L, "Bibek", 120.0, LocalDateTime.of(2026, 1, 15, 10, 30)),

					new CustomerDetails(1L, "Bibek", 75.0, LocalDateTime.of(2026, 2, 10, 11, 15)),

					new CustomerDetails(1L, "Bibek", 180.0, LocalDateTime.of(2026, 3, 5, 9, 20)),

					new CustomerDetails(2L, "Sudhir", 95.0, LocalDateTime.of(2026, 1, 20, 2, 45)),

					new CustomerDetails(2L, "Sudhir", 220.0, LocalDateTime.of(2026, 2, 18, 5, 10)),

					new CustomerDetails(2L, "Sudhir", 45.0, LocalDateTime.of(2026, 3, 25, 7, 30)));

			Map<Long, List<CustomerDetails>> customerMap = transactions.stream()
					.collect(Collectors.groupingBy(CustomerDetails::getCustomerId));

			List<RewardResponse> rewardResponses = new ArrayList<>();

			for (Map.Entry<Long, List<CustomerDetails>> entry : customerMap.entrySet()) {

				Long customerId = entry.getKey();

				List<CustomerDetails> customerTransactions = entry.getValue();

				if (customerTransactions == null || customerTransactions.isEmpty()) {
					throw new IllegalArgumentException("No transactions found for customer ID: " + customerId);
				}

				String customerName = customerTransactions.get(0).getCustomerName();

				Map<String, Integer> monthlyRewards = customerTransactions.stream().collect(
						Collectors.groupingBy(transaction -> {
							if (transaction.getDate() == null) {
								throw new IllegalArgumentException("Transaction date is null for customer ID: " + customerId);
							}
							return transaction.getDate().getMonth().toString();
						}, Collectors.summingInt(transaction -> {
							if (transaction.getAmount() == null) {
								throw new IllegalArgumentException("Transaction amount is null for customer ID: " + customerId);
							}
							return calculatePointsForAmount(transaction.getAmount().intValue());
						})));

				int totalRewards = monthlyRewards.values().stream().mapToInt(Integer::intValue).sum();

				RewardResponse response = new RewardResponse(customerId, customerName, monthlyRewards, totalRewards);

				rewardResponses.add(response);
			}

			return rewardResponses;
		} catch (Exception e) {
			throw new RuntimeException("Error calculating reward points: " + e.getMessage(), e);
		}
	}

	private int calculatePointsForAmount(int amount) {
		try {
			if (amount <= 50) {
				return 0;
			}

			int points = 0;

			if (amount > 100) {
				points += (amount - 100) * 2;
				points += 50;

			} else {
				points += (amount - 50);
			}

			return points;
		} catch (Exception e) {
			throw new RuntimeException("Error calculating points for amount: " + amount, e);
		}
	}
}
