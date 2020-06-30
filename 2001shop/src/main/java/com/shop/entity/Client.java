package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

/** client
	CID	INT(10)
	NICKNAME	VARCHAR(32)
	ACCOUNT	VARCHAR(32)
	PASSWORD	VARCHAR(32)
	TEL	CHAR(11)
	EMAIL	VARCHAR(255)
	ADDRESS	VARCHAR(255)
	STATUS	INT(1)
	SCORE	INT(11)
	ADDTIME	DATETIME(19)
	GRADE	INT(11)
	PIC	VARCHAR(255)
*/
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nickname;
	private String account;
	private String password;
	private String tel;
	private String email;
	private String address;
	private Integer status;
	private Integer score;
	private Date addtime;
	private Integer grade;
	private String pic;

}

