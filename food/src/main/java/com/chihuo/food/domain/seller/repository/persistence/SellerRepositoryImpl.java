package com.chihuo.food.domain.seller.repository.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.seller.repository.facade.SellerRepository;
import com.chihuo.food.domain.seller.repository.mapper.SellerDao;
import com.chihuo.food.domain.seller.repository.po.SellerPO;

@Repository
public class SellerRepositoryImpl implements SellerRepository {

	@Autowired
	private SellerDao sellerDao;
	
	@Override
	public void save(SellerPO sellerPO) {
		this.sellerDao.save(sellerPO);
	}

	@Override
	public List<SellerPO> findSellerList() {
		return this.sellerDao.findSellerList();
	}

	@Override
	public Optional<SellerPO> findSellerById(Long uid) {
		return this.sellerDao.findSellerById(uid);
	}

}
