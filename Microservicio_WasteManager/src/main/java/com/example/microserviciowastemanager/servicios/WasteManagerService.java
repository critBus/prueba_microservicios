package com.example.microserviciowastemanager.servicios;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.microserviciowastemanager.entidades.*;
import com.example.microserviciowastemanager.repositorios.*;

import com.example.microserviciowastemanager.feignclients.*;
import com.example.microserviciowastemanager.modelos.*;
import com.example.microserviciowastemanager.dto.*;
import java.util.Optional;

@Service
public class WasteManagerService {

	@Autowired
	private WasteManagerRepository repository;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WasteManagerAddressClient wasteManagerAddressClient;
	
	public List<WasteManagerDto> getAll(){
		List<WasteManagerEntity>  lista=repository.findAll();
		List<WasteManagerDto> respuesta=new ArrayList<WasteManagerDto>();
		lista.forEach(v->{
			WasteManagerDto wdto=new WasteManagerDto();
			wdto.setId(v.getId());
			wdto.setNif(v.getNif());
			wdto.setNombre(v.getNombre());
			wdto.setWasteManagerAddressId(v.getWasteManagerAddressEntity().getId());
			respuesta.add(wdto);
		});
		return respuesta;
	}
	
	public WasteManagerEntity findById(int id) {
		return repository.findById(id).orElse(null);//
	}
	
	public WasteManagerEntity save(WasteManagerEntity entidad) {
		WasteManagerEntity nuevo = repository.save(entidad);
		return nuevo;
	}

	
	public ResponseEntity<WasteManagerEntity> create(WasteManagerDto wasteManagerDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Invalid input data");
        }

        // Create a new WasteManagerEntity
        WasteManagerEntity wasteManagerEntity = new WasteManagerEntity();
        wasteManagerEntity.setNombre(wasteManagerDto.getNombre());
        wasteManagerEntity.setNif(wasteManagerDto.getNif());
        wasteManagerEntity.setIsEnabled(Boolean.TRUE);
        wasteManagerEntity.setVersion(0L);
        

        // Retrieve the WasteManagerAddressEntity from the WasteManagerAddress microservice
        ResponseEntity<WasteManagerAddressEntity> response =
        		wasteManagerAddressClient.findById(
        		wasteManagerDto.getWasteManagerAddressId()
        		);
        
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Could not retrieve the WasteManagerAddressEntity");
        }

        // Set the WasteManagerAddressEntity in the WasteManagerEntity
        wasteManagerEntity.setWasteManagerAddressEntity(response.getBody());

        // Save the WasteManagerEntity in the database
        WasteManagerEntity savedWasteManagerEntity = repository.save(wasteManagerEntity);

        return new ResponseEntity<>(savedWasteManagerEntity, HttpStatus.CREATED);
    }
	
	 public ResponseEntity<WasteManagerEntity> update(WasteManagerDto dto, BindingResult bindingResult) throws Exception {
	        if (bindingResult.hasErrors()) {
	            throw new IllegalArgumentException("Invalid input data");
	        }

	        // Retrieve the WasteManagerEntity from the database
	        Optional<WasteManagerEntity> optionalWasteManagerEntity = 
	        		repository.findById(
	        				dto.getId().intValue()
	        				);
	        if (!optionalWasteManagerEntity.isPresent()) {
	            throw new Exception("Could not find the WasteManagerEntity");
	        }

	        WasteManagerEntity wasteManagerEntity = optionalWasteManagerEntity.get();

	        // Update the WasteManagerEntity
	        wasteManagerEntity.setNombre(dto.getNombre());
	        wasteManagerEntity.setNif(dto.getNif());
	        
	        

	        // Retrieve the WasteManagerAddressEntity from the WasteManagerAddress microservice
	        ResponseEntity<WasteManagerAddressEntity> response = wasteManagerAddressClient.findById(dto.getWasteManagerAddressId());
	        if (response.getStatusCode() != HttpStatus.OK) {
	            throw new Exception("Could not retrieve the WasteManagerAddressEntity");
	        }

	        // Set the WasteManagerAddressEntity in the WasteManagerEntity
	        wasteManagerEntity.setWasteManagerAddressEntity(response.getBody());

	        // Save the updated WasteManagerEntity in the database
	        WasteManagerEntity updatedWasteManagerEntity = repository.save(wasteManagerEntity);

	        return new ResponseEntity<>(updatedWasteManagerEntity, HttpStatus.OK);
	    }
	 
	  public ResponseEntity<WasteManagerEntity> findById(long wasteManagerId) throws Exception {
	        // Retrieve the WasteManagerEntity from the database
	        Optional<WasteManagerEntity> optionalWasteManagerEntity = repository.findById(new Long(wasteManagerId).intValue());
	        if (!optionalWasteManagerEntity.isPresent()) {
	            throw new Exception("Could not find the WasteManagerEntity");
	        }

	        WasteManagerEntity wasteManagerEntity = optionalWasteManagerEntity.get();
	        return new ResponseEntity<>(wasteManagerEntity, HttpStatus.OK);
	    }
	

	
}
