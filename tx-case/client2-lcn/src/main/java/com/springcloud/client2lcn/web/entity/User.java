package com.springcloud.client2lcn.web.entity;

import com.codingapi.txlcn.tc.annotation.TableId;
import com.codingapi.txlcn.tc.annotation.TableName;

import lombok.Data;

/**
 * @author liq
 * @Title: User
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:11
 */
@Data
@TableName(value = "t_user")
public class User {
	@TableId(value = "id")
	private Integer id;
	private String name;
	private String username;
	private String password;
	private String groupId;
}
