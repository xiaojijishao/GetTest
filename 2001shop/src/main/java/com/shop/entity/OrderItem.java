package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

/** orderItem
	ID	INT(10)
	OID	INT(11)
	PID	INT(11)
	NUM	INT(11)
*/
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer oid;
	private Integer pid;
	private Integer num;
	private Double price;

}

