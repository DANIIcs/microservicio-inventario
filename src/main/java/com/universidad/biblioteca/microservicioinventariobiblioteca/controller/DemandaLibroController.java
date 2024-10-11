package com.universidad.biblioteca.microservicioinventariobiblioteca.controller;

import com.universidad.biblioteca.microservicioinventariobiblioteca.model.DemandaLibro;
import com.universidad.biblioteca.microservicioinventariobiblioteca.repository.DemandaLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
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
    public DemandaLibro agregarDemanda(@RequestBody DemandaLibro demandaLibro) {
        return demandaLibroRepository.save(demandaLibro);
    }

    // Eliminar una demanda de libro por su ID
    @DeleteMapping("/{id}")
    public void eliminarDemanda(@PathVariable Long id) {
        demandaLibroRepository.deleteById(id);
    }

    // Obtener una demanda específica por su ID
    @GetMapping("/{id}")
    public ResponseEntity<DemandaLibro> getDemandaById(@PathVariable Long id) {
        Optional<DemandaLibro> demandaOpt = demandaLibroRepository.findById(id);
        return demandaOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener el número de veces que se ha solicitado un libro específico por su ID
    @GetMapping("/{id}/solicitudes")
    public ResponseEntity<Integer> getSolicitudesById(@PathVariable Long id) {
        Optional<DemandaLibro> demandaOpt = demandaLibroRepository.findById(id);
        return demandaOpt.map(demanda -> ResponseEntity.ok(demanda.getVecesSolicitado())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Incrementar o reducir la cantidad de veces que un libro ha sido solicitado
    @PutMapping("/{id}/modificar-solicitudes")
    public ResponseEntity<DemandaLibro> modificarSolicitudes(@PathVariable Long id, @RequestParam String accion) {
        Optional<DemandaLibro> demandaOpt = demandaLibroRepository.findById(id);
        if (demandaOpt.isPresent()) {
            DemandaLibro demanda = demandaOpt.get();
            if ("incrementar".equals(accion)) {
                demanda.setVecesSolicitado(demanda.getVecesSolicitado() + 1);
            } else if ("reducir".equals(accion)) {
                if (demanda.getVecesSolicitado() == 0) {
                    return ResponseEntity.badRequest().body(null);  // Error si no se ha solicitado
                }
                demanda.setVecesSolicitado(demanda.getVecesSolicitado() - 1);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
            demandaLibroRepository.save(demanda);
            return ResponseEntity.ok(demanda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
