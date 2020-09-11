package com.springcloud.feignclient.web.entity;

import com.codingapi.txlcn.tc.annotation.TableId;
import com.codingapi.txlcn.tc.annotation.TableName;

/**
 * @author liq
 * @Title: User
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:11
 */
@TableName(value = "t_user")
public class User {
	@TableId(value = "id")
	private Integer id;
	private String name;
	private String username;
	private String password;
	private String groupId;

	public User() {
	}

	public User(Integer id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
