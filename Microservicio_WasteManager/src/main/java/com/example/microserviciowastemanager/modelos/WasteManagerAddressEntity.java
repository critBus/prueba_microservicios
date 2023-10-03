package com.example.microserviciowastemanager.modelos;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class WasteManagerAddressEntity {
	private String direccion;
		
		
	private Boolean isEnabled = Boolean.TRUE;
	
	private Long version = 0L;
	
	private Date createdDate;
	
	private Date lastModifiedDate;
}
