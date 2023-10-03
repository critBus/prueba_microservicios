package com.example.microserviciowastemanageraddres.servicios;


import com.example.microserviciowastemanageraddres.entidades.*;
import com.example.microserviciowastemanageraddres.repositorio.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WasteManagerAddressService {

	
	@Autowired
	private WasteManagerAddressRepository repository;
	
	public List<WasteManagerAddressEntity> getAll(){
		return repository.findAll();
	}
	
	public WasteManagerAddressEntity getById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public WasteManagerAddressEntity save(WasteManagerAddressEntity entidad) {
		WasteManagerAddressEntity nuevo = repository.save(entidad);
		return nuevo;
	}
	
	
	
}
