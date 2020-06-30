package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** shop
	SID	INT(10)
	NAME	VARCHAR(255)
	GRADE	INT(1)
	YMONEY	DOUBLE(10,2)
	LOGO	VARCHAR(255)
	PICS	VARCHAR(255)
	STATUS	INT(1)
	AID	INT(11)
	REASON	VARCHAR(255)
	INDUSTRY	VARCHAR(255)
	INTRO	VARCHAR(255)
	TYPE	INT(1)
	BOSS	VARCHAR(255)
	IDCARD	CHAR(18)
	TEL	CHAR(11)
	EMAIL	VARCHAR(255)
	APPLY_TIME	DATETIME(19)
	AUDIT_TIME	DATETIME(19)
*/
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer grade;
	private BigDecimal ymoney;
	private String logo;
	private String pics;
	private Integer status;
	private Integer aid;
	private String reason;
	private String industry;
	private String intro;
	private Integer type;
	private String boss;
	private String idcard;
	private String tel;
	private String email;
	private Date applyTime;
	private Date auditTime;

}

