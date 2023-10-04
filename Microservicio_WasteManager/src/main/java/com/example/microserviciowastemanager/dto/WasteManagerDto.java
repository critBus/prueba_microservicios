package com.example.microserviciowastemanager.dto;

import com.example.microserviciowastemanager.modelos.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.microserviciowastemanager.entidades.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasteManagerDto {
	private Long id;
	 private String nombre;
	 private String nif;
//	 private WasteManagerAddressEntity wasteManagerAddressEntity;
	 private Long WasteManagerAddressId;
	 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
//	public WasteManagerAddressEntity getWasteManagerAddressEntity() {
//		return wasteManagerAddressEntity;
//	}
//	public void setWasteManagerAddressEntity(WasteManagerAddressEntity wasteManagerAddressEntity) {
//		this.wasteManagerAddressEntity = wasteManagerAddressEntity;
//	} 
	public Long getWasteManagerAddressId() {
		return WasteManagerAddressId;
	}
	public void setWasteManagerAddressId(Long wasteManagerAddressId) {
		WasteManagerAddressId = wasteManagerAddressId;
	}

}
