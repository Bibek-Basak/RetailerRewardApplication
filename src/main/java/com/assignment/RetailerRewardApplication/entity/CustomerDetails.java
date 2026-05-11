package com.assignment.RetailerRewardApplication.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
	
	@Id
	private Long customerId;
	
	private String customerName;
	
	private Double amount;
	
	private LocalDateTime date;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public CustomerDetails(Long customerId, String customerName, Double amount, LocalDateTime date) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.amount = amount;
		this.date = date;
	}

}
