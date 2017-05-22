package com.esteban.camel.builder;



import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.esteban.camel.builderFiles.OrderItemXmlFile;

public class ConsumerRouteTest extends CamelTestSupport {
	protected MockEndpoint mock;
	public static final String ORDER_IN_HEADER = "mock:orderInHeader";

	@Override
	public void setUp() throws Exception {
		deleteDirectory("inTest");
		deleteDirectory("out");
		super.setUp();
	}

	@Override
	protected CamelContext createCamelContext() throws Exception {
		CamelContext context = super.createCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.setTracing(true);

		return context;
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new ConsumerRouteBuilder();
	}

	/***
	 * Prueba para determinar que el header del mensaje está siendo establecido
	 * para el número de orden que usa el aggregatos para agrupar los items
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void OrderNumberHeaderTest() throws InterruptedException {
		// Arrange
		OrderItemXmlFile item = new OrderItemXmlFile();
		template.sendBodyAndHeader("file:inTest?noop=true", item.body(), Exchange.FILE_NAME, "itemOrderExample.xml");

		// Act
		mock = getMockEndpoint(ORDER_IN_HEADER);

		// Assert
		mock.expectedMessageCount(1);
		mock.expectedHeaderReceived("OrderNumber", 1);
		mock.assertIsSatisfied();
	}

	/**
	 * Al final de la ruta se deben sumar todos los precios de los items de una
	 * misma orden de compra
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void TotalInHeaderTest() throws InterruptedException {
		// Arrange
		OrderItemXmlFile item = new OrderItemXmlFile();
		template.sendBodyAndHeader("file:inTest?noop=true", item.withPrice(1.0).body(), Exchange.FILE_NAME,
				"itemOrderExample2.xml");
		template.sendBodyAndHeader("file:inTest?noop=true", item.withPrice(1.0).withAmount(100).body(),
				Exchange.FILE_NAME, "itemOrderExample3.xml");

		// Act
		mock = getMockEndpoint("mock:orderAggregated");

		// Assert
		mock.expectedMessageCount(1);
		mock.expectedHeaderReceived("total", 2.0);
		// mock.message(0).header("total").isEqualTo(2.0);
		mock.assertIsSatisfied();

	}

	/**
	 * Regla de negocio en la que si la orden tiene menos de 144 unidades en el
	 * pedido debe lanzar una exvepción y guardar la información en la base de
	 * datos para análizis posterior.
	 */
    /*
	@Test
	public void AmountOfPencilFailureTest() throws IllegalAmountOfPencilsException {
		// Arrange
		OrderItemXmlFile item = new OrderItemXmlFile();
		Exchange exchange = getMandatoryEndpoint("direct:ordersTotal").createExchange(ExchangePattern.InOut);
		String body = item.withAmount(100).body();
		exchange.getIn().setBody(body);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("total", 0.0);
		headers.put("OrderNumber", 1);
		exchange.getIn().setHeaders(headers);

		// Act
		Exchange out = template.send("direct:ordersTotal", exchange);

		// Assert
		assertTrue("Debe fallar", out.isFailed());
		assertTrue("Debe ser IllegalAmountOfPencilsException",
				out.getException() instanceof IllegalAmountOfPencilsException);
		assertEquals("Solo se pueden procesar órdenes con 144 o mas lápices", out.getException().getMessage());
	}
	*/

}
