package com.universidad.biblioteca.controller;

import com.universidad.biblioteca.model.Inventario;
import com.universidad.biblioteca.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    // Obtener todos los productos
    @GetMapping
    public List<Inventario> getAllInventario() {
        return inventarioRepository.findAll();
    }

    // Agregar un producto al inventario
    @PostMapping
    public Inventario agregarProducto(@RequestBody Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar un producto del inventario
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        inventarioRepository.deleteById(id);
    }
}
