package com.esteban.camel.builderFiles;

public class OrderItemXmlFile {
	private int id = 1;
	private long itemId = 1;
	private int orderId = 01;
	private String description = "N2PENCIL";
	private int companyId = 01;
	private double price = 0.15;
	private int amount = 200;
	
	public OrderItemXmlFile(){}
	
	
	public OrderItemXmlFile(int id, long itemId, int orderId, String description, int companyId, double price,
			int amount) {
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.description = description;
		this.companyId = companyId;
		this.price = price;
		this.amount = amount;
	}


	/**
	 * Construye el objeto con los valores que hallan sido asignados a las variables del objeto
	 * @return Un objeto ItemXmlFile
	 */
	public OrderItemXmlFile build(){
		return new OrderItemXmlFile(id, itemId, orderId, description, companyId, price, amount);
	}
	
	
	
	public OrderItemXmlFile withId(int id) {
		this.id = id;
		return this;
	}
	
	public OrderItemXmlFile withItemId(int itemId) {
		this.itemId = itemId;
		return this;
	}

	public OrderItemXmlFile withOrderId(int orderId) {
		this.orderId = orderId;
		return this;
	}

	public OrderItemXmlFile withDescription(String description) {
		this.description = description;
		return this;
	}

	public OrderItemXmlFile withCompanyId(int companyId) {
		this.companyId = companyId;
		return this;
	}

	public OrderItemXmlFile withPrice(double price) {
		this.price = price;
		return this;
	}

	public OrderItemXmlFile withAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public String body(){
		String body = 
				"<orderItem>"
				+ "	<id>" + id + "</id>"
				+ "	<orderId>" + orderId + "</orderId>"
				+ "	<description>" + description + "</description>"
				+ "	<companyId>" + companyId + "</companyId>"
				+ "	<price>" + price + "</price>"
				+ "	<amount>" + amount + "</amount>"
				+ "</orderItem>";
		return body;
	}
	
	
}
