package com.esteban.camel.builder;

import org.apache.camel.builder.RouteBuilder;

public class FilterBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:in?noop=true")
		.filter(header("CamelFileName").endsWith(".xml"))
		.to("file:outFilter");
	}
	

}
