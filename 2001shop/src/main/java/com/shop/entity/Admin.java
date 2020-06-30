package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

/** admin
	AID	INT(10)
	ACCOUNT	VARCHAR(32)
	PASSWORD	VARCHAR(32)
	STATUS	INT(1)
*/
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String account;
	@JSONField(serialize=false)//不序列化
	private String password;
	private Integer status;
	private String tel;
	private String email;
	private Integer type;
	private Integer gender;
	private String note;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date addtime;

}

