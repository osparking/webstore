package com.ezen.webstore.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String name;
	private String address;
	private int noOfOrdersMade;
}
