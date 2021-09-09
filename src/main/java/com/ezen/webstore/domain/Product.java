package com.ezen.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//formatter:off
@Getter @Setter @EqualsAndHashCode
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String name;
	@Setter(value = AccessLevel.NONE)
	private BigDecimal unitPrice;
	private String unitPriceStr;
	private String description;
	private String manufacturer;
	private String category;
	@Setter(value = AccessLevel.NONE)
	private long unitsInStock;
	private String unitsInStockStr;
	private long unitsInOrder;
	private boolean discontinued;
	private String condition;
	
	public Product() {
		super();
	}

	public Product(String productId, 
			String name, 
			BigDecimal unitPrice) {
		this.productId = productId;
		this.name = name;
		this.setUnitPrice(unitPrice);
	}
	
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
		DecimalFormat df = new DecimalFormat("#,###");
		this.setUnitPriceStr(df.format(unitPrice));
	} 

	public void setUnitsInStock(long unitsInStock) {
		DecimalFormat df = new DecimalFormat("#,###");
		this.unitsInStock = unitsInStock;
		this.setUnitsInStockStr(df.format(unitsInStock));
	}
}
