package com.famiao.search.vo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zengbin
 * @Date 2019/6/8 13:30
 */
@Setter
@Getter
@TableName(value = "user", keepGlobalPrefix = false)
public class User extends Model<User> {

    private static final long serialVersionUID = -4972030881763418909L;
    @TableId(value = "uuid", type = IdType.ASSIGN_ID)
    private String uuid;
    private String userName;
    private String realName;
    private String nickName;
    private String mobile;

    public User() {}

    public User(String uuid, String userName, String realName, String nickName, String mobile) {
        this.uuid = uuid;
        this.userName = userName;
        this.realName = realName;
        this.nickName = nickName;
        this.mobile = mobile;
    }
}
