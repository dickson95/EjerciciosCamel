package com.esteban.camel.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.log4j.Logger;

public class OrderAggregatorStrategy implements AggregationStrategy {
	public static final Logger log = Logger.getLogger(OrderAggregatorStrategy.class);
	public static final String TOTAL = "total";

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		newValueHeaderTotal(oldExchange, newExchange);
		
		if (oldExchange == null) {
			return newExchange;
		}		
		
		String old = oldExchange.getIn().getBody(String.class);
		String newBody = newExchange.getIn().getBody(String.class);
		
		newBody = old + newBody;
		newExchange.getIn().setBody(newBody);
		
		return newExchange;
	}
	
	
	private void newValueHeaderTotal(Exchange oldExchange, Exchange newExchange){
		double price = Double.parseDouble(XPathBuilder.xpath("/orderItem/price").evaluate(newExchange.getContext(),
				newExchange.getIn().getBody(String.class)));
		
		double total = (oldExchange == null) ? price : oldExchange.getIn().getHeader(TOTAL, Double.class) + price;
		newExchange.getIn().getHeaders().put(TOTAL, total);
	}

}
