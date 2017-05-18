package com.esteban.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TotalValueOrders implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		body = body + "<total>" + exchange.getIn().getHeader("total") + "</total>";
		exchange.getIn().setBody(body);
	}

}
