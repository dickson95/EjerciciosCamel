package com.esteban.camel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "OrderItems")
@Table(name="OrderItems")

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@XmlAttribute(name="id")
	@Column(name="itemId", nullable = false)
	private long itemId;

	@XmlAttribute(name="orderId")
	@Column(name = "orderId", nullable = false)
	private int orderId;

	@XmlAttribute(name="description")
	@Column(name = "description", nullable = false)
	private char description;

	@XmlAttribute(name="companyId")
	@Column(name = "companyId", nullable = false)
	private int companyId;

	@XmlAttribute(name="price")
	@Column(name = "price", nullable = false)
	private double price;

	@XmlAttribute(name="amount")
	@Column(name = "amount", nullable = false)
	private int amount;

	public OrderItem(){}
	
	public OrderItem(int id, long itemId, int orderId, int companyId, double price, int amount) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.companyId = companyId;
		this.price = price;
		this.amount = amount;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public char getDescription() {
		return description;
	}

	public void setDescription(char description) {
		this.description = description;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
