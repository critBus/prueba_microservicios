package com.example.microserviciowastemanager.entidades;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.microserviciowastemanager.modelos.WasteManagerAddressEntity;

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

private WasteManagerAddressEntity wasteManagerAddressEntity;

//private Long addressid;

private Boolean isEnabled = Boolean.TRUE;
private Long version = 0L;

@Temporal(TemporalType.TIMESTAMP)
@CreatedDate
private Date createdDate;
@Temporal(TemporalType.TIMESTAMP)
@LastModifiedDate
private Date lastModifiedDate;
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getNif() {
	return nif;
}
public void setNif(String nif) {
	this.nif = nif;
}
//public Long getAddressid() {
//	return addressid;
//}
//public void setAddressid(Long addressid) {
//	this.addressid = addressid;
//}
public Boolean getIsEnabled() {
	return isEnabled;
}
public void setIsEnabled(Boolean isEnabled) {
	this.isEnabled = isEnabled;
}
public Long getVersion() {
	return version;
}
public void setVersion(Long version) {
	this.version = version;
}
public WasteManagerAddressEntity getWasteManagerAddressEntity() {
	return wasteManagerAddressEntity;
}
public void setWasteManagerAddressEntity(WasteManagerAddressEntity wasteManagerAddressEntity) {
	this.wasteManagerAddressEntity = wasteManagerAddressEntity;
} 

public Long getId() {
	return this.id;
}

}
