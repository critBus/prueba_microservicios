package com.example.microserviciowastemanager.servicios;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.microserviciowastemanager.entidades.*;
import com.example.microserviciowastemanager.repositorios.*;

import com.example.microserviciowastemanager.feignclients.*;
import com.example.microserviciowastemanager.modelos.WasteManagerAddressEntity;

@Service
public class WasteManagerService {

	@Autowired
	private WasteManagerRepository repository;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WasteManagerAddressClient wasteManagerAddressClient;
	
	public List<WasteManagerEntity> getAll(){
		return repository.findAll();
	}
	
	public WasteManagerEntity getById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public WasteManagerEntity save(WasteManagerEntity entidad) {
		WasteManagerEntity nuevo = repository.save(entidad);
		return nuevo;
	}
	
	public WasteManagerAddressEntity getAddress(int WasteManagerService_Id) {
		WasteManagerEntity entidadActual=getById(WasteManagerService_Id);
		if(entidadActual!=null) {
			return wasteManagerAddressClient.obtener(WasteManagerService_Id);
		}
		
		return null;
	}
	
}
