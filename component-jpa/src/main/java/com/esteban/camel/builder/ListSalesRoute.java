package com.esteban.camel.builder;

import org.apache.camel.builder.RouteBuilder;

public class ListSalesRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jpa:com.esteban.camel.entity.Sale?persistenceUnit=component-jpa&consumer.namedQuery=Sale.findAll")
		.to("log:out");
	}

}
