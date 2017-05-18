package com.esteban.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;

public class OrderNumberInHeaderProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		String orderNumber = XPathBuilder.xpath("/orderItem/orderId", String.class).evaluate(exchange.getContext(), body);
		exchange.getIn().getHeaders().put("OrderNumber", orderNumber);
	}

}
