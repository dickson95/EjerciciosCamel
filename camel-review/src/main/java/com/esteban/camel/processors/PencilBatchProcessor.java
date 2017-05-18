package com.esteban.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.log4j.Logger;

import com.esteban.camel.exception.IllegalAmountOfPencilsException;

public class PencilBatchProcessor implements Processor {
	public static final Logger log = Logger.getLogger(PencilBatchProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		int amount = XPathBuilder.xpath("/orderItem/amount", Integer.class).evaluate(exchange.getContext(), body,
				Integer.class);
		System.out.println("Analizando si amoint es valido para continuar: " + amount);
		if (amount < 144) {
			System.out.println("No es valido para continuar con el proceso");
			log.error("Bussiness exception: The number of pencil is lower than allowed");
			throw new IllegalAmountOfPencilsException();
		}

	}

}
