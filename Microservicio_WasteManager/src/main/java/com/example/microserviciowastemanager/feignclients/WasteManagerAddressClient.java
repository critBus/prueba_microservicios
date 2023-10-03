package com.example.microserviciowastemanager.feignclients;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.microserviciowastemanager.modelos.*;


@FeignClient(name = "microserviciowastemanageraddres")
@RequestMapping("/address")
public interface WasteManagerAddressClient {

	@GetMapping
	public List<WasteManagerAddressEntity> listar();
	
	@GetMapping("/{id}")
	public WasteManagerAddressEntity obtener(@PathVariable("id") int id);
	
	@PostMapping
	public WasteManagerAddressEntity guardar(@RequestBody WasteManagerAddressEntity entidad);
}
