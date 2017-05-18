package com.esteban.camel.main;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import com.esteban.camel.builder.ConsumerRouteBuilder;

public class ConsumerRoute {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

		context.setTracing(true);
		context.addComponent("jms", JmsComponent.jmsComponent(connectionFactory));
		context.addRoutes(new ConsumerRouteBuilder());

		context.start();
		Thread.sleep(5000);
		context.stop();

	}

}
