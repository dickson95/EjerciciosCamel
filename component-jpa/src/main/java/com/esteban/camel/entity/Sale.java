package com.esteban.camel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Sales")
@NamedQuery(name = "Sale.findAll", query = "Select s from Sale as s")
public class Sale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@XmlElement(name="no_bill")
	@Column(nullable=false)
	private long no_bill;
	
	@XmlElement(name="sells")
	@Column(nullable=false)
	private String sells;

	@XmlElement(name="description")
	@Column(nullable=false)
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSells() {
		return sells;
	}

	public void setSells(String sells) {
		this.sells = sells;
	}

	public long getNo_bill() {
		return no_bill;
	}

	public void setNo_bill(long no_bill) {
		this.no_bill = no_bill;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
