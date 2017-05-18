package com.esteban.camel.main;

import java.rmi.registry.Registry;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.esteban.camel.builder.ListSalesRoute;

public class ListSales {
	
	private ListSales(){}

	public static void main(String[] args) throws Exception{
		SimpleRegistry registry = new SimpleRegistry();
		CamelContext context = new DefaultCamelContext();
		context.setTracing(true);
		context.addRoutes(new ListSalesRoute());
		
		context.start();
		Thread.sleep(5000);
		context.stop();
	}

}
