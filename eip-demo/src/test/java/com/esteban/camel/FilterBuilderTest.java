package com.esteban.camel;

import java.io.File;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.esteban.camel.builder.FilterBuilder;
import com.esteban.camel.builder.JavaRouteBuilder;

public class FilterBuilderTest extends CamelTestSupport {
	public static Logger log = Logger.getLogger(JavaRouteBuilder.class.getName());
	public final String FILE_NAME = Exchange.FILE_NAME;

	@Override
	public RouteBuilder createRouteBuilder() throws Exception {
		return new FilterBuilder();
	}

	@Test
	public void testMoveFileXml() throws Exception {
		
		// Arrange
		String body = new String();
		body.concat("<description>");
		body.concat("XML file only");
		body.concat("</description>");
		
		log.info("Test from splitter pattern. Virtual file name: " + FILE_NAME);
		template.sendBodyAndHeader("file:in", body, FILE_NAME, "order.xml");	
		
		// Act
		Thread.sleep(1000);
		File xml = new File("outFilter/order.xml");
		
		
		// Assert
		assertTrue("File not moved", xml.exists());
	}
	
	@Test
	public void testNotMoveFiletXT() throws Exception {
		
		// Arrange
		
		log.info("Test from splitter pattern. Virtual file name: " + FILE_NAME);
		template.sendBodyAndHeader("file:in", "File not moved", FILE_NAME, "order.txt");
		
		// Act
		Thread.sleep(1000);
		File txt = new File("outFilter/order.txt");
		
		
		// Assert
		assertFalse("File not moved", txt.exists());
	}
}
