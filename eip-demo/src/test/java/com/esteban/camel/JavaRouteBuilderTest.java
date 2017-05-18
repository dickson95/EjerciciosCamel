package com.esteban.camel;

import java.io.File;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.esteban.camel.builder.JavaRouteBuilder;

public class JavaRouteBuilderTest extends CamelTestSupport {
	public static Logger log = Logger.getLogger(JavaRouteBuilder.class.getName());

	@Override
	public RouteBuilder createRouteBuilder() throws Exception {
		return new JavaRouteBuilder();
	}

	@Test
	public void testMoveFile() throws Exception {
		log.info("File name virtual: "+ Exchange.FILE_NAME);
		
		// Arrange
		template.sendBodyAndHeader("file:inSimple", "Purchase order", Exchange.FILE_NAME, 
				"order.txt");
		
		// Act
		
		// Se deja un segundo después de crear el archivo en vista de que camel 
		// revisa dos veces por segundo los archivos entrantes
		// Sleep one second after dropping the file in the inbox folder. 
		// Camel scans twice per second for incoming files so one second is necessary for safety
		Thread.sleep(1000);
		
		File txt = new File("outputSimple/order.txt");
		
		// Assert
		assertTrue("No se movió el archivo", txt.exists());
		
		
		

	}

}
