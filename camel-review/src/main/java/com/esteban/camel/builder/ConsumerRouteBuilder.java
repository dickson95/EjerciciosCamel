package com.esteban.camel.builder;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import com.esteban.camel.exception.IllegalAmountOfPencilsException;
import com.esteban.camel.processors.DestinationInHeaderProcessor;
import com.esteban.camel.processors.OrderNumberInHeaderProcessor;
import com.esteban.camel.processors.PencilBatchProcessor;
import com.esteban.camel.processors.TotalInHeaderProcessor;
import com.esteban.camel.processors.TotalValueOrders;
import com.esteban.camel.strategy.OrderAggregatorStrategy;

public class ConsumerRouteBuilder extends RouteBuilder {
	private static final String FINAL_ORDER = "direct:finalOrder";
	@Override
	public void configure() throws Exception {
		DataFormat dataFormat = new JaxbDataFormat("com.esteban.camel.entity");
		onException(IllegalAmountOfPencilsException.class)
			// It exception will be handled then does not assert on test method
			.handled(true)
			.to(FINAL_ORDER)
			.unmarshal(dataFormat)
		.to("jpa:com.esteban.camel.entity.OrderItem?persistenceUnit=order_exceptions");

		
		from("file:inTest?noop=true")
		.process(new OrderNumberInHeaderProcessor())
		.to("direct:orders")
		.to("mock:orderInHeader");

		from("direct:orders")
		.process(new TotalInHeaderProcessor())
		.to("direct:ordersTotal")
		.to("mock:orderInHeader");
		
		from("direct:ordersTotal")
		.process(new DestinationInHeaderProcessor())
		
		.process(new PencilBatchProcessor())
		.recipientList(header("destination"))
		.to(FINAL_ORDER)
		.to("mock:finalOrder");

		from(FINAL_ORDER)
		.aggregate(header("orderNumber"), new OrderAggregatorStrategy())
			.completionFromBatchConsumer().process(new TotalValueOrders())
		.to("mock:orderAggregated")
		.to("file:out");

	}

}
