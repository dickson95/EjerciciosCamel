package com.esteban.camel.builder;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.esteban.camel.bean.NamerFile;
import com.esteban.camel.entity.Sale;

public class ListSalesRoute extends RouteBuilder {
	
	/**
	 * Esta guía fue hecha con base a este ejercicio. 
	 * https://goo.gl/LJWTUn
	 */
	
	@Override
	public void configure() throws Exception {
		// dataFormat que define el tipo de conversión con que se trabaja
		JaxbDataFormat dataFormat = parseWithProperties();
		
		from("jpa:com.esteban.camel.entity.Sale?persistenceUnit=component-jpa&consumeDelete=false")
			.split().body(Sale.class)
			.to("direct:saleRecord")
			.to("mock:saleRecordTest")
		.end();

		from("direct:saleRecord")
			.marshal(dataFormat)
			// Con el uso de un bean, cambiar el nombre del archivo usando el body
			// Más información  https://goo.gl/0pG5m4 
			.setHeader(Exchange.FILE_NAME, method(NamerFile.class, "nameFileXml"))
		.to("file:finalPoint");	

	}

	// Definir qué propiedades va a tener el dataFormat
	// Mas detalles de configuración http://camel.apache.org/jaxb.html
	private JaxbDataFormat parseWithProperties() {
		JaxbDataFormat dataFormat = new JaxbDataFormat("com.esteban.camel.entity");
		dataFormat.setSchema("classpath:schemas/schema.xsd");
		return dataFormat;
	}


}
