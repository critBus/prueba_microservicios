package com.example.microserviciowastemanager.entidades;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.microserviciowastemanager.modelos.WasteManagerAddressEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
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

private Long WasteManagerAddressId;

private Boolean isEnabled = Boolean.TRUE;
private Long version = 0L;

@Temporal(TemporalType.TIMESTAMP)
@CreatedDate
private Date createdDate;
@Temporal(TemporalType.TIMESTAMP)
@LastModifiedDate
private Date lastModifiedDate;

@PreUpdate
public void setLastUpdate() {  this.lastModifiedDate = new Date(); }

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

public Long getWasteManagerAddressId() {
	return WasteManagerAddressId;
}
public void setWasteManagerAddressId(Long wasteManagerAddressId) {
	WasteManagerAddressId = wasteManagerAddressId;
}

//public WasteManagerAddressEntity getWasteManagerAddressEntity() {
//	return wasteManagerAddressEntity;
//}
//public void setWasteManagerAddressEntity(WasteManagerAddressEntity wasteManagerAddressEntity) {
//	this.wasteManagerAddressEntity = wasteManagerAddressEntity;
//}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Date getLastModifiedDate() {
	return lastModifiedDate;
}
public void setLastModifiedDate(Date lastModifiedDate) {
	this.lastModifiedDate = lastModifiedDate;
} 

//public Long getId() {
//	return this.id;
//}

}
