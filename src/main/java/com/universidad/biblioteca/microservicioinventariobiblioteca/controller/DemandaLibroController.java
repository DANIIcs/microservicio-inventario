package com.universidad.biblioteca.microservicioinventariobiblioteca.controller;

import com.universidad.biblioteca.microservicioinventariobiblioteca.model.DemandaLibro;
import com.universidad.biblioteca.microservicioinventariobiblioteca.repository.DemandaLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demanda")
public class DemandaLibroController {

    @Autowired
    private DemandaLibroRepository demandaLibroRepository;

    // Obtener todos los registros de demanda de libros
    @GetMapping
    public List<DemandaLibro> getAllDemandas() {
        return demandaLibroRepository.findAll();
    }

    // Agregar una nueva demanda de libro
    @PostMapping
    public DemandaLibro agregarDemandaLibro(@RequestBody DemandaLibro demandaLibro) {
        return demandaLibroRepository.save(demandaLibro);
    }

    // Eliminar una demanda de libro
    @DeleteMapping("/{id}")
    public void eliminarDemandaLibro(@PathVariable Long id) {
        demandaLibroRepository.deleteById(id);
    }
}
