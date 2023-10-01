package com.example.microserviciowastemanager.repositorios;

import com.example.microserviciowastemanager.entidades.*;


import org.springframework.data.jpa.repository.JpaRepository;


public interface WasteManagerRepository extends JpaRepository<WasteManagerEntity, Integer> {

}
