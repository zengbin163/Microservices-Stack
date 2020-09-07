package com.chihuo.food.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.food.domain.seller.service.SellerDomainService;

@Service
public class SellerApplicationService {
	
	@Autowired
	private SellerDomainService sellerDomainService;

	public void createSellerData() {
		this.sellerDomainService.createSellerData();
	}
	
}
