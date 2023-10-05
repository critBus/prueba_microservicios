package com.example.microserviciowastemanager.controles;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.microserviciowastemanager.entidades.*;
import com.example.microserviciowastemanager.repositorios.*;
import com.example.microserviciowastemanager.servicios.*;
import com.example.microserviciowastemanager.dto.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/waste")
public class WasteManagerController {
	@Autowired
	private WasteManagerService service;
	
	@GetMapping
	public ResponseEntity<List<WasteManagerDto>> getAll(){
		List<WasteManagerDto> lista = service.getAll();
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@CircuitBreaker(name = "microserviciowastemanageraddresCB",fallbackMethod = "fallBackGetAddres")
	@PostMapping
    public ResponseEntity create(@Valid @RequestBody WasteManagerDto wasteManagerDto
    		, BindingResult bindingResult) throws Exception {
		return service.create(wasteManagerDto,bindingResult);

    }
	
	@CircuitBreaker(name = "microserviciowastemanageraddresCB",fallbackMethod = "fallBackGetAddres")
    @PutMapping
    public ResponseEntity update(@Valid @RequestBody WasteManagerDto wasteManagerDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return  service.update(wasteManagerDto,bindingResult);

    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") long wasteManagerId) throws Exception {
    	return  service.findById(wasteManagerId);

    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    private ResponseEntity fallBackGetAddres(@Valid @RequestBody WasteManagerDto wasteManagerDto
    		, BindingResult bindingResult,RuntimeException excepcion) throws Exception {
    
		return new ResponseEntity("El servidor de Addres esta desactivado", HttpStatus.OK);
	} 
    
}
