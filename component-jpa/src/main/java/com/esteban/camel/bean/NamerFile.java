package com.esteban.camel.bean;

import org.apache.camel.Body;

import com.esteban.camel.entity.Sale;

public class NamerFile {
	public String nameFileXml(@Body Sale body) {
		return "sale-" + body.getNo_bill() + "-" + body.getId() + ".xml";
	}
}
