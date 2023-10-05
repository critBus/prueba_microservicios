package com.example.microserviciowastemanager.servicios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.microserviciowastemanager.entidades.*;
import com.example.microserviciowastemanager.repositorios.*;

import feign.FeignException;

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
			//wdto.setWasteManagerAddressId(v.getWasteManagerAddressEntity().getId());
			wdto.setWasteManagerAddressId(v.getWasteManagerAddressId());
			respuesta.add(wdto);
		});
		return respuesta;
	}
	
	public WasteManagerEntity findById(Long id) {
		return repository.findById(id).orElse(null);//
	}
	
//	public WasteManagerEntity save(WasteManagerEntity entidad) {
//		WasteManagerEntity nuevo = repository.save(entidad);
//		return nuevo;
//	}

	
	public ResponseEntity create(WasteManagerDto wasteManagerDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
        	return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            //throw new IllegalArgumentException("Invalid input data");
        }

        // Create a new WasteManagerEntity
        WasteManagerEntity wasteManagerEntity = new WasteManagerEntity();
        wasteManagerEntity.setNombre(wasteManagerDto.getNombre());
        wasteManagerEntity.setNif(wasteManagerDto.getNif());
        wasteManagerEntity.setIsEnabled(Boolean.TRUE);
        wasteManagerEntity.setVersion(0L);
        
        System.out.println("va a pedir el addres3");
        
        ResponseEntity response=null;
        try {
         response =
        		wasteManagerAddressClient.findById(
        		wasteManagerDto.getWasteManagerAddressId()
        		);
        System.out.println("lo proceso");
        }catch (FeignException e) {
        	if(e.status()==404) {
        		return ResponseEntity.badRequest().body("Could not retrieve the WasteManagerAddressEntity");
        	}
        	return ResponseEntity.internalServerError().body(e.getMessage());
        	
			// TODO: handle exception
		}
        catch (Exception e) {
			// TODO: handle exception
        	return ResponseEntity.internalServerError().body(e.getMessage());
		}
        
        if (response.getStatusCode() != HttpStatus.OK) {
        	return ResponseEntity.badRequest().body("Could not retrieve the WasteManagerAddressEntity");
        }

        // Set the WasteManagerAddressEntity in the WasteManagerEntity
        wasteManagerEntity.setWasteManagerAddressId(wasteManagerDto.getWasteManagerAddressId());

        // Save the WasteManagerEntity in the database
        WasteManagerEntity savedWasteManagerEntity = repository.save(wasteManagerEntity);
        
        WasteManagerDto respuesta=new WasteManagerDto();
        respuesta.setId(savedWasteManagerEntity.getId());
        respuesta.setNif(savedWasteManagerEntity.getNif());
        respuesta.setNombre(savedWasteManagerEntity.getNombre());
        respuesta.setWasteManagerAddressId(savedWasteManagerEntity.getWasteManagerAddressId());
        
        return ResponseEntity.ok(respuesta);
       }
	
	 public ResponseEntity update(WasteManagerDto dto, BindingResult bindingResult) throws Exception {
	        if (bindingResult.hasErrors()) {
	        	return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//	            throw new IllegalArgumentException("Invalid input data");
	        }
	        if(dto.getId()==null) {
	        	return ResponseEntity.badRequest().body("The given id must not be null!");
	        }

	        // Retrieve the WasteManagerEntity from the database
	        Optional<WasteManagerEntity> optionalWasteManagerEntity = 
	        		repository.findById(
	        				dto.getId()
	        				);
	        if (!optionalWasteManagerEntity.isPresent()) {
	        	return ResponseEntity.badRequest().body("Could not find the WasteManagerEntity");
//	            throw new Exception("Could not find the WasteManagerEntity");
	        }

	        WasteManagerEntity wasteManagerEntity = optionalWasteManagerEntity.get();

	        // Update the WasteManagerEntity
	        wasteManagerEntity.setNombre(dto.getNombre());
	        wasteManagerEntity.setNif(dto.getNif());
	        
	        
	        ResponseEntity response=null;
	        try {
	         response =
	        		wasteManagerAddressClient.findById(
	        				dto.getWasteManagerAddressId()
	        		);
	        System.out.println("lo proceso");
	        }catch (FeignException e) {
	        	if(e.status()==404) {
	        		return ResponseEntity.badRequest().body("Could not retrieve the WasteManagerAddressEntity");
	        	}
	        	return ResponseEntity.internalServerError().body(e.getMessage());
	        	
				// TODO: handle exception
			}
	        catch (Exception e) {
				// TODO: handle exception
	        	return ResponseEntity.internalServerError().body(e.getMessage());
			}
//	        // Retrieve the WasteManagerAddressEntity from the WasteManagerAddress microservice
//	        ResponseEntity<WasteManagerAddressEntity> response = wasteManagerAddressClient.findById(dto.getWasteManagerAddressId());
//	        if (response.getStatusCode() != HttpStatus.OK) {
//	            throw new Exception("Could not retrieve the WasteManagerAddressEntity");
//	        }

	        // Set the WasteManagerAddressEntity in the WasteManagerEntity
//	        wasteManagerEntity.setWasteManagerAddressEntity(response.getBody());
	        wasteManagerEntity.setWasteManagerAddressId(dto.getWasteManagerAddressId());

	        // Save the updated WasteManagerEntity in the database
	        WasteManagerEntity updatedWasteManagerEntity = repository.save(wasteManagerEntity);
	        
	        WasteManagerDto respuesta=new WasteManagerDto();
	        respuesta.setId(updatedWasteManagerEntity.getId());
	        respuesta.setNif(updatedWasteManagerEntity.getNif());
	        respuesta.setNombre(updatedWasteManagerEntity.getNombre());
	        respuesta.setWasteManagerAddressId(updatedWasteManagerEntity.getWasteManagerAddressId());
	        
	        return ResponseEntity.ok(respuesta);
//	        return respuesta;
//	        return new ResponseEntity<>(updatedWasteManagerEntity, HttpStatus.OK);
	    }
	 
	  public ResponseEntity findById(long wasteManagerId) throws Exception {
	        // Retrieve the WasteManagerEntity from the database
	        Optional<WasteManagerEntity> optionalWasteManagerEntity = repository.findById(wasteManagerId);
	        if (!optionalWasteManagerEntity.isPresent()) {
	        	return ResponseEntity.badRequest().body("Could not find the WasteManagerEntity");
//	            throw new Exception("Could not find the WasteManagerEntity");
	        }

	        WasteManagerEntity wasteManagerEntity = optionalWasteManagerEntity.get();
	       
	        WasteManagerDto respuesta=new WasteManagerDto();
	        respuesta.setId(wasteManagerEntity.getId());
	        respuesta.setNif(wasteManagerEntity.getNif());
	        respuesta.setNombre(wasteManagerEntity.getNombre());
	        respuesta.setWasteManagerAddressId(wasteManagerEntity.getWasteManagerAddressId());
	        
	        return ResponseEntity.ok(respuesta);
	        //return respuesta;
	        //return new ResponseEntity<>(wasteManagerEntity, HttpStatus.OK);
	    }
	  
	  public void deleteById(Long id) {
	        repository.deleteById(id);
	    }
	

	
}
