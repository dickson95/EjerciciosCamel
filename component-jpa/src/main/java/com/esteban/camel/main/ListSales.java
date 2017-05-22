package com.esteban.camel.main;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import com.esteban.camel.builder.ListSalesRoute;

public class ListSales {
	
	private ListSales(){}

	public static void main(String[] args) throws Exception{
		
		CamelContext context = new DefaultCamelContext();
		
		context.setTracing(true);
		context.addRoutes(new ListSalesRoute());
		
		context.start();
		Thread.sleep(5000);
		context.stop();
	}

}
