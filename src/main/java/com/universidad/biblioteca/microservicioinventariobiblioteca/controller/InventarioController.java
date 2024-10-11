package com.universidad.biblioteca.microservicioinventariobiblioteca.controller;

import com.universidad.biblioteca.microservicioinventariobiblioteca.model.Inventario;
import com.universidad.biblioteca.microservicioinventariobiblioteca.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping
    public List<Inventario> getAllInventario() {
        return inventarioRepository.findAll();
    }

    @PostMapping
    public Inventario agregarProducto(@RequestBody Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        inventarioRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Long id) {
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(id);
        return inventarioOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> getInventarioStockById(@PathVariable Long id) {
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(id);
        return inventarioOpt.map(inventario -> ResponseEntity.ok(inventario.getCantidad())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/modificar-stock")
    public ResponseEntity<Inventario> modificarStock(@PathVariable Long id, @RequestParam String accion) {
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(id);
        if (inventarioOpt.isPresent()) {
            Inventario inventario = inventarioOpt.get();
            if ("incrementar".equals(accion)) {
                inventario.setCantidad(inventario.getCantidad() + 1);
            } else if ("reducir".equals(accion)) {
                if (inventario.getCantidad() == 0) {
                    return ResponseEntity.badRequest().body(null);  // Devuelve error si ya no queda stock
                }
                inventario.setCantidad(inventario.getCantidad() - 1);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
            inventarioRepository.save(inventario);
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
