package com.esteban.camel.processors;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;

public class DestinationInHeaderProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		String companyId = XPathBuilder.xpath("/orderItem/companyId", String.class).evaluate(exchange.getContext(),
				body);
		String destination = "jms:queue:company" + companyId;
		exchange.getIn().getHeaders().put("destination", destination);

	}

}
