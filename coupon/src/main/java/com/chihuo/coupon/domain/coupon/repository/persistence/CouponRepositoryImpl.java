package com.chihuo.coupon.domain.coupon.repository.persistence;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.chihuo.coupon.domain.coupon.entity.valueobject.Use;
import com.chihuo.coupon.domain.coupon.repository.facade.CouponRepository;
import com.chihuo.coupon.domain.coupon.repository.po.UserCouponPO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

    @Resource
    private MongoTemplate mongoTemplate;

	@Override
	public Long save(UserCouponPO userCouponPO) {
		this.mongoTemplate.save(userCouponPO);
		return userCouponPO.getUid();
	}

	@Override
	public boolean update(UserCouponPO userCouponPO) {
        Query query = new Query(Criteria.where("uid").is(userCouponPO.getUid()));
        Update update = new Update();
        if(null != userCouponPO.getUserId()) {
        	update.set("user_id", userCouponPO.getUserId());
        }
        if(null != userCouponPO.getCategoryId()) {
        	update.set("category_id", userCouponPO.getCategoryId());
        }
        if(null != userCouponPO.getTotalAmount()) {
        	update.set("total_amount", userCouponPO.getTotalAmount());
        }
        if(null != userCouponPO.getDeductAmount()) {
        	update.set("deduct_amount", userCouponPO.getDeductAmount());
        }
        if(null != userCouponPO.getIsUsed()) {
        	update.set("is_used", userCouponPO.getIsUsed());
        }
        if(null != userCouponPO.getUseTime()) {
        	update.set("use_time", userCouponPO.getUseTime());
        }
        UpdateResult result = this.mongoTemplate.updateFirst(query, update, UserCouponPO.class);
		return result.wasAcknowledged();
	}

	@Override
	public boolean remove(Long uid) {
        Query query = new Query(Criteria.where("uid").is(uid));
		DeleteResult result = this.mongoTemplate.remove(query, UserCouponPO.class);
		return result.wasAcknowledged();
	}

	@Override
	public UserCouponPO findUserCouponByUid(Long uid) {
        Query query = new Query(Criteria.where("uid").is(uid));
        UserCouponPO userCouponPO = this.mongoTemplate.findOne(query, UserCouponPO.class);
        return userCouponPO;
	}

	@Override
	public boolean use(Long uid) {
        Query query = new Query(Criteria.where("uid").is(uid));
        Update update = new Update();
        update.set("is_used", Use.BE_USED.getIsUse());
        update.set("use_time", new Date());
        UpdateResult result = this.mongoTemplate.updateFirst(query, update, UserCouponPO.class);
		return result.wasAcknowledged();
	}

	@Override
	public List<UserCouponPO> findCouponListByCategoryIds(List<Long> ids) {
        Query query = new Query(Criteria.where("category_id").in(ids));
        List<UserCouponPO> list = this.mongoTemplate.find(query, UserCouponPO.class);
		return list;
	}

}
