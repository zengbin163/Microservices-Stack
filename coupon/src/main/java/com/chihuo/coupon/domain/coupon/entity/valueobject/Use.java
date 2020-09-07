package com.chihuo.coupon.domain.coupon.entity.valueobject;

public enum Use {

	NOT_USE(0), 
	BE_USED(1),;

	private Integer isUse;

	Use(int isUse) {
		this.isUse = isUse;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
}
