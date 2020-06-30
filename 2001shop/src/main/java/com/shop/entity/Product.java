package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** product
	PID	INT(10)
	NAME	VARCHAR(255)
	INTRO	VARCHAR(255)
	JPRICE	DECIMAL(10,2)
	YPRICE	DECIMAL(10,2)
	XPRICE	DECIMAL(10,2)
	STATUS	INT(1)
	ADDTIME	DATETIME(19)
	TID	INT(11)
	BRAND	VARCHAR(255)
	IMAGES	VARCHAR(255)
	DETAIL	VARCHAR(21845)
	IS_HOT	INT(1)
	IS_NEW	INT(1)
	IS_HOME	INT(1)
	SELL_NUM	INT(11)
	WATCH_NUM	INT(11)
	SID	INT(11)
	STORE	INT(11)
*/
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String intro;
	private Double jprice;
	private Double yprice;
	private Double xprice;
	private Integer status;
	private Date addtime;
	private Integer tid;
	private String brand;
	private String images;
	private String detail;
	private Integer isHot;
	private Integer isNew;
	private Integer isHome;
	private Integer sellNum;
	private Integer watchNum;
	private Integer sid;
	private Integer store;

}

