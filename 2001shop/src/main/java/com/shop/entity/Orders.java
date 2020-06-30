package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** orders
	OID	INT(10)
	CID	INT(11)
	NAME	VARCHAR(255)
	TEL	CHAR(11)
	ADDRESS	VARCHAR(255)
	CODE	CHAR(6)
	SUM	DOUBLE(10,2)
	STATUS	INT(1)
	PAYWAY	VARCHAR(255)
	NOTE	VARCHAR(255)
	ADDTIME	DATETIME(19)
	PAYTIME	DATETIME(19)
	SENDTIME	DATETIME(19)
	GETTIME	DATETIME(19)
	COMMENTTIME	DATETIME(19)
*/
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer cid;
	private String name;
	private String tel;
	private String address;
	private String code;
	private Double sum;
	private Integer status;
	private String payway;
	private String note;
	private Date addtime;
	private Date paytime;
	private Date sendtime;
	private Date gettime;
	private Date commenttime;

}

