package com.universidad.biblioteca.microservicioinventariobiblioteca.repository;

import com.universidad.biblioteca.microservicioinventariobiblioteca.model.DemandaLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandaLibroRepository extends JpaRepository<DemandaLibro, Long> {

}
