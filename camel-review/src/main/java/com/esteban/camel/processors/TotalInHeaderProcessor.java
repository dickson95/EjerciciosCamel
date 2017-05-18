package com.esteban.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TotalInHeaderProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getIn().getHeaders().put("total", 0.0);
	}

}
