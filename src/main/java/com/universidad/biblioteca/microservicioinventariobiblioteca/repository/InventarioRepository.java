package com.universidad.biblioteca.microservicioinventariobiblioteca.repository;

import com.universidad.biblioteca.microservicioinventariobiblioteca.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
