package com.example.microserviciowastemanageraddres.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<WasteManagerAddressEntity> findById(@PathVariable("id") int id){
		WasteManagerAddressEntity entidad = service.findById(id);
		if(entidad == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entidad);
	}
	
	@PostMapping
	public ResponseEntity<WasteManagerAddressEntity> save(@RequestBody WasteManagerAddressEntity entidad){
		WasteManagerAddressEntity nuevo= service.save(entidad);
		return ResponseEntity.ok(nuevo);
	}
}
