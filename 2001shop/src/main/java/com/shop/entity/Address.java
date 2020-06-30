package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

/** address
	ID	INT(10)
	CID	INT(11)
	NAME	VARCHAR(255)
	TEL	VARCHAR(255)
	CODE	VARCHAR(255)
	PROVINCE	VARCHAR(255)
	CITY	VARCHAR(255)
	AREA	VARCHAR(255)
	STREET	VARCHAR(255)
	DETAIL	VARCHAR(255)
	IS_DEFAULT	INT(1)
*/
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer cid;
	private String name;
	private String tel;
	private String code;
	private String province;
	private String city;
	private String area;
	private String street;
	private String detail;
	private Integer isDefault;

}

