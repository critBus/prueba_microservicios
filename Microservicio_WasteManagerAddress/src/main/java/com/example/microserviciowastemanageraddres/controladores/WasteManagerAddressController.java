package com.example.microserviciowastemanageraddres.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.microserviciowastemanageraddres.entidades.*;
import com.example.microserviciowastemanageraddres.repositorio.*;
import com.example.microserviciowastemanageraddres.servicios.*;


@RestController
@RequestMapping("/address")
public class WasteManagerAddressController {
	@Autowired
	private WasteManagerAddressService service;
	
	@GetMapping
	public ResponseEntity<List<WasteManagerAddressEntity>> getAll(){
		List<WasteManagerAddressEntity> lista = service.getAll();
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WasteManagerAddressEntity> findById(@PathVariable("id") Long id){
		WasteManagerAddressEntity entidad = service.findById(id);
		if(entidad == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entidad);
	}
	
	@PostMapping
	public ResponseEntity<WasteManagerAddressEntity> save(@RequestBody WasteManagerAddressEntity entidad){
//		System.out.println("va ha intentar agregar");
		WasteManagerAddressEntity nuevo= service.save(entidad);
//		System.out.println("supuestamente agrego");
		ResponseEntity<WasteManagerAddressEntity> r=ResponseEntity.ok(nuevo);
//		System.out.println("se preparo la respuesta");
		return r;
	}
	
	 @PutMapping
	    public ResponseEntity update(@Valid @RequestBody WasteManagerAddressEntity entidad, BindingResult bindingResult) throws Exception {
	        if (bindingResult.hasErrors()) {
	            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
	        }
	        return  service.update(entidad,bindingResult);
	 	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
