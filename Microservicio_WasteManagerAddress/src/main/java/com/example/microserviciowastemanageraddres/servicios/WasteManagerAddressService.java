package com.example.microserviciowastemanageraddres.servicios;



import com.example.microserviciowastemanageraddres.entidades.*;
import com.example.microserviciowastemanageraddres.repositorio.*;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class WasteManagerAddressService {

	
	@Autowired
	private WasteManagerAddressRepository repository;
	
	public List<WasteManagerAddressEntity> getAll(){
		return repository.findAll();
	}
	
	public WasteManagerAddressEntity findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public WasteManagerAddressEntity save(WasteManagerAddressEntity entidad) {
		WasteManagerAddressEntity nuevo = repository.save(entidad);
		return nuevo;
	}
	
	public void deleteById(Long id) {
        repository.deleteById(id);
    }
	
	public ResponseEntity update(WasteManagerAddressEntity entidad, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
        	return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if(entidad.getId()==null) {
        	return ResponseEntity.badRequest().body("The given id must not be null!");
        }

        // Retrieve the WasteManagerEntity from the database
        Optional<WasteManagerAddressEntity > optionalWasteManagerEntity = 
        		repository.findById(
        				entidad.getId()
        				);
        if (!optionalWasteManagerEntity.isPresent()) {
        	return ResponseEntity.badRequest().body("Could not find the WasteManagerEntity");
        }
        
        WasteManagerAddressEntity addres=optionalWasteManagerEntity.get();
        addres.setDireccion(entidad.getDireccion());
        
        addres=repository.save(addres);
        return ResponseEntity.ok(addres);
        
	}
	
	
	
}
