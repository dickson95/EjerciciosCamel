package com.esteban.camel.builder;

import org.apache.camel.builder.RouteBuilder;

import com.esteba.camel.processors.ToUppercaseLetter;
import com.esteban.camel.exceptions.NoTransformUpperCaseException;

public class TransformUpcaseBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// handled method avoid show exception 
		// El método handled evita que la excepción sea mostrada
		onException(NoTransformUpperCaseException.class).handled(true)
		  .to("file:outTxtDownCase");
		
		
		from("file:in?noop=true")
		.choice()
		.when(header("CamelFileName").endsWith(".txt"))
			.process(new ToUppercaseLetter())
			.to("file:outTxt")
		.when(header("CamelFileName").endsWith(".xml"))
			.to("file:outXml")
		.end();
			

	}

}
