package com.esteban.camel.builder;

import org.apache.camel.builder.RouteBuilder;

public class JavaRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:inSimple?noop=true")
		.to("file:outputSimple");

	}

}
