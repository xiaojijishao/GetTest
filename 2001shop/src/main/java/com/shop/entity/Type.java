package com.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * type
 * TID	INT(11)
 * NAME	VARCHAR(32)
 * PARENTID	INT(11)
 * SORT	INT(11)
 * NOTE	VARCHAR(255)
 * STATUS	INT(11)
 * GRADE	INT(11)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	@JSONField(defaultValue="0")//为null不给序列化，加默认值为0
	private Integer pId;
	@JSONField(defaultValue="0")//为null不给序列化，加默认值为0
	private Integer sort;
	private String note;
	@JSONField(defaultValue="1")//为null不给序列化，加默认值为0
	private Integer status;
	@JSONField(defaultValue="1")//为null不给序列化，加默认值为0
	private Integer grade;

}

