package com.esteba.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.esteban.camel.exceptions.NoTransformUpperCaseException;

public class ToUppercaseLetter implements Processor {

	@Override
	public void process(Exchange exc) throws Exception {
		String body = exc.getIn().getBody(String.class);
		if (body.contains("ONLYDOWNCASE")) {
			 throw new NoTransformUpperCaseException();
		}else{
			body = body.toUpperCase();
			exc.getIn().setBody(body);
		}
	}

}
