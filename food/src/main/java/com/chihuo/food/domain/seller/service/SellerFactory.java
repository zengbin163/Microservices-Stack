package com.chihuo.food.domain.seller.service;

import org.springframework.stereotype.Service;

import com.chihuo.food.domain.seller.entity.Seller;
import com.chihuo.food.domain.seller.entity.SellerFood;
import com.chihuo.food.domain.seller.repository.po.SellerFoodPO;
import com.chihuo.food.domain.seller.repository.po.SellerPO;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;

@Service
public class SellerFactory {
	
	public Seller createSeller(SellerPO sellerPO) {
		if(null == sellerPO) {
			return null;
		}
		BeanCopier<Seller> beanCopier = BeanCopier.create(sellerPO, new Seller(), CopyOptions.create());
		return beanCopier.copy();
	}
	
	public SellerPO createSellerPO(Seller seller) {
		if(null == seller) {
			return null;
		}
		BeanCopier<SellerPO> beanCopier = BeanCopier.create(seller, new SellerPO(), CopyOptions.create());
		return beanCopier.copy();
	}
	
	public SellerFood createSellerFood(SellerFoodPO sellerFoodPO) {
		if(null == sellerFoodPO) {
			return null;
		}
		BeanCopier<SellerFood> beanCopier = BeanCopier.create(sellerFoodPO, new SellerFood(), CopyOptions.create());
		return beanCopier.copy();
	}
	
}
