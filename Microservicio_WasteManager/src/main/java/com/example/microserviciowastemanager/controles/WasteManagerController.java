package com.example.microserviciowastemanager.controles;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microserviciowastemanager.entidades.*;
import com.example.microserviciowastemanager.repositorios.*;
import com.example.microserviciowastemanager.servicios.*;

@RestController
@RequestMapping("/waste")
public class WasteManagerController {
	@Autowired
	private WasteManagerService service;
	
	@GetMapping
	public ResponseEntity<List<WasteManagerEntity>> listar(){
		List<WasteManagerEntity> lista = service.getAll();
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WasteManagerEntity> obtener(@PathVariable("id") int id){
		WasteManagerEntity entidad = service.getById(id);
		if(entidad == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entidad);
	}
	
	@PostMapping
	public ResponseEntity<WasteManagerEntity> guardar(@RequestBody WasteManagerEntity entidad){
		WasteManagerEntity nuevo= service.save(entidad);
		return ResponseEntity.ok(nuevo);
	}
}
