package com.esteban.camel.context;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.esteban.camel.builder.FilterBuilder;

public class FilterContext {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new FilterBuilder());
		context.setTracing(true);
		context.start();
		Thread.sleep(5000);
		context.stop();
	}

}
