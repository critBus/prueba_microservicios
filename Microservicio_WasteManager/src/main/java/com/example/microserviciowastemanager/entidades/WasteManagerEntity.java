package com.example.microserviciowastemanager.entidades;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
public class WasteManagerEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nombre;
private String nif;
//private WasteManagerAddressEntity wasteManagerAddressEntity;

private Long addressid;

private Boolean isEnabled = Boolean.TRUE;
private Long version = 0L;

@Temporal(TemporalType.TIMESTAMP)
@CreatedDate
private Date createdDate;
@Temporal(TemporalType.TIMESTAMP)
@LastModifiedDate
private Date lastModifiedDate; 

}
